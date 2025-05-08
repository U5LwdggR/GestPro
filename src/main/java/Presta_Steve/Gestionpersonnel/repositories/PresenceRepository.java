package Presta_Steve.Gestionpersonnel.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Presta_Steve.Gestionpersonnel.entities.Presence;

public interface PresenceRepository extends CrudRepository<Presence, Integer> {
    Optional<Presence> findByIdPer(int idPer);
    Long countByIdPer(int idPer);
}
