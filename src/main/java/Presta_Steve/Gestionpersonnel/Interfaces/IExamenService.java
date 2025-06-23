package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Examen;

public interface IExamenService {
    void creerExamen(Examen examen);
    
    List<Examen> afficherLesExamens();
    
    void modifierExamen(Examen examen, int idE);
    
    Examen afficherExamenParId(int idE);
    
    void suppressionExamen(int idE);
}
