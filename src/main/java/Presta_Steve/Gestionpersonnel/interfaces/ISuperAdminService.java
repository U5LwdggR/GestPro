package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.Map;

import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;

public interface ISuperAdminService{
    void EnregistrerSuperAdmin(SuperAdmin superAdmin);
    void connexionSuperAdmin(String emailSup, String mdpSup);
    // SuperAdmin recupererSuperAdminParEmail(String emailSup);
    void activation(Map<String, String> activation);
    SuperAdmin loadUserByUsername(String Email);

}
