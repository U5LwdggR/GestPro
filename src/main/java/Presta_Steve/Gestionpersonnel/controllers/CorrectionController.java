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

import Presta_Steve.Gestionpersonnel.entities.Correction;
import Presta_Steve.Gestionpersonnel.entities.Epreuve;
import Presta_Steve.Gestionpersonnel.services.CorrectionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "correction")
@AllArgsConstructor
public class CorrectionController {

	private final CorrectionService correctionService;
	
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
	    @RequestParam("fichier") MultipartFile fichier,
	    @RequestParam("id") int id) {
	    try {

	    	correctionService.creerEpreuve(titre,destription,matiere,classe,etablissement,typeExamen,session,option,sequence,fichier,id);
	      return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	  }

	  @PutMapping("/modifier-epreuve/{id}")
	  public ResponseEntity<?> modifier(@RequestBody Correction epreuve, @PathVariable int id, @RequestParam("fichier") MultipartFile fichier ){
	    try {
	      this.correctionService.modifierEpreuve(id,epreuve,fichier);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	/*  @PutMapping("bloquer-epreuve/{id}")
	  public ResponseEntity<?> bloquer(@PathVariable int id){
	    try {
	      this.correctionService.BloquerPublicationEpreuve(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("republier-epreuve/{id}")
	  public ResponseEntity<?> republier(@PathVariable int id){
	    try {
	      this.correctionService.DebloquerPublicationEpreuveBloquer(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }*/

	  @GetMapping("liste-epreuve")
	  public ResponseEntity<?> toutesLesEpreuves(){
	    try {
	      
	      return new ResponseEntity<>(this.correctionService.afficherToutesLesEpreuves(),HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("epreuve/{id}")
	  public ResponseEntity<?> epreuveParId(@PathVariable int id){
	    try {
	      return new ResponseEntity<>(this.correctionService.afficherEpreuveParId(id) ,HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("supprimer-epreuve/{id}")
	  public ResponseEntity<?> supprimerEpreuve(@PathVariable int id){
	    try {
	      this.correctionService.SupprimerEpreuve(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("erreur: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/{id}/pdf")
	  public ResponseEntity<byte[]> getEpreuvePdf(@PathVariable int id) {
	      byte[] pdfContent = correctionService.getPdfContent(id);
	      
	      return ResponseEntity.ok()
	          .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=epreuve.pdf")
	          .contentType(MediaType.APPLICATION_PDF)
	          .body(pdfContent);
	  }
}
