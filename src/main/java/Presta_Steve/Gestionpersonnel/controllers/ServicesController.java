package Presta_Steve.Gestionpersonnel.controllers;

import java.util.List;

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

import Presta_Steve.Gestionpersonnel.entities.Services;
import Presta_Steve.Gestionpersonnel.services.ServicesService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "services")
public class ServicesController {

  //Injecter le service
  private ServicesService servicesService;
  
  @PostMapping(path = "ajouterService")
  public ResponseEntity<?> addService(@RequestBody Services services) {
    servicesService.addService(services);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @DeleteMapping(path = "supprimer/{idSer}")
  public ResponseEntity<?> deleteService(@PathVariable int idSer) {
    servicesService.deleteService(idSer);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @GetMapping(path = "afficher/{idSer}")
  public ResponseEntity<Services> getServiceById(@PathVariable int idSer) {
    return  new ResponseEntity<>(servicesService.getServiceById(idSer), HttpStatus.OK);
  }
  
  @GetMapping(path = "tousAfficher")
  public ResponseEntity<List<Services>> getAllServices() {
    return new ResponseEntity<>(servicesService.getAllServices(), HttpStatus.OK);
  }
  
  @PutMapping(path = "modifier/{idSer}")
  public ResponseEntity<?> updateService(@RequestBody Services service, @PathVariable int idSer) {
    servicesService.updateService(service, idSer);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
