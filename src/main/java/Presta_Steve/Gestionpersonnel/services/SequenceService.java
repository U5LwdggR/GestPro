package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.ISequenceService;
import Presta_Steve.Gestionpersonnel.entities.Sequence;
import Presta_Steve.Gestionpersonnel.repositories.SequenceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SequenceService implements ISequenceService {

    //Injection de dependance
    //on utilise le repository pour acceder a la base de donnees
  private final SequenceRepository sequenceRepository;

    //creation d'une sequence
  public void creersequence(Sequence sequence){
    try {
      this.sequenceRepository.save(sequence);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'une sequence");
    }
  }

  //afficher toutes les sequences
  public List<Sequence> afficherLessequences(){
    try {
      List<Sequence> sequenceCherche = (List<Sequence>) this.sequenceRepository.findAll();
      if (sequenceCherche.isEmpty()) {
        System.out.println("aucune sequence n'a ete retouvee");
      }
      return sequenceCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des sequences: "+e);
    }
  }

  //afficher les sequences en fonction de leur id
  public Sequence affichersequenceParId(int idS){
    try {
      Optional<Sequence> sequenceCherhe = this.sequenceRepository.findById(idS);
      if (sequenceCherhe.isEmpty()) {
        throw new RuntimeException("cette sequence n'existe pas dans le systeme");
      }
      Sequence sequenceTrouve = sequenceCherhe.get();
      return sequenceTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage de la sequence: "+e);
    }
  }

  //modifier un sequence
  public void modifiersequence(Sequence sequence, int idS){
    try {
      Optional<Sequence> sequenceCherhe = this.sequenceRepository.findById(idS);
      if (sequenceCherhe.isEmpty()) {
        throw new RuntimeException("cette sequence n'existe pas dans le systeme");
      }
      Sequence sequenceTrouve = sequenceCherhe.get();
      sequenceTrouve.setNumSeq(sequence.getNumSeq());
      this.sequenceRepository.save(sequenceTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de la sequence: "+e);
    }
  }

  //supprimer un sequence
  public void suppressionsequence(int idS){
    try {
      Optional<Sequence> sequenceCherhe = this.sequenceRepository.findById(idS);
      if (sequenceCherhe.isEmpty()) {
        throw new RuntimeException("cette sequence n'existe pas dans le systeme");
      }
      this.sequenceRepository.deleteById(idS);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de la sequence: "+e);
    }
  }

}
