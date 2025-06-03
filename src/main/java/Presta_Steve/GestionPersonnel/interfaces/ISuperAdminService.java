package Presta_Steve.GestionPersonnel.interfaces;

import java.util.Map;

import Presta_Steve.GestionPersonnel.entities.Utilisateur;

public interface ISuperAdminService{
    void EnregistrerSuperAdmin(Utilisateur superAdmin);
    void connexionSuperAdmin(String emailSup, String mdpSup);
    // SuperAdmin recupererSuperAdminParEmail(String emailSup);
    void activation(Map<String, String> activation);
    Utilisateur loadUserByUsername(String Email);
    void modifierPassword(String email, String password);
}
