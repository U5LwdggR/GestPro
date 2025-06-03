package Presta_Steve.GestionPersonnel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.GestionPersonnel.entities.Sanction;
import Presta_Steve.GestionPersonnel.interfaces.ISanctionService;
import lombok.AllArgsConstructor;


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

  //methode pour l'attribution d'une sanction
@PostMapping(path = "AttributionSanction")
public ResponseEntity<?> AttributionSanction(@RequestBody Sanction sanction){
  try{
    this.sanctionService.addSanction(sanction);
    return new ResponseEntity<>("Attribution de la sanction reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la suppression d'une sanction
@PutMapping(path = "SuppressionSanction/{idSanction}")
public ResponseEntity<?> SuppressionSanction(@PathVariable int idSanction){
  try{
    this.sanctionService.deleteSanction(idSanction);
    return new ResponseEntity<>("Suppression de la sanction reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la modification d'une sanction
@PutMapping(path = "ModificationSanction/{idSanction}")
public ResponseEntity<?> ModificationSanction(@PathVariable int idSanction, @RequestBody Sanction sanction){
  try{
    this.sanctionService.updateSanction(idSanction, sanction);
    return new ResponseEntity<>("Modification de la sanction reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la recuperation d'une sanction par son id
@GetMapping(path = "AfficherSanction/{idSanction}")
public ResponseEntity<?> AfficherSanction(@PathVariable int idSanction){
  try{
    this.sanctionService.AfficherSanction(idSanction);
    return new ResponseEntity<>("Affichage de la sanction reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la recuperation de toutes les sanctions
@GetMapping(path = "AfficherTousLesSanctions")
public ResponseEntity<?> AfficherTousLesSanctions(){
  try{
    this.sanctionService.AfficherTousLesPostes();
    return new ResponseEntity<>("Affichage de toutes les sanctions reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

}
