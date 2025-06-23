package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IClasseService;
import Presta_Steve.Gestionpersonnel.entities.Classe;
import Presta_Steve.Gestionpersonnel.repositories.ClasseRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClasseService implements IClasseService{

	private final ClasseRepository classeRepository;
	
	   //creation d'une Classe
	  public void creerClasse(Classe classe){
	    try {
	      this.classeRepository.save(classe);
	    } catch (Exception e) {
	      throw new RuntimeException("erreur a la creation d'une classe");
	    }
	  }

	  //afficher toutes les Classes
	  public List<Classe> afficherLesClasses(){
	    try {
	      List<Classe> classeCherche = (List<Classe>) this.classeRepository.findAll();
	      if (classeCherche.isEmpty()) {
	        System.out.println("aucune classe n'a ete retouvee");
	      }
	      return classeCherche;
	    } catch (Exception e) {
	      throw new RuntimeException("erreur a l'affichage des classes: "+e);
	    }
	  }

	  //afficher les classes en fonction de leur id
	  public Classe afficherClasseParId(int idM){
	    try {
	      Optional<Classe> classeCherhe = this.classeRepository.findById(idM);
	      if (classeCherhe.isEmpty()) {
	        throw new RuntimeException("cette classe n'existe pas dans le systeme");
	      }
	      Classe classeTrouve = classeCherhe.get();
	      return classeTrouve;
	    } catch (Exception e) {
	      throw new RuntimeException("erreur a l'affichage de la classe: "+e);
	    }
	  }

	  //modifier une Classe
	  public void modifiersession(Classe classe, int idM){
	    try {
	      Optional<Classe> sessionCherhe = this.classeRepository.findById(idM);
	      if (sessionCherhe.isEmpty()) {
	        throw new RuntimeException("cette classe n'existe pas dans le systeme");
	      }
	      Classe sessionTrouve = sessionCherhe.get();
	      sessionTrouve.setLibelle(classe.getLibelle());
	      this.classeRepository.save(sessionTrouve);
	    } catch (Exception e) {
	      throw new RuntimeException("erreur a la modification de la classe: "+e);
	    }
	  }

	  //supprimer une Classe
	  public void suppressionclasse(int idM){
	    try {
	      Optional<Classe> classeCherhe = this.classeRepository.findById(idM);
	      if (classeCherhe.isEmpty()) {
	        throw new RuntimeException("cette classe n'existe pas dans le systeme");
	      }
	      this.classeRepository.deleteById(idM);
	    } catch (Exception e) {
	      throw new RuntimeException("erreur a la suppression de la classe: "+e);
	    }
	  }
}
