package Presta_Steve.GestionPersonnel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.Role;
import Presta_Steve.GestionPersonnel.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleService {

  private final RoleRepository roleRepository;

  public Role createRole(Role role) {
    return roleRepository.save(role);
  }

  public List<Role> getAllRoles() {
    Iterable<Role> Role = roleRepository.findAll();
    List<Role> role_list = new ArrayList<>();
    Role.forEach(role_list::add);
    return role_list;
  }

  public Role getRoleById(int id) {
    return roleRepository.findById(id);
  }

  public Role updateRole(int id, Role updatedRole) {
    Role role = roleRepository.findById(id);
    if (role == null) {
      throw new RuntimeException("le role que vous voulez modifier n'existe pas");
    }
    role.setLibelle(updatedRole.getLibelle());
    return roleRepository.save(role);
  }


  public void deleteRole(int id) {
    if (roleRepository.existsById(id)) {
      roleRepository.deleteById(id);
    } else {
      throw new RuntimeException("le role n'existe pas " + id);
    }
  }

}
