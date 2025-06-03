package Presta_Steve.GestionPersonnel.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.GestionPersonnel.entities.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
  Role findById(int id);

}
