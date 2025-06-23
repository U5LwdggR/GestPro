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

import Presta_Steve.Gestionpersonnel.Interfaces.IOptionService;
import Presta_Steve.Gestionpersonnel.entities.Option;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/option")
public class OptionController {

  private final IOptionService optionService;

  @PostMapping("option")
  public ResponseEntity<?> create(@RequestBody Option option){
    try {
      this.optionService.creerOption(option);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("Lister-les-options")
  public ResponseEntity<?> getAllOptions() {
    try {
      return new ResponseEntity<>(this.optionService.afficherLesOptions(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("option/{id}")
  public ResponseEntity<?> getOptionById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(this.optionService.afficherOptionParId(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/modifier-option/{id}")
  public ResponseEntity<?> updateOption(@PathVariable int id, @RequestBody Option option) {
    try {
      this.optionService.modifierOption(option, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/supprimer-option/{id}")
  public ResponseEntity<?> deleteOption(@PathVariable int id) {
    try {
      this.optionService.suppressionOption(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
