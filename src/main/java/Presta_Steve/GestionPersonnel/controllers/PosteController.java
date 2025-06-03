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

import Presta_Steve.GestionPersonnel.entities.Poste;
import Presta_Steve.GestionPersonnel.interfaces.IPosteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/poste")
@RestController
public class PosteController {

  private final IPosteService posteService;

  
//methode pour l'attribution d'un poste
@PostMapping(path = "AttributionPoste")
public ResponseEntity<?> AttributionPoste(@RequestBody Poste poste){
  try{
    this.posteService.EnregistrerPoste(poste);
    return new ResponseEntity<>(HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la suppression d'un poste
@DeleteMapping(path = "SuppressionPoste/{idPoste}")
public ResponseEntity<?> SuppressionPoste(@PathVariable int idPoste){
  try{
    this.posteService.SupprimerPoste(idPoste);
    return new ResponseEntity<>(HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
//methode pour la modification d'un poste
@DeleteMapping(path = "ModificationPoste/{idPoste}")
public ResponseEntity<?> ModificationPoste(@PathVariable int idPoste, @RequestBody Poste poste){
  try{
    this.posteService.ModifierPoste(idPoste, poste);
    return new ResponseEntity<>(HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la recuperation d'un poste par son id
@GetMapping(path = "AfficherPoste/{idPoste}")
public ResponseEntity<?> AfficherPoste(@PathVariable int idPoste){
  try{
    Poste afficherPoste = this.posteService.AfficherPoste(idPoste);
    return new ResponseEntity<>(afficherPoste,HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
//methode pour la recuperation de tous les postes
@GetMapping(path = "AfficherTousLesPostes")
public ResponseEntity<?> AfficherTousLesPostes(){
  try{
    
    return new ResponseEntity<>(this.posteService.AfficherTousLesPostes(),HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
}
