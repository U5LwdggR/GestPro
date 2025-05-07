package Presta_Steve.Gestionpersonnel.services;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Role;
import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;
import Presta_Steve.Gestionpersonnel.interfaces.ISuperAdminService;
import Presta_Steve.Gestionpersonnel.repositories.RoleRepository;
import Presta_Steve.Gestionpersonnel.repositories.SuperAdminRepository;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@Service
public class SuperAdminService implements ISuperAdminService,UserDetailsService {

    private final SuperAdminRepository superAdminRepository;

    private final BCryptPasswordEncoder PasswordEncoder;

    private final ActivationCompteService activationCompteService;

    private final RoleRepository roleRepository;



    //methode pour l'enregistrement d'un super admin
    public void EnregistrerSuperAdmin(SuperAdmin superAdmin) {
        //verifier si l'email contient @
        if (!superAdmin.getEmailSup().contains("@")) {
            throw new RuntimeException("votre email doit contenir '@'");
        }
        // ensuite verifier si l'email contient .
        if (!superAdmin.getEmailSup().contains(".")) {
            throw new RuntimeException("votre email doit contenir '.'");
        }

        //verifier si le super admin existe deja en fonction de son email
        Optional<SuperAdmin> findSuperAdmin = this.superAdminRepository.findByEmailSup(superAdmin.getEmailSup());

        if (findSuperAdmin.isPresent()) {
            throw new RuntimeException("Veuillez choisir un autre email le compte semble deja exister");
        }
        //cryptage du mot de passe du super admin
        superAdmin.setMdpSup(this.PasswordEncoder.encode(superAdmin.getMdpSup()));

        //j'associe le role a  parce que je sais que le role "SUPER_ADMIN" a pour id 1 dans la bd
        Role roleExiste = this.roleRepository.findById(1);
        if (roleExiste == null) {
            throw new RuntimeException("le role n'existe pas");
        }
        superAdmin.setRole(roleExiste.getLibelle());

        //enregistrement du super admin
        SuperAdmin superAdmin2 = this.superAdminRepository.save(superAdmin);
        
        //enregistrement de l'activation du compte super admin
        this.activationCompteService.enregistrerActivationCompteSuperAdmin(superAdmin2);
    }

    //methode pour la verification de l'existance du super admin dans la bd
    public void connexionSuperAdmin(String emailSup, String mdpSup) {
        // Récupérer le SuperAdmin par email
        Optional<SuperAdmin> findSuperAdmin = this.superAdminRepository.findByEmailSup(emailSup);
    
        // Vérifier si le SuperAdmin existe
        if (findSuperAdmin.isEmpty()) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    
        // Comparer le mot de passe brut avec le mot de passe haché
        SuperAdmin superAdmin = findSuperAdmin.get();
        if (!this.PasswordEncoder.matches(mdpSup, superAdmin.getMdpSup())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    }

    public void activation(Map<String, String> activation) {
    ActivationCompte activationCompte = this.activationCompteService.LireEnfonctionDuCode(activation.get("code"));
    if (Instant.now().isAfter(activationCompte.getExpirationDate())) {
        throw new RuntimeException("le code a expiré");
    
    }
    SuperAdmin SuperAdminActiver = this.superAdminRepository.findById(activationCompte.getIdSuperAdmin()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
    SuperAdminActiver.setActif(true);
    this.superAdminRepository.save(SuperAdminActiver);
}
    //methode pour la recuperation d'un super admin par son id
    @Override
    public SuperAdmin loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SuperAdmin> chercherParEmail = this.superAdminRepository.findByEmailSup(username);
        if (chercherParEmail.isEmpty()) {
            throw new RuntimeException("Aucun utilisateur ne correspond a cette email");
        }
        return chercherParEmail.get();
    }

}
