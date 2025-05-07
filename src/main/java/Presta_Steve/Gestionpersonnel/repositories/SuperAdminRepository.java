package Presta_Steve.Gestionpersonnel.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;


public interface SuperAdminRepository extends CrudRepository<SuperAdmin, Integer> {
    //cette methode permettra de verifier si un utilisateur existe deja avant de l'enregistrer au moment de l'inscription
    Optional<SuperAdmin> findByEmailSup(String emailSup);

}

