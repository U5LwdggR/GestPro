package Presta_Steve.GestionPersonnel.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Presta_Steve.GestionPersonnel.entities.Utilisateur;


public interface SuperAdminRepository extends CrudRepository<Utilisateur, Integer> {
    //cette methode permettra de verifier si un utilisateur existe deja avant de l'enregistrer au moment de l'inscription
    Optional<Utilisateur> findByEmailSup(String emailSup);

}

