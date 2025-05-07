package Presta_Steve.Gestionpersonnel.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Presta_Steve.Gestionpersonnel.interfaces.IPrimeService;
import lombok.AllArgsConstructor;
import Presta_Steve.Gestionpersonnel.entities.Prime;
import org.springframework.web.bind.annotation.PostMapping;


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

}
