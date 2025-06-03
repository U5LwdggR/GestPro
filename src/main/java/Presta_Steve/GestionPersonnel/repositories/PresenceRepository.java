package Presta_Steve.GestionPersonnel.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Presta_Steve.GestionPersonnel.entities.Presence;

public interface PresenceRepository extends CrudRepository<Presence, Integer> {
    Optional<Presence> findByIdPer(int idPer);
    Long countByIdPer(int idPer);
    Optional<Presence> findByIdPerAndPeriode(int idPer, LocalDate periode);
}
