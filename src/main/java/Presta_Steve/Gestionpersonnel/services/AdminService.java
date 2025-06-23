package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.repositories.UtilisateursRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
  private final UtilisateursRepository utilisateursRepository;

  //creation d'une Utilisateur
  public void creerUtilisateur(Utilisateurs Utilisateur){
    try {
      this.utilisateursRepository.save(Utilisateur);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation de l'Utilisateur");
    }
  }

  //afficher toutes les Utilisateurs
  public List<Utilisateurs> afficherLesUtilisateurs(){
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
  public void ActiverUtilisateur(int idUser){
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
  public void activerCompteAvecCode(){

  }
}
