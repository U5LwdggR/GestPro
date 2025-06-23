package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IEtablissementService;
import Presta_Steve.Gestionpersonnel.entities.Etablissement;
import Presta_Steve.Gestionpersonnel.repositories.EtablissementRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EtablissementService implements IEtablissementService {
  //Injection de dependance
  private final EtablissementRepository etablissementRepository;

  //creer un etablissemnt
  public void ajouterEtablissement(Etablissement etablissement){
    try {
      etablissementRepository.save(etablissement);
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'ajout de l'etablissement"+e.getMessage());
    }
  }
  
  //recuperer tous les etablissemnts
  public List<Etablissement> afficherLesEtablissements(){
    try {
      List<Etablissement> etablissements = (List<Etablissement>) this.etablissementRepository.findAll();
      if (etablissements.isEmpty()) {
        System.out.println("aucun etablissement retrouve");
      }
      return etablissements;
    } catch (Exception e) {
      throw new RuntimeException("erreur lors de la recuperation des etablissements: "+e.getMessage());
    }
  }
  
  //modifier un etablissemnt
  public void modifierEtablissement(Etablissement etablissement, int idE){
    try{
    Optional<Etablissement> rechercheEtablissemnt = this.etablissementRepository.findById(idE);
    if (rechercheEtablissemnt.isEmpty()){
      throw new RuntimeException("cet etablissemnt n'existe pas");
    }
    Etablissement etablissementTrouve = rechercheEtablissemnt.get();
    etablissementTrouve.setNomE(etablissement.getNomE());
    this.etablissementRepository.save(etablissementTrouve);
  }catch(Exception e){
    throw new RuntimeException("erreur lors de la modification de l'etablissement: "+e);
  }
  }

  //recuperation d'un etablissemnt par son id
  public Etablissement AfficherEtablissementParId(int idE){
    try {
      Optional<Etablissement> rechercheEtablissemnt = this.etablissementRepository.findById(idE);
      if (rechercheEtablissemnt.isEmpty()){
        throw new RuntimeException("cet etablissemnt n'existe pas");
      }
      return rechercheEtablissemnt.get();
    } catch (Exception e) {
      throw new RuntimeException("erreur lors de la recuperation de cet etablissement: "+e);
    }
  }

//suppression d'un etablissemnt
  public void supprimerEtablissemnt(int idE){
    try {
      Optional<Etablissement> rechercheEtablissemnt = this.etablissementRepository.findById(idE);
      if (rechercheEtablissemnt.isEmpty()){
        throw new RuntimeException("cet etablissemnt n'existe pas");
      }
      this.etablissementRepository.deleteById(idE);
    } catch (Exception e) {
      throw new RuntimeException("erreur lors de la suppression de cet etablissement: "+e);
    }
  }
}
