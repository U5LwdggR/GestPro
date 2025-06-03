package Presta_Steve.GestionPersonnel.repositories;




import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Presta_Steve.GestionPersonnel.entities.Utilisateur;

@Repository
public interface AdminRepository extends CrudRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmailSup(String emailAd);
}
