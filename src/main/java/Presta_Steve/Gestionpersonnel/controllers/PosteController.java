package Presta_Steve.Gestionpersonnel.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import Presta_Steve.Gestionpersonnel.entities.Poste;
import Presta_Steve.Gestionpersonnel.interfaces.IPosteService;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RequestMapping("/poste")
@RestController
public class PosteController {

  private final IPosteService posteService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void enregistrerPoste(@RequestBody Poste poste) {
    posteService.EnregistrerPoste(poste);
  }

}
