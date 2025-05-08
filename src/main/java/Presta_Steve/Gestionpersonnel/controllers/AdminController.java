package Presta_Steve.Gestionpersonnel.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.Gestionpersonnel.Securite.JwtService;
import Presta_Steve.Gestionpersonnel.data.AuthentificationDTO;
import Presta_Steve.Gestionpersonnel.entities.Poste;
import Presta_Steve.Gestionpersonnel.entities.Prime;
import Presta_Steve.Gestionpersonnel.entities.Sanction;
import Presta_Steve.Gestionpersonnel.entities.Utilisateur;
import Presta_Steve.Gestionpersonnel.interfaces.IAdminService;
import Presta_Steve.Gestionpersonnel.interfaces.IPosteService;
import Presta_Steve.Gestionpersonnel.interfaces.IPrimeService;
import Presta_Steve.Gestionpersonnel.interfaces.ISanctionService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RequestMapping(path = "admin")
@RestController
public class AdminController {

    private final IPosteService posteService;
    private final ISanctionService sanctionService;
    private final IPrimeService primeService;
    private final IAdminService adminService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

      @PostMapping(path = "inscription")
      public ResponseEntity<?> ajouterAdmin(@RequestBody Utilisateur admin) {
        try {
          //Enregistrement
          this.adminService.ajouterAdmin(admin); 
          return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path = "connexion")
    public  Map<String,String> Connexion(@RequestBody AuthentificationDTO authentificationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.UserName(), authentificationDTO.password()));
        if (authenticate.isAuthenticated()) {
            // Si l'authentification est réussie, générez le token JWT
            return this.jwtService.generate(authentificationDTO.UserName());
        }
        return null;

    }

      @PostMapping(path = "activationCompte")
    public ResponseEntity<?> activationCompteAdmin(@RequestBody Map<String, String> activation){
      try{
        this.adminService.activation(activation);
        return new ResponseEntity<>("activation reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //Action du superAdmin(ajout,modification,suppression) sur les sanctions

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

    //Action du superAdmin(ajout,modification,suppression) sur les primes

    //methode pour l'attribution d'une prime
    @PostMapping(path = "AttributionPrime")
    public ResponseEntity<?> AttributionPrime(@RequestBody Prime prime){
      try{
        this.primeService.ajouterPrime(prime);
        return new ResponseEntity<>("Attribution de la prime reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //methode pour la suppression d'une prime
    @DeleteMapping(path = "SuppressionPrime/{idPrime}")
    public ResponseEntity<?> SuppressionPrime(@PathVariable int idPrime){
      try{
        this.primeService.annulerPrime(idPrime);
        return new ResponseEntity<>("Suppression de la prime reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //methode pour la modification d'une prime
    @PutMapping(path = "ModificationPrime/{idPrime}")
    public ResponseEntity<?> ModificationPrime(@PathVariable int idPrime, @RequestBody Prime prime){
      try{
        this.primeService.modifierPrime(idPrime, prime);
        return new ResponseEntity<>("Modification de la prime reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //methode pour la recuperation d'une prime par son id
    @GetMapping(path = "AfficherPrime/{idPrime}")
    public ResponseEntity<?> AfficherPrime(@PathVariable int idPrime){
      try{
        this.primeService.afficherPrime(idPrime);
        return new ResponseEntity<>("Affichage de la prime reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    //methode pour la recuperation de toutes les primes
    @GetMapping(path = "AfficherTousLesPrimes")
    public ResponseEntity<?> AfficherTousLesPrimes(){
      try{
        this.primeService.afficherToutesLesPrimes();
        return new ResponseEntity<>("Affichage de toutes les primes reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //Action du superAdmin(ajout,modification,suppression) sur les postes

    //methode pour l'attribution d'un poste
    @PostMapping(path = "AttributionPoste")
    public ResponseEntity<?> AttributionPoste(@RequestBody Poste poste){
      try{
        this.posteService.EnregistrerPoste(poste);
        return new ResponseEntity<>("Attribution du poste reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //methode pour la suppression d'un poste
    @DeleteMapping(path = "SuppressionPoste/{idPoste}")
    public ResponseEntity<?> SuppressionPoste(@PathVariable int idPoste){
      try{
        this.posteService.SupprimerPoste(idPoste);
        return new ResponseEntity<>("Suppression du poste reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    //methode pour la modification d'un poste
    @DeleteMapping(path = "ModificationPoste/{idPoste}")
    public ResponseEntity<?> ModificationPoste(@PathVariable int idPoste, @RequestBody Poste poste){
      try{
        this.posteService.ModifierPoste(idPoste, poste);
        return new ResponseEntity<>("Modification du poste reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //methode pour la recuperation d'un poste par son id
    @GetMapping(path = "AfficherPoste/{idPoste}")
    public ResponseEntity<?> AfficherPoste(@PathVariable int idPoste){
      try{
        this.posteService.AfficherPoste(idPoste);
        return new ResponseEntity<>("Affichage du poste reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    //methode pour la recuperation de tous les postes
    @GetMapping(path = "AfficherTousLesPostes")
    public ResponseEntity<?> AfficherTousLesPostes(){
      try{
        this.posteService.AfficherTousLesPostes();
        return new ResponseEntity<>("Affichage de tous les postes reussie...", HttpStatus.OK);
      }catch (Exception e){
        return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
