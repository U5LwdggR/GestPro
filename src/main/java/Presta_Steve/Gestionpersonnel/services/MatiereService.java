package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IMatiereService;
import Presta_Steve.Gestionpersonnel.entities.Matiere;
import Presta_Steve.Gestionpersonnel.repositories.MatiereRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MatiereService implements IMatiereService {

  //Injection de dependance
private final MatiereRepository matiereRepository;

  //creation d'une Matiere
  public void creerMatiere(Matiere Matiere){
    try {
      this.matiereRepository.save(Matiere);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'une Matiere");
    }
  }

  //afficher toutes les Matieres
  public List<Matiere> afficherLesMatieres(){
    try {
      List<Matiere> MatiereCherche = (List<Matiere>) this.matiereRepository.findAll();
      if (MatiereCherche.isEmpty()) {
        System.out.println("aucune Matiere n'a ete retouvee");
      }
      return MatiereCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des Matieres: "+e);
    }
  }

  //afficher les Matieres en fonction de leur id
  public Matiere afficherMatiereParId(int idM){
    try {
      Optional<Matiere> MatiereCherhe = this.matiereRepository.findById(idM);
      if (MatiereCherhe.isEmpty()) {
        throw new RuntimeException("cette Matiere n'existe pas dans le systeme");
      }
      Matiere MatiereTrouve = MatiereCherhe.get();
      return MatiereTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage de la Matiere: "+e);
    }
  }

  //modifier un Matiere
  public void modifierMatiere(Matiere Matiere, int idM){
    try {
      Optional<Matiere> MatiereCherhe = this.matiereRepository.findById(idM);
      if (MatiereCherhe.isEmpty()) {
        throw new RuntimeException("cette Matiere n'existe pas dans le systeme");
      }
      Matiere MatiereTrouve = MatiereCherhe.get();
      MatiereTrouve.setNomM(Matiere.getNomM());
      this.matiereRepository.save(MatiereTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de la Matiere: "+e);
    }
  }

  //supprimer un Matiere
  public void suppressionMatiere(int idM){
    try {
      Optional<Matiere> MatiereCherhe = this.matiereRepository.findById(idM);
      if (MatiereCherhe.isEmpty()) {
        throw new RuntimeException("cette Matiere n'existe pas dans le systeme");
      }
      this.matiereRepository.deleteById(idM);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de la Matiere: "+e);
    }
  }
}
