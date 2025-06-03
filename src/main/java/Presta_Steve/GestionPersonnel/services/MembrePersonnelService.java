package Presta_Steve.GestionPersonnel.services;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.MembrePersonnel;
import Presta_Steve.GestionPersonnel.entities.Poste;
import Presta_Steve.GestionPersonnel.entities.Services;
import Presta_Steve.GestionPersonnel.entities.Utilisateur;
import Presta_Steve.GestionPersonnel.interfaces.IMembrePersonnelService;
import Presta_Steve.GestionPersonnel.repositories.MembrePersonnelRepository;
import Presta_Steve.GestionPersonnel.repositories.PosteRepository;
import Presta_Steve.GestionPersonnel.repositories.ServicesRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MembrePersonnelService implements IMembrePersonnelService {

    private final MembrePersonnelRepository Personnel_repository;
    private final GenerateCodeBarService generateCodeBarService;
    private final ServicesRepository servicesRepository;
    private final PosteRepository posteRepository;

    //méthode pour ajouter un membre personnel
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
  public void ajouterMembrePersonnel(MembrePersonnel membrePersonnel) {
    if (membrePersonnel.getEmailper() == null || !membrePersonnel.getEmailper().contains(".")) {
        throw new RuntimeException("l'email n'est pas valide...");
  }
    if (membrePersonnel.getEmailper() == null || !membrePersonnel.getEmailper().contains("@")) {
      throw new RuntimeException("l'email n'est pas valide...");
  }

  //enregistrer l'id de l'admin ou du super admin
    Utilisateur idAdmin = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    membrePersonnel.setIdSup(idAdmin.getIdSup());

    Services infosService = this.servicesRepository.findById(membrePersonnel.getIdSer()).orElseThrow(() -> new RuntimeException("ce service n'existe pas "));
    Poste infosPoste = this.posteRepository.findById(membrePersonnel.getIdPost()).orElseThrow(() -> new RuntimeException("ce poste n'existe pas "));

    Personnel_repository.save(membrePersonnel);
    // Chiffrement de l'id du membre personnel avant de le convertir en chaîne de caractères
    int idChiffre = generateCodeBarService.chiffrerIdCesar(membrePersonnel.getIdPer());
    //conversion de l'id en chaine de caractere
    String idString = String.valueOf(idChiffre);
    // Générer le code-barres après l'ajout du membre
    try {
      generateCodeBarService.generateBarcodeToImage(idString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try{
    generateCodeBarService.WriteInformationMembrePersonnel(membrePersonnel.getNomPer(), infosService.getNomSer(),infosPoste.getNomPost() , membrePersonnel.getTelPer(),membrePersonnel.getEmailper(), membrePersonnel.getPhotoPer());
  }catch (IOException e) {
    e.printStackTrace();
  }
  }

  @Override
  //méthode pour modifier un membre personnel
  public void modifierMembrePersonnel(int id, MembrePersonnel membrePersonnel) {
    MembrePersonnel existingMembre = Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
    existingMembre.setNomPer(membrePersonnel.getNomPer());
    existingMembre.setEmailper(membrePersonnel.getEmailper());
    existingMembre.setTelPer(membrePersonnel.getTelPer());
    Personnel_repository.save(existingMembre);
  }

  @Override
  //méthode pour afficher un membre personnel par son id
  public MembrePersonnel afficherMembrePersonnelParId(int id) {
    return Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
  }

  @Override
  //méthode pour afficher tous les membres personnel
  public List<MembrePersonnel> afficherTousLesMembresPersonnel() {
    Iterable<MembrePersonnel> personnel = Personnel_repository.findAll();
    List<MembrePersonnel> listeMembrePersonnel = new java.util.ArrayList<>();
    personnel.forEach(listeMembrePersonnel::add);
    return listeMembrePersonnel;
  }

  @Override
  //méthode pour supprimer un membre personnel
  public void supprimerMembrePersonnel(int id) {
    MembrePersonnel membrePersonnel = Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
    Personnel_repository.delete(membrePersonnel);
  }

  //méthode pour changer le statut du compte
  // public void changerStatutCompte(int id, boolean statut) {
  //   MembrePersonnel membrePersonnel = Personnel_repository.findById(id)
  //       .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
  //   membrePersonnel.setStatutCompte(statut);
  //   Personnel_repository.save(membrePersonnel);
  // }

}