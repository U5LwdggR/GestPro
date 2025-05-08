package Presta_Steve.Gestionpersonnel.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.Gestionpersonnel.entities.Utilisateur;

@Repository
public interface AdminRepository extends CrudRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmailSup(String emailAd);
}
