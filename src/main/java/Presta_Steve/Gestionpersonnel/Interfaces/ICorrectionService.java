package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Presta_Steve.Gestionpersonnel.entities.Correction;
import Presta_Steve.Gestionpersonnel.entities.Epreuve;

public interface ICorrectionService {
	
	void creerEpreuve(String titre, String destription, String matiere, String classe, String etablissement, String typeExamen, String session, String option, String sequence, MultipartFile  fichier,int id);
	
	void SupprimerEpreuve(int id);
	
	void modifierEpreuve(int id, Correction epreuve, MultipartFile  fichier);
	
	List<Correction> afficherToutesLesEpreuves();
	
	Correction afficherEpreuveParId(int id);
	
	byte[] getPdfContent (int id);

}
