package Presta_Steve.Gestionpersonnel.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;

@Repository
public interface UtilisateursRepository extends CrudRepository<Utilisateurs,Integer>{
  Optional<Utilisateurs> findByEmail(String email);
}
