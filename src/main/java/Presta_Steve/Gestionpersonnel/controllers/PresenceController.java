package Presta_Steve.Gestionpersonnel.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Presta_Steve.Gestionpersonnel.entities.Presence;
import Presta_Steve.Gestionpersonnel.interfaces.IPresenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;



@AllArgsConstructor
@RequestMapping("presence")
@RestController
public class PresenceController {

  private final IPresenceService presenceService;

  @PostMapping
  public ResponseEntity<?> ajouterPresence(@RequestBody Presence presence) {
    try {
      presenceService.ajouterPresence(presence);
      return new ResponseEntity<>("creer avec succes...",HttpStatus.CREATED);
      
    } catch (Exception e) {
      return new ResponseEntity<>("erreur lors de la creation:"+ e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
  }
}
