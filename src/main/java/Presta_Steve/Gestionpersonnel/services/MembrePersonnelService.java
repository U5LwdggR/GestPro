package Presta_Steve.Gestionpersonnel.services;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.MembrePersonnel;
import Presta_Steve.Gestionpersonnel.entities.Poste;
import Presta_Steve.Gestionpersonnel.entities.Services;
import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;
import Presta_Steve.Gestionpersonnel.interfaces.IMembrePersonnelService;
import Presta_Steve.Gestionpersonnel.repositories.MembrePersonnelRepository;
import Presta_Steve.Gestionpersonnel.repositories.PosteRepository;
import Presta_Steve.Gestionpersonnel.repositories.ServicesRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MembrePersonnelService implements IMembrePersonnelService {

    private final MembrePersonnelRepository Personnel_repository;
    private final GenerateCodeBarService generateCodeBarService;
    private final ServicesRepository servicesRepository;
    private final PosteRepository posteRepository;

    //méthode pour ajouter un membre personnel
  public void ajouterMembrePersonnel(MembrePersonnel membrePersonnel) {
    if (membrePersonnel.getEmailper() == null || !membrePersonnel.getEmailper().contains(".")) {
        throw new RuntimeException("l'email n'est pas valide...");
  }
    if (membrePersonnel.getEmailper() == null || !membrePersonnel.getEmailper().contains("@")) {
      throw new RuntimeException("l'email n'est pas valide...");
  }

  //enregistrer l'id de l'admin ou du super admin
    SuperAdmin idAdmin = (SuperAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

  //méthode pour modifier un membre personnel
  public void modifierMembrePersonnel(int id, MembrePersonnel membrePersonnel) {
    MembrePersonnel existingMembre = Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
    existingMembre.setNomPer(membrePersonnel.getNomPer());
    existingMembre.setEmailper(membrePersonnel.getEmailper());
    existingMembre.setTelPer(membrePersonnel.getTelPer());
    Personnel_repository.save(existingMembre);
  }

  //méthode pour afficher un membre personnel par son id
  public MembrePersonnel afficherMembrePersonnelParId(int id) {
    return Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
  }

  //méthode pour afficher tous les membres personnel
  public List<MembrePersonnel> afficherTousLesMembresPersonnel() {
    Iterable<MembrePersonnel> personnel = Personnel_repository.findAll();
    List<MembrePersonnel> listeMembrePersonnel = new java.util.ArrayList<>();
    personnel.forEach(listeMembrePersonnel::add);
    return listeMembrePersonnel;
  }

  //methode pour Scanner la carte d'un membre personnel

  public void scannerCarteMembrePersonnel(int code) {
    //dechiffrer l'id avant de faire la requette vers la base de donnee
    String strN = Integer.toString(code); // Convertir le nombre en chaîne
        StringBuilder resultat = new StringBuilder();

        for (char c : strN.toCharArray()) {
            int chiffre = Character.getNumericValue(c); // Convertir le caractère en chiffre
            int chiffreChiffre = (chiffre + 5) % 10; // Appliquer le décalage
            resultat.append(chiffreChiffre); // Ajouter au résultat
        }

        int id = Integer.parseInt(resultat.toString()); // Convertir en entier
        //verification de l'existance d'un membre personnel avec l'id
    MembrePersonnel membrePersonnel = Personnel_repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membre personnel introuvable avec l'id: " + id));
    // Logique pour scanner la carte du membre personnel
    System.out.println("Carte scannée pour le membre personnel: " + membrePersonnel.getNomPer());
  }

}