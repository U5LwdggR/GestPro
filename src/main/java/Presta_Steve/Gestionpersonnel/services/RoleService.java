package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.IRoleService;
import Presta_Steve.Gestionpersonnel.entities.Roles;
import Presta_Steve.Gestionpersonnel.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {

    //Injection de dependance
    //on utilise le constructeur pour injecter le repository
    //pour pouvoir l'utiliser dans les methodes du service
  private final RoleRepository roleRepository;

    //creation d'une role
  public void creerrole(Roles role){
    try {
      this.roleRepository.save(role);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation du role");
    }
  }

  //afficher toutes les roles
  public List<Roles> afficherLesroles(){
    try {
      List<Roles> roleCherche = (List<Roles>) this.roleRepository.findAll();
      if (roleCherche.isEmpty()) {
        System.out.println("aucun role n'a ete retouve");
      }
      return roleCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des roles: "+e);
    }
  }

  //afficher les roles en fonction de leur id
  public Roles afficherroleParId(int idR){
    try {
      Optional<Roles> roleCherhe = this.roleRepository.findById(idR);
      if (roleCherhe.isEmpty()) {
        throw new RuntimeException("ce role n'existe pas dans le systeme");
      }
      Roles roleTrouve = roleCherhe.get();
      return roleTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage du role: "+e);
    }
  }

  //modifier un role
  public void modifierrole(Roles role, int idR){
    try {
      Optional<Roles> roleCherhe = this.roleRepository.findById(idR);
      if (roleCherhe.isEmpty()) {
        throw new RuntimeException("cette role n'existe pas dans le systeme");
      }
      Roles roleTrouve = roleCherhe.get();
      roleTrouve.setLibelle(role.getLibelle());
      this.roleRepository.save(roleTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de la role: "+e);
    }
  }

  //supprimer un role
  public void suppressionrole(int idR){
    try {
      Optional<Roles> roleCherhe = this.roleRepository.findById(idR);
      if (roleCherhe.isEmpty()) {
        throw new RuntimeException("cette role n'existe pas dans le systeme");
      }
      this.roleRepository.deleteById(idR);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de la role: "+e);
    }
  }

  
}
