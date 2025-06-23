package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Roles;

public interface IRoleService {
    void creerrole(Roles role);

    List<Roles> afficherLesroles();

    Roles afficherroleParId(int idR);

    void modifierrole(Roles role, int idR);

    void suppressionrole(int idR);
}
