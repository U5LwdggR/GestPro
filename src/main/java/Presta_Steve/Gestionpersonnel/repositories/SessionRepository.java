package Presta_Steve.Gestionpersonnel.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session,Integer>{

}
