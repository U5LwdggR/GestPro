package Presta_Steve.GestionPersonnel.controllers;


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

import Presta_Steve.GestionPersonnel.entities.Prime;
import Presta_Steve.GestionPersonnel.interfaces.IPrimeService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("prime")
public class PrimeController {

  private final IPrimeService iPrimeService;


  @PostMapping
  public ResponseEntity<?> ajouterPrime(@RequestBody Prime prime) {
    try {
      iPrimeService.ajouterPrime(prime);
      return new ResponseEntity<>("la prime a ete ajoutee avec succes...", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("une erreur est survenue lors de l'ajout de la prime:" +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

//methode pour la suppression d'une prime
@DeleteMapping(path = "SuppressionPrime/{idPrime}")
public ResponseEntity<?> SuppressionPrime(@PathVariable int idPrime){
  try{
    this.iPrimeService.annulerPrime(idPrime);
    return new ResponseEntity<>("Suppression de la prime reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la modification d'une prime
@PutMapping(path = "ModificationPrime/{idPrime}")
public ResponseEntity<?> ModificationPrime(@PathVariable int idPrime, @RequestBody Prime prime){
  try{
    this.iPrimeService.modifierPrime(idPrime, prime);
    return new ResponseEntity<>("Modification de la prime reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la recuperation d'une prime par son id
@GetMapping(path = "AfficherPrime/{idPrime}")
public ResponseEntity<?> AfficherPrime(@PathVariable int idPrime){
  try{
    this.iPrimeService.afficherPrime(idPrime);
    return new ResponseEntity<>("Affichage de la prime reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
//methode pour la recuperation de toutes les primes
@GetMapping(path = "AfficherTousLesPrimes")
public ResponseEntity<?> AfficherTousLesPrimes(){
  try{
    this.iPrimeService.afficherToutesLesPrimes();
    return new ResponseEntity<>("Affichage de toutes les primes reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
}
