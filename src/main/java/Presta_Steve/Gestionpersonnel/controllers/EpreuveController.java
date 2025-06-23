package Presta_Steve.Gestionpersonnel.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Presta_Steve.Gestionpersonnel.Interfaces.IEpreuveService;
import Presta_Steve.Gestionpersonnel.entities.Epreuve;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/epreuve")
public class EpreuveController {

  private final IEpreuveService epreuveService;

  @PostMapping("/ajouter")
  public ResponseEntity<?> ajouterEpreuve( @RequestParam("titre") String titre,
    @RequestParam("destription") String destription,
    @RequestParam("matiere") String matiere,
    @RequestParam("classe") String classe,
    @RequestParam("etablissement") String etablissement,
    @RequestParam("typeExamen") String typeExamen,
    @RequestParam("session") String session,
    @RequestParam("option") String option,
    @RequestParam("sequence") String sequence,
    @RequestParam("fichier") MultipartFile fichier) {
    try {

      epreuveService.creerEpreuve(titre,destription,matiere,classe,etablissement,typeExamen,session,option,sequence,fichier);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  }

  @PutMapping("/modifier-epreuve/{id}")
  public ResponseEntity<?> modifier(@RequestBody Epreuve epreuve, @PathVariable int id, @RequestParam("fichier") MultipartFile fichier ){
    try {
      this.epreuveService.modifierEpreuve(id,epreuve,fichier);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("bloquer-epreuve/{id}")
  public ResponseEntity<?> bloquer(@PathVariable int id){
    try {
      this.epreuveService.BloquerPublicationEpreuve(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("republier-epreuve/{id}")
  public ResponseEntity<?> republier(@PathVariable int id){
    try {
      this.epreuveService.DebloquerPublicationEpreuveBloquer(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("liste-epreuve")
  public ResponseEntity<?> toutesLesEpreuves(){
    try {
      
      return new ResponseEntity<>(this.epreuveService.afficherToutesLesEpreuves(),HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("epreuve/{id}")
  public ResponseEntity<?> epreuveParId(@PathVariable int id){
    try {
      return new ResponseEntity<>(this.epreuveService.afficherEpreuveParId(id) ,HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("supprimer-epreuve/{id}")
  public ResponseEntity<?> supprimerEpreuve(@PathVariable int id){
    try {
      this.epreuveService.SupprimerEpreuve(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/{id}/pdf")
  public ResponseEntity<byte[]> getEpreuvePdf(@PathVariable int id) {
      byte[] pdfContent = epreuveService.getPdfContent(id);
      
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=epreuve.pdf")
          .contentType(MediaType.APPLICATION_PDF)
          .body(pdfContent);
  }

}
