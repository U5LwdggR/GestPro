package Presta_Steve.GestionPersonnel.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.GestionPersonnel.entities.ActivationCompte;


@Repository
public interface ActivationCompteRepository extends CrudRepository<ActivationCompte, Integer> {
    Optional<ActivationCompte> findByCode(String code);
}
