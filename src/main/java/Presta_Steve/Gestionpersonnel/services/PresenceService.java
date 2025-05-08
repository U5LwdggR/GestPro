package Presta_Steve.Gestionpersonnel.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.MembrePersonnel;
import Presta_Steve.Gestionpersonnel.entities.Poste;
import Presta_Steve.Gestionpersonnel.entities.Presence;
import Presta_Steve.Gestionpersonnel.interfaces.IPresenceService;
import Presta_Steve.Gestionpersonnel.repositories.MembrePersonnelRepository;
import Presta_Steve.Gestionpersonnel.repositories.PosteRepository;
import Presta_Steve.Gestionpersonnel.repositories.PresenceRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PresenceService implements IPresenceService {

  private final PresenceRepository presenceRepository;
  private final MembrePersonnelRepository membrePersonnelRepository;
  private final GenerateCodeBarService generateCodeBarService;
  private final PosteRepository posteRepository;

//heure d'arrive 09:00:00
//heure de depart :
                  // -employe: 19:30
                  // -stagiaire: 16h30
  public void ajouterPresence(int code) {
    Presence presence = new Presence();
    
    int id = this.generateCodeBarService.dechiffrerIdCesar(code);

    presence.setIdPer(id);
    LocalTime heureArrive =  LocalTime.now();
    //heure de depart employe est compris entre [19h30 - 22h]
    LocalTime heureDepartEmploye = LocalTime.parse("19:30:00");
    //heure de depart employe est compris entre [16h30 - 22h]
    LocalTime heureDepartStagiaire = LocalTime.parse("16:30:00");
    presence.setHeureArrive(heureArrive);
    LocalDate jourMoisAnnee = LocalDate.now();
    presence.setPeriode(jourMoisAnnee);
    //System.out.println(code + "est devenu" + id);
    //verifier si l'utilisateur n'a pas deja marque sa presence un premiere fois
    Optional<Presence> verifier_si_presence_est_deja_marque = presenceRepository.findByIdPer(id);
    //Long nbre_de_marquage_presence = presenceRepository.countByIdPer(id);

    //verification de l'existance d'un membre personnel avec l'id
    Optional<MembrePersonnel> membrePersonnel = membrePersonnelRepository.findById(id);
    if (membrePersonnel.isEmpty()) {
      throw new RuntimeException("ce membre du personnel n'existe pas");
    }
    //recuperation des infos sur le membre du personnel
    MembrePersonnel personnelTrouve = membrePersonnel.get();
    Optional<Poste> obtenir_poste_personnel = this.posteRepository.findById(personnelTrouve.getIdPost());
    if (obtenir_poste_personnel.isEmpty()) {
      throw new RuntimeException("le poste ce membre du personnel n'existe pas");
    }
    Poste poste_personnel_trouver = obtenir_poste_personnel.get();
    if (verifier_si_presence_est_deja_marque.isPresent()) {
        Presence periodeMarquage = verifier_si_presence_est_deja_marque.get();
        //si cette condition est respecte ca veux dire qu'il a deja scanne une fois
        if (periodeMarquage.getPeriode().equals(LocalDate.now())) {
          //processus du depart d'un employe
          LocalTime heure_depart = LocalTime.now();
          if (poste_personnel_trouver.getNomPost() == "stagiaire") {
            if (heureDepartStagiaire.isBefore(heure_depart) || heureDepartStagiaire.equals(heure_depart) ) {
              presence.setHeureDepart(heure_depart);
              presenceRepository.save(presence);
              System.out.println("au revoir et a demain stagiaire");
            }
          }
          if (heureDepartEmploye.isBefore(heure_depart) || heureDepartEmploye.equals(heure_depart) ) {
              presence.setHeureDepart(heure_depart);
              presenceRepository.save(presence);
              System.out.println("au revoir et a demain employe");

          }else{
            System.out.println("Désolé, vous ne pouvez pas marquer votre présence deux fois la même journée.");
          }
        }

        if(presence.getHeureArrive().isAfter(presence.getHeureStandard())){

          Duration duree = Duration.between(presence.getHeureStandard(), presence.getHeureArrive());
          long heure = duree.toHours();
          long minutes = duree.toMinutes() % 60;
          presence.setEnRetard(true);
          System.out.println("Bonjour "+personnelTrouve.getNomPer()+" vous etes en retard de "+heure+" heures et "+minutes+" minute aujourd'hui");

        }else if(presence.getHeureArrive().isBefore(presence.getHeureStandard())){
          presence.setEnRetard(false);
          System.out.println("Bienvenue "+personnelTrouve.getNomPer()+" Comment allez vous ce matin ?");
          
        }else{
          presence.setEnRetard(false);
          System.out.println("Bonjour "+personnelTrouve.getNomPer()+" vous etes presque en retard, mais faites attention la prochaine fois");
        }  
  }
  else{
    
  }
    presenceRepository.save(presence);
    }
  }
