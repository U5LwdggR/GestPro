package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Presta_Steve.Gestionpersonnel.entities.Epreuve;


public interface IEpreuveService {

  void creerEpreuve(String titre, String destription, String matiere, String classe, String etablissement, String typeExamen, String session, String option, String sequence, MultipartFile  fichier);
  
  Epreuve afficherEpreuveParId(int id);

  List<Epreuve> afficherToutesLesEpreuves();

  void modifierEpreuve(int id, Epreuve epreuve, MultipartFile  fichier);

  void SupprimerEpreuve(int id);

  void BloquerPublicationEpreuve(int id);

  void DebloquerPublicationEpreuveBloquer(int id);
  
  byte[] getPdfContent (int id);
}
