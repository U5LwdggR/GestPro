package Presta_Steve.Gestionpersonnel.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.Gestionpersonnel.entities.MembrePersonnel;
import Presta_Steve.Gestionpersonnel.interfaces.IMembrePersonnelService;
import lombok.AllArgsConstructor;



@RequestMapping(path = "membrePersonnel")
@AllArgsConstructor
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

}
