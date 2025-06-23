package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IOptionService;
import Presta_Steve.Gestionpersonnel.entities.Option;
import Presta_Steve.Gestionpersonnel.repositories.OptionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OptionService implements IOptionService {

    //Injection de dependance
  private final OptionRepository optionRepository;

    //creation d'une Option
  public void creerOption(Option Option){
    try {
      this.optionRepository.save(Option);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'une Option");
    }
  }

  //afficher toutes les Options
  public List<Option> afficherLesOptions(){
    try {
      List<Option> OptionCherche = (List<Option>) this.optionRepository.findAll();
      if (OptionCherche.isEmpty()) {
        System.out.println("aucune Option n'a ete retouvee");
      }
      return OptionCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des Options: "+e);
    }
  }

  //afficher les Options en fonction de leur id
  public Option afficherOptionParId(int idOp){
    try {
      Optional<Option> OptionCherhe = this.optionRepository.findById(idOp);
      if (OptionCherhe.isEmpty()) {
        throw new RuntimeException("cette Option n'existe pas dans le systeme");
      }
      Option OptionTrouve = OptionCherhe.get();
      return OptionTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage de l'Option: "+e);
    }
  }

  //modifier un Option
  public void modifierOption(Option Option, int idOp){
    try {
      Optional<Option> OptionCherhe = this.optionRepository.findById(idOp);
      if (OptionCherhe.isEmpty()) {
        throw new RuntimeException("cette Option n'existe pas dans le systeme");
      }
      Option OptionTrouve = OptionCherhe.get();
      OptionTrouve.setNomOp(Option.getNomOp());
      this.optionRepository.save(OptionTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de l'Option: "+e);
    }
  }

  //supprimer un Option
  public void suppressionOption(int idOp){
    try {
      Optional<Option> OptionCherhe = this.optionRepository.findById(idOp);
      if (OptionCherhe.isEmpty()) {
        throw new RuntimeException("cette Option n'existe pas dans le systeme");
      }
      this.optionRepository.deleteById(idOp);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de l'Option: "+e);
    }
  }
}
