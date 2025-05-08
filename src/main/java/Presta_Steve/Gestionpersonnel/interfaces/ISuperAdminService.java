package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.Map;

import Presta_Steve.Gestionpersonnel.entities.Utilisateur;

public interface ISuperAdminService{
    void EnregistrerSuperAdmin(Utilisateur superAdmin);
    void connexionSuperAdmin(String emailSup, String mdpSup);
    // SuperAdmin recupererSuperAdminParEmail(String emailSup);
    void activation(Map<String, String> activation);
    Utilisateur loadUserByUsername(String Email);

}
