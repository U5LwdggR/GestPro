package Presta_Steve.GestionPersonnel.controllers;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.GestionPersonnel.Securite.JwtService;
import Presta_Steve.GestionPersonnel.data.AuthentificationDTO;
import Presta_Steve.GestionPersonnel.entities.Utilisateur;
import Presta_Steve.GestionPersonnel.interfaces.ISuperAdminService;
import Presta_Steve.GestionPersonnel.services.ActivationCompteService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RequestMapping(path = "superadmin",consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class SuperAdminController {

    private final JwtService jwtService;
    private final ISuperAdminService superAdminService;
    private final AuthenticationManager authenticationManager;
    private final ActivationCompteService activationCompteService;


    //envoyer un nouveau code d'activation du compte
@PostMapping(path = "code")
public ResponseEntity<?> nouveauCode(@PathVariable String email){
  this.activationCompteService.sendNewCode(email);
  return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

//methode pour l'enregistrement d'un superAdmin
@PostMapping(path = "inscription")
public ResponseEntity<?> saveSuperAdmin(@RequestBody Utilisateur superAdmin){
  try
  {
    superAdminService.EnregistrerSuperAdmin(superAdmin);
    return new ResponseEntity<>("SuperAdmin enregistré avec succes...", HttpStatus.CREATED);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
//methode pour l'activation d'un compte superAdmin
@PostMapping(path = "activationCompte")
public ResponseEntity<?> activationCompteSuperAdmin(@RequestBody Map<String, String> activation){
  try{
    this.superAdminService.activation(activation);
    return new ResponseEntity<>("activation reussie...", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<>("une erreur est survenue:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

//methode pour la connexion d'un superAdmin
@PostMapping(path = "connexion")
public  Map<String,String> Connexion(@RequestBody AuthentificationDTO authentificationDTO) {
    final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.UserName(), authentificationDTO.password()));
    if (authenticate.isAuthenticated()) {
        // Si l'authentification est réussie, générez le token JWT
        return this.jwtService.generate(authentificationDTO.UserName());
    }
    return null;
}

//methode pour la modification du mot de passe d'un superAdmin
@PostMapping(path = "resetPassword")
public ResponseEntity<?> resetPassword(@RequestBody AuthentificationDTO authentificationDTO) {
    this.superAdminService.modifierPassword(authentificationDTO.UserName(),authentificationDTO.password());
    return new ResponseEntity<>(HttpStatus.ACCEPTED);

}

}
