package Presta_Steve.GestionPersonnel.services;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.ActivationCompte;
import Presta_Steve.GestionPersonnel.entities.Role;
import Presta_Steve.GestionPersonnel.entities.Utilisateur;
import Presta_Steve.GestionPersonnel.interfaces.IAdminService;
import Presta_Steve.GestionPersonnel.repositories.AdminRepository;
import Presta_Steve.GestionPersonnel.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder PasswordEncoder;
    private final ActivationCompteService activationCompteService;
    private final RoleRepository roleRepository;
    
    //private final TokenService tokenService;

    @Override
    public void ajouterAdmin(Utilisateur admin) {


    //verifier si l'email contient @
        if (!admin.getEmailSup().contains("@")) {
            throw new RuntimeException("votre email doit contenir '@'");
        }
        // ensuite verifier si l'email contient .
        if (!admin.getEmailSup().contains(".")) {
            throw new RuntimeException("votre email doit contenir '.'");
        }
        //verifier si le super admin existe deja en fonction de son email
        Optional<Utilisateur> findAdmin = this.adminRepository.findByEmailSup(admin.getEmailSup());
        if (findAdmin.isPresent()) {
            throw new RuntimeException("Veuillez choisir un autre email le compte semble deja exister");
        }
        
        //cryptage du mot de passe de l'admin
        admin.setMdpSup(this.PasswordEncoder.encode(admin.getMdpSup()));
        
        // Utilisateur idAdmin = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // admin.setIdSup(idAdmin.getIdSup());

        //j'associe le role a  parce que je sais que le role "SUPER_ADMIN" a pour id 1 dans la bd
        Role roleExiste = this.roleRepository.findById(2);
        if (roleExiste == null) {
            throw new RuntimeException("le role n'existe pas");
        }
        admin.setRole(roleExiste.getLibelle());

        //enregistrement du super admin
        Utilisateur admin2 = this.adminRepository.save(admin);
        
        //enregistrement de l'activation du compte super admin
        this.activationCompteService.enregistrerActivationCompte(admin2);
    }

    @Override
    public void connexionAdmin(String emailAd, String mdpAd) {
            // Récupérer l'Admin par email
        Optional<Utilisateur> findAdmin = this.adminRepository.findByEmailSup(emailAd);
    
        // Vérifier si l'Admin existe
        if (findAdmin.isEmpty()) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
    
        // Comparer le mot de passe brut avec le mot de passe haché
        Utilisateur Admin = findAdmin.get();
        if (!this.PasswordEncoder.matches(mdpAd, Admin.getMdpSup())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
}


    @Override
    public void activation(Map<String, String> activation) {
    ActivationCompte activationCompte = this.activationCompteService.LireEnfonctionDuCode(activation.get("code"));
    if (Instant.now().isAfter(activationCompte.getExpirationDate())) {
        throw new RuntimeException("le code a expiré");
    
    }
    Utilisateur AdminActiver = this.adminRepository.findById(activationCompte.getIdSuperAdmin()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
    AdminActiver.setActif(true);
    this.adminRepository.save(AdminActiver);
}
}