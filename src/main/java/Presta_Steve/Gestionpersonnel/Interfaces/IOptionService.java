package Presta_Steve.Gestionpersonnel.Interfaces;

import Presta_Steve.Gestionpersonnel.entities.Option;

public interface IOptionService {
    void creerOption(Option option);
    
    java.util.List<Option> afficherLesOptions();
    
    void modifierOption(Option option, int idOp);
    
    Option afficherOptionParId(int idOp);
    
    void suppressionOption(int idOp);
}
