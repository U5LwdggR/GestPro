package Presta_Steve.Gestionpersonnel.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.Gestionpersonnel.Interfaces.IUtilisateurService;
import Presta_Steve.Gestionpersonnel.data.AuthentificationDTO;
import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.services.JwtService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {

  private final IUtilisateurService utilisateurService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @PostMapping("/creation-de-compte")
  public ResponseEntity<?> creerUtilisateur(@RequestBody Utilisateurs utilisateur) {
    try {
      this.utilisateurService.creerUtilisateur(utilisateur);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur"+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/activation-de-compte")
  public ResponseEntity<?> activationCompte(@RequestBody Map<String, String> activation) {
    try {
      this.utilisateurService.activation(activation);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

@PostMapping("/connexion")
public  Map<String,String> Connexion(@RequestBody AuthentificationDTO authentificationDTO) {
    final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.email(), authentificationDTO.motDePasse()));
    if (authenticate.isAuthenticated()) {
        // Si l'authentification est réussie, générez le token JWT
        return this.jwtService.generate(authentificationDTO.email());
    }
    return null;
}

@GetMapping("/Les-Utilisateurs")
public ResponseEntity<?> getAllUtilisateurs() {
    try {
        return new ResponseEntity<>(this.utilisateurService.afficherTousLesUtilisateurs(), HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Utilisateur/{id}")
  public ResponseEntity<?> getUtilisateurById(@PathVariable int id) {
    try {
      Utilisateurs utilisateur = this.utilisateurService.afficherUtilisateurParId(id);
      return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/modifier-utilisateur/{id}")
  public ResponseEntity<?> modifierUtilisateur(@PathVariable int id, @RequestBody Utilisateurs utilisateur) {
    try {
      this.utilisateurService.modifierUtilisateur(utilisateur, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("bloquer-utilisateur/{id}")
  public ResponseEntity<?> bloquerUtilisateur(@PathVariable int id) {
    try {
      this.utilisateurService.BloquerUtilisateur(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("debloquer-utilisateur/{id}")
  public ResponseEntity<?> debloquerUtilisateur(@PathVariable int id) {
    try {
      this.utilisateurService.DebloquerUtilisateur(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
