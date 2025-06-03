package Presta_Steve.GestionPersonnel.interfaces;


import Presta_Steve.GestionPersonnel.entities.Utilisateur;

public interface IactivationCompteService {
    void enregistrerActivationCompteAdmin(Utilisateur admin); 
    void enregistrerActivationCompteSuperAdmin(Utilisateur superAdmin); 
    void LireEnfonctionDuCode(String code);
}
