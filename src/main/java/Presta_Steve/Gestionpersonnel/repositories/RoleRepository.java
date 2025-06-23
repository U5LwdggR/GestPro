package Presta_Steve.Gestionpersonnel.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles,Integer>{

}
