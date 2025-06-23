package Presta_Steve.Gestionpersonnel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.Gestionpersonnel.Interfaces.IEtablissementService;
import Presta_Steve.Gestionpersonnel.entities.Etablissement;
import lombok.AllArgsConstructor;

@RequestMapping("/etablissement")
@RestController
@AllArgsConstructor
public class EtablissementController {
  private final IEtablissementService etablissementService;

  @PostMapping("/ajouter")
  public ResponseEntity<?> ajouterEtablissement(@RequestBody Etablissement etablissement) {
    
    try {
      this.etablissementService.ajouterEtablissement(etablissement);
      return new ResponseEntity<>("Etablissement ajouté avec succès", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de l'ajout de l'établissement: " + e.getMessage(), HttpStatus.NOT_FOUND);

    }
  }


  @GetMapping("/lister-les-etablissements")
  public ResponseEntity<?> listerEtablissements() {
    try {
      return new ResponseEntity<>(this.etablissementService.afficherLesEtablissements(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la récupération des établissements: " + e.getMessage(), HttpStatus.NOT_FOUND);

    } 
  }

  @GetMapping("/etablissement/{id}")
  public ResponseEntity<?> getEtablissementById(@PathVariable int id) {
    try {

        return new ResponseEntity<>(this.etablissementService.AfficherEtablissementParId(id), HttpStatus.FOUND);

    } catch (Exception e) {
      return new ResponseEntity<>("Etablissement non trouvé: "+e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/modifier-etablissement/{id}")
  public ResponseEntity<?> modifierEtablissement(@PathVariable int id, @RequestBody Etablissement etablissement) {
    try {
      this.etablissementService.modifierEtablissement(etablissement, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la modification de l'établissement: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/supprimer-etablissement/{id}")
  public ResponseEntity<?> supprimerEtablissement(@RequestBody int id) {
    try {
      this.etablissementService.supprimerEtablissemnt(id);
      return new ResponseEntity<>( HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la suppression de l'établissement: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
