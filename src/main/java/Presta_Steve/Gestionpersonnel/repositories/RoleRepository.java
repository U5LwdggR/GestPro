package Presta_Steve.Gestionpersonnel.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
  Role findById(int id);

}
