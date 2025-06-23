package Presta_Steve.Gestionpersonnel.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Classe;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Integer>{

}
