package Presta_Steve.Gestionpersonnel.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Presta_Steve.Gestionpersonnel.interfaces.ISanctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import Presta_Steve.Gestionpersonnel.entities.Sanction;


@RestController
@AllArgsConstructor
@RequestMapping("/sanction")
public class SanctionController {

  private final ISanctionService sanctionService;

  @PostMapping
  public ResponseEntity<?> addSanction(@RequestBody Sanction sanction) {
    try {
      sanctionService.addSanction(sanction);
      return new ResponseEntity<>("la sanction a ete assignee avec succes...", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("une erreur est survenue lors de l'assignation de la sanction: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
