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

import Presta_Steve.Gestionpersonnel.Interfaces.IClasseService;
import Presta_Steve.Gestionpersonnel.entities.Classe;
import lombok.AllArgsConstructor;

@RequestMapping(path = "/classe")
@AllArgsConstructor
@RestController
public class ClasseController {

	private final IClasseService classeService;
	
	
	@PostMapping(path = "add")
	public ResponseEntity<?> ajouterClasse(@RequestBody Classe classe){
		try {
			this.classeService.creerClasse(classe);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<?> getAllClasse(){
		try {
			
			return new ResponseEntity<>(this.classeService.afficherLesClasses(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "getById/{id}")
	public ResponseEntity<?> getByIdClasse(@PathVariable int id){
		try {
			
			return new ResponseEntity<>(this.classeService.afficherClasseParId(id),HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "update/{id}")
	public ResponseEntity<?> updateClasse( @RequestBody Classe classe , @PathVariable int id){
		try {
			this.classeService.modifiersession(classe, id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "delete/{id}")
	public ResponseEntity<?> deleteClasse(@PathVariable int id){
		try {
			this.classeService.suppressionclasse(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
