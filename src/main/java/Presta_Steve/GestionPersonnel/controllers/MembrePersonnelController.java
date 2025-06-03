package Presta_Steve.GestionPersonnel.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.GestionPersonnel.entities.MembrePersonnel;
import Presta_Steve.GestionPersonnel.interfaces.IMembrePersonnelService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping(path = "membrePersonnel")
@RestController
public class MembrePersonnelController {

  private final IMembrePersonnelService membrePersonnelService;


  @PostMapping(path = "Inscription" )
  public ResponseEntity<?> ajouterMembrePersonnel(@RequestBody MembrePersonnel membrePersonnel) {
    try {
      membrePersonnelService.ajouterMembrePersonnel(membrePersonnel);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<?> afficherMembrePersonnel() {
    try {
      return new ResponseEntity<>(membrePersonnelService.afficherTousLesMembresPersonnel(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path="employe/{idPer}")
  public ResponseEntity<?> employeParId(@PathVariable int idPer){
    try{
      return new ResponseEntity<>(membrePersonnelService.afficherMembrePersonnelParId(idPer),HttpStatus.ACCEPTED);
    }catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(path = "supprimer/{idPer}")
  public ResponseEntity<?> supprimerMembrePersonnel(@PathVariable int idPer) {
    try {
      membrePersonnelService.supprimerMembrePersonnel(idPer);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
