package Presta_Steve.Gestionpersonnel.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Presta_Steve.Gestionpersonnel.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Optional<Admin> findByEmailAd(String emailAd);
}
