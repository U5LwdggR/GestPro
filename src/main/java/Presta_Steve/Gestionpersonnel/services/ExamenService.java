package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IExamenService;
import Presta_Steve.Gestionpersonnel.entities.Examen;
import Presta_Steve.Gestionpersonnel.repositories.ExamenRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ExamenService implements IExamenService {
  private final ExamenRepository examenRepository;

  //creation d'un examen
  public void creerExamen(Examen examen){
    try {
      this.examenRepository.save(examen);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'un examen");
    }
  }

  //afficher tous les examens
  public List<Examen> afficherLesExamens(){
    try {
      List<Examen> examenCherche = (List<Examen>) this.examenRepository.findAll();
      if (examenCherche.isEmpty()) {
        System.out.println("aucun examen n'a ete retouve");
      }
      return examenCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des examens: "+e);
    }
  }

  //afficher les examens en fonction de leur id
  public Examen afficherExamenParId(int idEx){
    try {
      Optional<Examen> examenCherhe = this.examenRepository.findById(idEx);
      if (examenCherhe.isEmpty()) {
        throw new RuntimeException("cet examen n'existe pas dans le systeme");
      }
      Examen examenTrouve = examenCherhe.get();
      return examenTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage de l'examen: "+e);
    }
  }

  //modifier un examen
  public void modifierExamen(Examen examen, int idEx){
    try {
      Optional<Examen> examenCherhe = this.examenRepository.findById(idEx);
      if (examenCherhe.isEmpty()) {
        throw new RuntimeException("cet examen n'existe pas dans le systeme");
      }
      Examen examenTrouve = examenCherhe.get();
      examenTrouve.setTypeEx(examen.getTypeEx());
      this.examenRepository.save(examenTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de l'examen: "+e);
    }
  }

  //supprimer un examen
  public void suppressionExamen(int idEx){
    try {
      Optional<Examen> examenCherhe = this.examenRepository.findById(idEx);
      if (examenCherhe.isEmpty()) {
        throw new RuntimeException("cet examen n'existe pas dans le systeme");
      }
      this.examenRepository.deleteById(idEx);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de l'examen: "+e);
    }
  }
}
