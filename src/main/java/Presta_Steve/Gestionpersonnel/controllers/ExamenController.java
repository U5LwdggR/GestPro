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

import Presta_Steve.Gestionpersonnel.Interfaces.IExamenService;
import Presta_Steve.Gestionpersonnel.entities.Examen;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/examen")
public class ExamenController {

  private final IExamenService examenService;

  @PostMapping("/ajouter")
  public ResponseEntity<?> ajouterExamen(@RequestBody Examen examen) {
    try {
      this.examenService.creerExamen(examen);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de l'ajout de l'examen: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/lister-les-examens")
  public ResponseEntity<?> listerExamens() {
    try {
      return new ResponseEntity<>(this.examenService.afficherLesExamens(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la récupération des examens: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/examen/{id}")
  public ResponseEntity<?> getExamenById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(this.examenService.afficherExamenParId(id), HttpStatus.FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("Examen non trouvé: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/modifier-examen/{id}")
  public ResponseEntity<?> modifierExamen(@PathVariable int id, @RequestBody Examen examen) {
    try {
      this.examenService.modifierExamen(examen, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la modification de l'examen: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/supprimer-examen/{id}")
  public ResponseEntity<?> supprimerExamen(@PathVariable int id) {
    try {
      this.examenService.suppressionExamen(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur lors de la suppression de l'examen: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

}
