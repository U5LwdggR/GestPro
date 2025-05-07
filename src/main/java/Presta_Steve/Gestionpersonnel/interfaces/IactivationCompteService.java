package Presta_Steve.Gestionpersonnel.interfaces;


import Presta_Steve.Gestionpersonnel.entities.Admin;
import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;

public interface IactivationCompteService {
    void enregistrerActivationCompteAdmin(Admin admin); 
    void enregistrerActivationCompteSuperAdmin(SuperAdmin superAdmin); 
    void LireEnfonctionDuCode(String code);
}
