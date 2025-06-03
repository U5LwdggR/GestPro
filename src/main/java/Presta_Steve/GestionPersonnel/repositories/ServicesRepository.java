package Presta_Steve.GestionPersonnel.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.GestionPersonnel.entities.Services;

@Repository
public interface ServicesRepository extends CrudRepository<Services, Integer> {
  Optional<Services> findById(int idSer); // Optional pour gérer les cas où l'ID n'existe pas

}
