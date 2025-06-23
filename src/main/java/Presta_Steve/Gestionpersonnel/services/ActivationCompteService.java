package Presta_Steve.Gestionpersonnel.services;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.repositories.ActivationCompteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivationCompteService {
  private final ActivationCompteRepository activationCompteRepository;
  private final MailService emailService;


    //creation d'une activationCompte
  public void creeractivationCompte(Utilisateurs utilisateurs){
    try {
    ActivationCompte activationCompte = new ActivationCompte();
    activationCompte.setUtilisateur(utilisateurs);
    Instant creation = Instant.now();
    activationCompte.setCreationDate(creation);
    Instant expiration = creation.plus(10,MINUTES); 
    activationCompte.setExpirationDate(expiration);
    Random random = new Random();
    int randomInt = random.nextInt(1000000); //generer un nombre aleatoire entre 0 et 999999
    String code = String.format("%06d", randomInt); //formater en une chaine de 6 chiffres
    activationCompte.setCode(code);
    this.activationCompteRepository.save(activationCompte);
    this.emailService.sendEmail(activationCompte); //envoi de l'email
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'une activationCompte");
    }
  }

  //activer compte
  public ActivationCompte LireEnfonctionDuCode(String code) {
    Instant Activation = Instant.now(); // Date et heure actuelle
    Optional<ActivationCompte> activationCompte = this.activationCompteRepository.findByCode(code);
    if (activationCompte.isPresent()) {
      activationCompte.get().setActivationDate(Activation);
      return activationCompte.get();
    } else {
      throw new RuntimeException("ce code est invalide");
      
    }
  }

  //afficher toutes les activationComptes
  public List<ActivationCompte> afficherLesactivationComptes(){
    try {
      List<ActivationCompte> activationCompteCherche = (List<ActivationCompte>) this.activationCompteRepository.findAll();
      if (activationCompteCherche.isEmpty()) {
        System.out.println("aucune activationCompte n'a ete retouvee");
      }
      return activationCompteCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des activationComptes: "+e);
    }
  }

  //supprimer un activationCompte
  public void suppressionactivationCompte(int idM){
    try {
      Optional<ActivationCompte> activationCompteCherhe = this.activationCompteRepository.findById(idM);
      if (activationCompteCherhe.isEmpty()) {
        throw new RuntimeException("cette activationCompte n'existe pas dans le systeme");
      }
      this.activationCompteRepository.deleteById(idM);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de la activationCompte: "+e);
    }
  }
}
