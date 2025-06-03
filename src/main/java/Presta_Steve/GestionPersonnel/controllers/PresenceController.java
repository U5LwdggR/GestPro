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

import Presta_Steve.GestionPersonnel.entities.Presence;
import Presta_Steve.GestionPersonnel.interfaces.IPresenceService;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@RequestMapping(path = "presence")
@RestController
public class PresenceController {

  private final IPresenceService presenceService;

  @PostMapping(path = "marquerPresence")
  public ResponseEntity<?> ajouterPresence(@RequestBody  Presence presence) {
    try {
      presenceService.ajouterPresence(presence.getIdPer());
      return new ResponseEntity<>(HttpStatus.CREATED);
      
    } catch (Exception e) {
      return new ResponseEntity<>("erreur lors de la creation:"+ e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
  }

  @GetMapping(path = "toutes-les-presences")
  public ResponseEntity<?> postMethodName() {
      try{
        return  new ResponseEntity<>(presenceService.AfficherToutesLesPresences(), HttpStatus.ACCEPTED);
      }catch(Exception e){
        return new ResponseEntity<>("erreur lors de la recuperation des presences: "+e.getMessage(),HttpStatus.BAD_REQUEST );
      }
  }

  @DeleteMapping(path = "supprimer/{id}")
    public ResponseEntity<?> deletePresencee(@PathVariable int id) {
    try{
      presenceService.SupprimerPresence(id);
      return  new ResponseEntity<>("supprimer avec succes...", HttpStatus.ACCEPTED);
    }catch(Exception e){
      return new ResponseEntity<>("erreur lors de la suppression de la presence: "+e.getMessage(),HttpStatus.BAD_REQUEST );
    }
  }

  @PutMapping(path = "modifier/{id}")
  public ResponseEntity<?> updatePresence(@RequestBody Presence presence ,@PathVariable int id) {
    try{
      presenceService.modifierPresence(presence,id);
      return  new ResponseEntity<>("modifier avec succes...", HttpStatus.ACCEPTED);
    }catch(Exception e){
      return new ResponseEntity<>("erreur lors de la modifier de la presence: "+e.getMessage(),HttpStatus.BAD_REQUEST );
    }
  }
}
