package Presta_Steve.Gestionpersonnel.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Correction;

@Repository
public interface CorrectionRepository extends CrudRepository<Correction, Integer>{
	
	Optional<Correction> findByEpreuveId(int id);

}
