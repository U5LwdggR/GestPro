package Presta_Steve.Gestionpersonnel.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IUtilisateurService;
import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.repositories.UtilisateursRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService, UserDetailsService {

    //Injection des dependances
  private final UtilisateursRepository utilisateursRepository;
    private final BCryptPasswordEncoder cryptMdp;
    private final ActivationCompteService activationCompteService;

  //creation d'une Utilisateur
  public void creerUtilisateur(Utilisateurs Utilisateur){
    try {
      //verification sur l'email
      if ((!Utilisateur.getEmail().contains("@") && !Utilisateur.getEmail().contains(".")) || (!Utilisateur.getEmail().contains("@") || !Utilisateur.getEmail().contains("."))) {
        throw new RuntimeException("votre email est invalide");
      }
      Optional<Utilisateurs> UtilisateurCherhe = this.utilisateursRepository.findByEmail(Utilisateur.getEmail());
      if (UtilisateurCherhe.isPresent()) {
        throw new RuntimeException("Cet email existe deja veuillez la changer...");
      }
      //crypter le mot de passe
      Utilisateur.setMdp(this.cryptMdp.encode(Utilisateur.getMdp()));

      //enregistrement de l'Utilisateur
      this.utilisateursRepository.save(Utilisateur);

      //envoyer le code d'activation de code par email
      this.activationCompteService.creeractivationCompte(Utilisateur);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation de l'Utilisateur");
    }
  }

    //activer compte
  public void activation(Map<String, String> activation) {
    ActivationCompte activationCompte = this.activationCompteService.LireEnfonctionDuCode(activation.get("code"));
    if (Instant.now().isAfter(activationCompte.getExpirationDate())) {
        throw new RuntimeException("le code a expiré");
    }
    Utilisateurs utilisateurActiver = this.utilisateursRepository.findById(activationCompte.getUtilisateur().getIdUser()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
    utilisateurActiver.setActif(true);
    this.utilisateursRepository.save(utilisateurActiver);
  }

  //afficher toutes les Utilisateurs
  public List<Utilisateurs> afficherTousLesUtilisateurs(){
    try {
      List<Utilisateurs> UtilisateurCherche = (List<Utilisateurs>) this.utilisateursRepository.findAll();
      if (UtilisateurCherche.isEmpty()) {
        System.out.println("aucun Utilisateur n'a ete retouve");
      }
      return UtilisateurCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des Utilisateurs: "+e);
    }
  }

  //afficher les Utilisateurs en fonction de leur id
  public Utilisateurs afficherUtilisateurParId(int idUser){
    try {
      Optional<Utilisateurs> UtilisateurCherhe = this.utilisateursRepository.findById(idUser);
      if (UtilisateurCherhe.isEmpty()) {
        throw new RuntimeException("ce Utilisateur n'existe pas dans le systeme");
      }
      Utilisateurs UtilisateurTrouve = UtilisateurCherhe.get();
      return UtilisateurTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage l'Utilisateur: "+e);
    }
  }

  //modifier un Utilisateur
  public void modifierUtilisateur(Utilisateurs Utilisateur, int idUser){
    try {
      Optional<Utilisateurs> UtilisateurCherhe = this.utilisateursRepository.findById(idUser);
      if (UtilisateurCherhe.isEmpty()) {
        throw new RuntimeException("cette Utilisateur n'existe pas dans le systeme");
      }
      Utilisateurs UtilisateurTrouve = UtilisateurCherhe.get();
      UtilisateurTrouve.setNom(Utilisateur.getNom());
      UtilisateurTrouve.setEmail(Utilisateur.getEmail());
      UtilisateurTrouve.setMdp(Utilisateur.getMdp());
      UtilisateurTrouve.setPhoto(Utilisateur.getPhoto());
      UtilisateurTrouve.setTelephone(Utilisateur.getTelephone());
      this.utilisateursRepository.save(UtilisateurTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de la Utilisateur: "+e);
    }
  }

  //bloquer le compte d'un Utilisateur(fait par l'admin)
  public void BloquerUtilisateur(int idUser){
    try {
      Optional<Utilisateurs> UtilisateurCherhe = this.utilisateursRepository.findById(idUser);
      if (UtilisateurCherhe.isEmpty()) {
        throw new RuntimeException("cette Utilisateur n'existe pas dans le systeme");
      }
      Utilisateurs utilisateurTrouve = UtilisateurCherhe.get();
      utilisateurTrouve.setActif(false);
      this.utilisateursRepository.save(utilisateurTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur au bloquage du compte de l'Utilisateur: "+e);
    }
  }

  //Activer le compte d'un Utilisateur(fait par l'admin)
  public void DebloquerUtilisateur(int idUser){
    try {
      Optional<Utilisateurs> UtilisateurCherhe = this.utilisateursRepository.findById(idUser);
      if (UtilisateurCherhe.isEmpty()) {
        throw new RuntimeException("cette Utilisateur n'existe pas dans le systeme");
      }
      Utilisateurs utilisateurTrouve = UtilisateurCherhe.get();
      utilisateurTrouve.setActif(true);
      this.utilisateursRepository.save(utilisateurTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'activation du compte de l'Utilisateur: "+e);
    }
  }

  //activer le compte avec un code envoye par email
  public void activerCompteAvecCode(Map<String, String> activation){
    ActivationCompte activationCompte = this.activationCompteService.LireEnfonctionDuCode(activation.get("code"));
    if (Instant.now().isAfter(activationCompte.getExpirationDate())) {
        throw new RuntimeException("le code a expiré");
    
    }
    Utilisateurs utilisateur = this.utilisateursRepository.findById(activationCompte.getUtilisateur().getIdUser()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
    utilisateur.setActif(true);
    this.utilisateursRepository.save(utilisateur);
  }

      //methode pour la verification de l'existance de l'utilisateur dans la bd
      public void connexion(String email, String mdpSup) {
        // Récupérer le SuperAdmin par email
        Optional<Utilisateurs> findUser = this.utilisateursRepository.findByEmail(email);
    
        // Vérifier si le SuperAdmin existe
        if (findUser.isEmpty()) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    
        // Comparer le mot de passe brut avec le mot de passe haché
        Utilisateurs utilisateurs = findUser.get();
        if (!this.cryptMdp.matches(mdpSup, utilisateurs.getMdp())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    }

  @Override
  public Utilisateurs loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Utilisateurs> utilisateur = this.utilisateursRepository.findByEmail(username);
    if (utilisateur.isEmpty()) {
      throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + username);
    }
    
    return utilisateur.get();
  }


}
