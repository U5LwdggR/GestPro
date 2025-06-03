package Presta_Steve.GestionPersonnel.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.MembrePersonnel;
import Presta_Steve.GestionPersonnel.entities.Poste;
import Presta_Steve.GestionPersonnel.entities.Presence;
import Presta_Steve.GestionPersonnel.interfaces.IPresenceService;
import Presta_Steve.GestionPersonnel.repositories.MembrePersonnelRepository;
import Presta_Steve.GestionPersonnel.repositories.PosteRepository;
import Presta_Steve.GestionPersonnel.repositories.PresenceRepository;
import lombok.AllArgsConstructor;
// import t2s.son.LecteurTexte;

@AllArgsConstructor
@Service
public class PresenceService implements IPresenceService {

    private final PresenceRepository presenceRepository;
    private final MembrePersonnelRepository membrePersonnelRepository;
    private final GenerateCodeBarService generateCodeBarService;
    private final PosteRepository posteRepository;
    private final EmailService emailService;
    //private final LecteurTexte lecteurOrale;

    @Override
    public void ajouterPresence(int code) {  
        String nombreStr = String.valueOf(code);
        String resultatStr = nombreStr.substring(1);
        int resultat = Integer.parseInt(resultatStr);
        System.out.println(resultatStr);
        int id = this.generateCodeBarService.dechiffrerIdCesar(resultat);
        LocalDate aujourdhui = LocalDate.now();
        LocalTime maintenant = LocalTime.now();

        // Vérification du membre
        MembrePersonnel personnel = membrePersonnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce membre du personnel n'existe pas"));
        Presence presence = new Presence();
        presence.setIdPer(id);
        presence.setHeureArrive(maintenant);
        presence.setPeriode(aujourdhui);

        // Vérification du poste
        Poste poste = posteRepository.findById(personnel.getIdPost())
                .orElseThrow(() -> new RuntimeException("Le poste de ce membre n'existe pas"));

        // Gérer le cas des employés qui ne sont pas venus
        LocalTime heureLimite = LocalTime.of(19, 0); // 19:00:00

        if (maintenant.isAfter(heureLimite) || maintenant.equals(heureLimite)) {
            // Récupérer tous les membres du personnel
            Iterable<MembrePersonnel> tousLesMembres = membrePersonnelRepository.findAll();
            
            for (MembrePersonnel membre : tousLesMembres) {
                // Vérifier s'il n'a pas de présence enregistrée aujourd'hui
                boolean absent = presenceRepository
                    .findByIdPerAndPeriode(membre.getIdPer(), aujourdhui)
                    .isEmpty();
                
                if (absent) {
                    Presence absence = new Presence();
                    absence.setIdPer(membre.getIdPer());
                    absence.setPresent(false);
                    absence.setPeriode(aujourdhui);
                    absence.setHeureStandard(heureLimite);
                    
                    this.emailService.sendEmailEmploye(membre);
                    
                    presenceRepository.save(absence);
                    // lecteurOrale.setTexte("Absence enregistrée pour: " + membre.getNomPer());
                    // lecteurOrale.playAll();
                    System.out.println("Absence enregistrée pour: " + membre.getNomPer());
                }
            }
        }

        // Vérification présence existante
        Optional<Presence> presenceExistante = presenceRepository.findByIdPerAndPeriode(id, aujourdhui);

        if (presenceExistante.isPresent()) {
            Presence existante = presenceExistante.get();
            
            // Empêcher les absents de marquer leur départ
            if (existante.getPresent() != null && !existante.getPresent()) {
                
                // lecteurOrale.setTexte();
                // lecteurOrale.playAll();
                System.out.println("Vous êtes marqué comme absent aujourd'hui, vous ne pouvez pas marquer votre départ.");
                return;
            }
            
            LocalTime heureDepart = LocalTime.now();
            
            // Vérifier si l'heure de départ a déjà été enregistrée
            if (existante.getHeureDepart() != null) {
                // lecteurOrale.setTexte();
                // lecteurOrale.playAll();
                System.out.println("Votre heure de départ a déjà été enregistrée aujourd'hui.");
                return;
            }
            
            // Gérer les départs
            if (poste.getNomPost().equals("stagiaire")) {
                LocalTime heureDepartStagiaire = LocalTime.parse("16:30:00");
                if (!heureDepart.isBefore(heureDepartStagiaire)) {
                    existante.setHeureDepart(heureDepart);
                    presenceRepository.save(existante);
                    // lecteurOrale.setTexte();
                    // lecteurOrale.playAll();
                    System.out.println("Au revoir et à demain stagiaire "+personnel.getNomPer());
                    return;
                }
            } else {
                LocalTime heureDepartEmploye = LocalTime.parse("19:30:00");
                if (!heureDepart.isBefore(heureDepartEmploye)) {
                    existante.setHeureDepart(heureDepart);
                    presenceRepository.save(existante);
                    // lecteurOrale.setTexte();
                    // lecteurOrale.playAll();
                    System.out.println("Au revoir et à demain employé "+personnel.getNomPer());
                    return;
                }
            }
            // lecteurOrale.setTexte();
            // lecteurOrale.playAll();
            System.out.println("Désolé, vous ne pouvez pas marquer votre présence deux fois la même journée.");
            return;
        }

        // Gestion de l'arrivée
        LocalTime heureStandard = LocalTime.of(9, 0);
        presence.setHeureStandard(heureStandard);
        presence.setPresent(true);

        if (maintenant.isAfter(heureStandard)) {
            Duration retard = Duration.between(heureStandard, maintenant);
            presence.setEnRetard(true);
            // lecteurOrale.setTexte();
            // lecteurOrale.playAll();
            System.out.println("Bonjour"+ personnel.getNomPer() +", vous êtes en retard de"+ retard.toHours()+ "heures et"+ retard.toMinutes() % 60 +"minutes aujourd'hui%n");
        } else if (maintenant.isBefore(heureStandard)) {
            presence.setEnRetard(false);
            System.out.printf("Bienvenue %s, comment allez-vous ce matin ?%n", personnel.getNomPer());
            // lecteurOrale.setTexte("Bienvenue "+personnel.getNomPer()+", comment allez-vous ce matin ?%n");
            // lecteurOrale.playAll();
        } else {
            presence.setEnRetard(false);
                    // lecteurOrale.setTexte();
                    // lecteurOrale.playAll();
                    System.out.println("Bonjour "+personnel.getNomPer()+", vous êtes presque en retard, faites attention la prochaine fois%n");
        }

        presenceRepository.save(presence);
    }

    @Override
    public List<Presence> AfficherToutesLesPresences() {
    Iterable<Presence> iterable = presenceRepository.findAll();
    List<Presence> presence = new ArrayList<>();
    iterable.forEach(presence::add);
    return presence;
}

@Override
    public void SupprimerPresence(int id) {
    Optional<Presence> chercherPresence = presenceRepository.findById(id);
    if (chercherPresence.isPresent()) {
        presenceRepository.deleteById(id);
    } else {
        throw new IllegalArgumentException("ce pointage n'existe pas, donc il ne peut pas être supprimé");
    }
}

@Override
public void modifierPresence(Presence presence, int id) {
    Optional<Presence> chercherService = presenceRepository.findById(id);
    if (chercherService.isPresent()) {
        Presence presenceToUpdate = chercherService.get();
        presenceToUpdate.setHeureArrive(presence.getHeureArrive());
        presenceToUpdate.setHeureDepart(presence.getHeureDepart());
        presenceToUpdate.setPeriode(presence.getPeriode());
    presenceRepository.save(presenceToUpdate);
    } else {
        throw new IllegalArgumentException("ce service n'existe pas, donc il ne peut pas être modifié");
    }
    
}
}