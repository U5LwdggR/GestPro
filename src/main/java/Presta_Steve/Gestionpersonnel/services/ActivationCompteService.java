package Presta_Steve.Gestionpersonnel.services;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Utilisateur;
import Presta_Steve.Gestionpersonnel.repositories.ActivationCompteRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ActivationCompteService {

private final ActivationCompteRepository activationCompteRepository;
private final EmailService emailService;
  

  public void enregistrerActivationCompte(Utilisateur superAdmin) {
    ActivationCompte activationCompte = new ActivationCompte();
    activationCompte.setIdSuperAdmin(superAdmin.getIdSup());
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
  }

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
}
