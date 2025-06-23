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

import Presta_Steve.Gestionpersonnel.Interfaces.IMatiereService;
import Presta_Steve.Gestionpersonnel.entities.Matiere;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/matiere")
public class MatiereController {

  private final IMatiereService matiereService;

  @PostMapping("/matiere")
  public ResponseEntity<?> createMatiere(@RequestBody Matiere matiere) {
    try {
      this.matiereService.creerMatiere(matiere);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("Lister-les-matieres")
  public ResponseEntity<?> getAllMatieres() {
    try {
      return new ResponseEntity<>(this.matiereService.afficherLesMatieres(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("matiere/{id}")
  public ResponseEntity<?> getMatiereById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(this.matiereService.afficherMatiereParId(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @PutMapping("/modifier-matiere/{id}")
  public ResponseEntity<?> updateMatiere(@PathVariable int id, @RequestBody Matiere matiere) {
    try {
      this.matiereService.modifierMatiere(matiere, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/supprimer-matiere/{id}")
  public ResponseEntity<?> deleteMatiere(@PathVariable int id) {
    try {
      this.matiereService.suppressionMatiere(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
