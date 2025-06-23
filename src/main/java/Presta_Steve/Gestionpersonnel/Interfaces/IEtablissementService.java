package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Etablissement;

public interface IEtablissementService {
  void ajouterEtablissement(Etablissement etablissement);
  
  List<Etablissement> afficherLesEtablissements();
  
  void modifierEtablissement(Etablissement etablissement, int idE);
  
  Etablissement AfficherEtablissementParId(int idE);

  void supprimerEtablissemnt(int idE);

}
