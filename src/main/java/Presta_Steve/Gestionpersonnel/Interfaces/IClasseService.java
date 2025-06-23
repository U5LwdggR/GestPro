package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Classe;

public interface IClasseService {

	void creerClasse(Classe classe);
	
	List<Classe> afficherLesClasses();
	
	Classe afficherClasseParId(int idM);
	
	void modifiersession(Classe classe, int idM);
	
	void suppressionclasse(int idM);
}
