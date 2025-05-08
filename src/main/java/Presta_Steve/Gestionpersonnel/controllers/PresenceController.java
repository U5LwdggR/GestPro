package Presta_Steve.Gestionpersonnel.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Presta_Steve.Gestionpersonnel.entities.Presence;
import Presta_Steve.Gestionpersonnel.interfaces.IPresenceService;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@RequestMapping(path = "presence")
@RestController
public class PresenceController {

  private final IPresenceService presenceService;

  @PostMapping(path = "marquerPresence")
  public ResponseEntity<?> ajouterPresence(@RequestBody Presence presence) {
    try {
      presenceService.ajouterPresence(presence.getIdPer());
      return new ResponseEntity<>("creer avec succes...",HttpStatus.CREATED);
      
    } catch (Exception e) {
      return new ResponseEntity<>("erreur lors de la creation:"+ e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
  }
}
