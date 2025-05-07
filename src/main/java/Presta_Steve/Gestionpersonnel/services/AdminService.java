package Presta_Steve.Gestionpersonnel.services;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Admin;
import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;
import Presta_Steve.Gestionpersonnel.interfaces.IAdminService;
import Presta_Steve.Gestionpersonnel.repositories.AdminRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private BCryptPasswordEncoder PasswordEncoder;
    private final ActivationCompteService activationCompteService;
    
    //private final TokenService tokenService;


    public void ajouterAdmin(Admin admin) {


    //verifier si l'email contient @
        if (!admin.getEmailAd().contains("@")) {
            throw new RuntimeException("votre email doit contenir '@'");
        }
        // ensuite verifier si l'email contient .
        if (!admin.getEmailAd().contains(".")) {
            throw new RuntimeException("votre email doit contenir '.'");
        }
        //verifier si le super admin existe deja en fonction de son email
        Optional<Admin> findAdmin = this.adminRepository.findByEmailAd(admin.getEmailAd());
        if (findAdmin.isPresent()) {
            throw new RuntimeException("Veuillez choisir un autre email le compte semble deja exister");
        }
        
        //cryptage du mot de passe du super admin
        admin.setMdpAd(this.PasswordEncoder.encode(admin.getMdpAd()));
        SuperAdmin idAdmin = (SuperAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        admin.setIdSup(idAdmin.getIdSup());

        //enregistrement du super admin
        Admin admin2 = this.adminRepository.save(admin);
        
        //enregistrement de l'activation du compte super admin
        this.activationCompteService.enregistrerActivationCompteAdmin(admin2);
    }


    public void connexionAdmin(String emailAd, String mdpAd) {
            // Récupérer l'Admin par email
        Optional<Admin> findAdmin = this.adminRepository.findByEmailAd(emailAd);
    
        // Vérifier si l'Admin existe
        if (findAdmin.isEmpty()) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    
        // Comparer le mot de passe brut avec le mot de passe haché
        Admin Admin = findAdmin.get();
        if (!this.PasswordEncoder.matches(mdpAd, Admin.getMdpAd())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
}


    @Override
    public void activation(Map<String, String> activation) {
    ActivationCompte activationCompte = this.activationCompteService.LireEnfonctionDuCode(activation.get("code"));
    if (Instant.now().isAfter(activationCompte.getExpirationDate())) {
        throw new RuntimeException("le code a expiré");
    
    }
    Admin AdminActiver = this.adminRepository.findById(activationCompte.getIdAdmin()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
    AdminActiver.setActif(true);
    this.adminRepository.save(AdminActiver);
}
    //methode pour la recuperation d'un super admin par son id
    @Override
    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> chercherParEmail = this.adminRepository.findByEmailAd(username);
        if (chercherParEmail.isEmpty()) {
            throw new RuntimeException("Aucun utilisateur ne correspond a cette email");
        }
        return chercherParEmail.get();
    }
    
}