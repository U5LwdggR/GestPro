package Presta_Steve.Gestionpersonnel.controllers;

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

import Presta_Steve.Gestionpersonnel.Interfaces.ISessionService;
import Presta_Steve.Gestionpersonnel.entities.Session;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/session")
public class SessionController {

  private final ISessionService sessionService;

  @PostMapping("/session")
  public ResponseEntity<?> createSession(@RequestBody Session session) {
    try {
      this.sessionService.creersession(session);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/Lister-les-sessions")
  public ResponseEntity<?> getAllSessions() {
    try {
      return new ResponseEntity<>(this.sessionService.afficherLessessions(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/session/{id}")
  public ResponseEntity<?> getSessionById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(this.sessionService.affichersessionParId(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/modifier-session/{id}")
  public ResponseEntity<?> updateSession(@PathVariable int id, @RequestBody Session session) {
    try {
      this.sessionService.modifiersession(session, id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/supprimer-session/{id}")
  public ResponseEntity<?> deleteSession(@PathVariable int id) {
    try {
      this.sessionService.suppressionsession(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
