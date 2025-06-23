package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Matiere;

public interface IMatiereService {
    void creerMatiere(Matiere matiere);
    
    List<Matiere> afficherLesMatieres();
    
    void modifierMatiere(Matiere matiere, int idM);
    
    Matiere afficherMatiereParId(int idM);
    
    void suppressionMatiere(int idM);

}
