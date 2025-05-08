package Presta_Steve.Gestionpersonnel.interfaces;


import Presta_Steve.Gestionpersonnel.entities.Utilisateur;

public interface IactivationCompteService {
    void enregistrerActivationCompteAdmin(Utilisateur admin); 
    void enregistrerActivationCompteSuperAdmin(Utilisateur superAdmin); 
    void LireEnfonctionDuCode(String code);
}
