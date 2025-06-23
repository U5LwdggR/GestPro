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

import Presta_Steve.Gestionpersonnel.Interfaces.ISequenceService;
import Presta_Steve.Gestionpersonnel.entities.Sequence;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/sequence")
public class SequenceController {

  private final ISequenceService sequenceService;

  @PostMapping("/sequence")
  public ResponseEntity<?> create(@RequestBody Sequence sequence) {
    try {
      this.sequenceService.creersequence(sequence);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/Lister-les-sequences")
  public ResponseEntity<?> getAllSequences() {
    try {
      return new ResponseEntity<>(this.sequenceService.afficherLessequences(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/sequence/{id}")
  public ResponseEntity<?> getSequenceById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(this.sequenceService.affichersequenceParId(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/modifier-sequence/{id}")
  public ResponseEntity<?> updateSequence(@PathVariable int id, @RequestBody Sequence sequence) {
    try {
      this.sequenceService.modifiersequence(sequence, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/supprimer-sequence/{id}")
  public ResponseEntity<?> deleteSequence(@PathVariable int id) {
    try {
      this.sequenceService.suppressionsequence(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
