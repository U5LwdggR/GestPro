package Presta_Steve.Gestionpersonnel.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Admin;
import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;
import Presta_Steve.Gestionpersonnel.repositories.AdminRepository;
import Presta_Steve.Gestionpersonnel.repositories.SuperAdminRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SuperAdminRepository superAdminRepository;
    private final AdminRepository adminRepository;

    public void sendEmail(ActivationCompte activationCompte) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            SuperAdmin superAdmin = this.superAdminRepository.findById(activationCompte.getIdSuperAdmin()).orElseThrow(() -> new RuntimeException("SuperAdmin pas trouvé"));

            // Configurez le message
            mail.setFrom("stevedarel107@gmail.com"); // Assurez-vous que cette adresse correspond à spring.mail.username
            mail.setTo(superAdmin.getEmailSup());
            System.err.println("email: " + superAdmin.getEmailSup());
            mail.setSubject("verification de votre compte");
            String contenuMessage = "Bonjour " + superAdmin.getNomSup() + "\n" +
                    "Copiez puis collez le code ci-dessous dans le formulaire pour activer votre compte sans ca, vous ne pourrez pas vous connecter\n" +
                    "code: " + activationCompte.getCode();
            mail.setText(contenuMessage);
            // Envoyez le message
            mailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }

    public void sendEmailAd(ActivationCompte activationCompte) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            Admin Admin = this.adminRepository.findById(activationCompte.getIdAdmin()).orElseThrow(() -> new RuntimeException("SuperAdmin pas trouvé"));

            // Configurez le message
            mail.setFrom("stevedarel107@gmail.com"); // Assurez-vous que cette adresse correspond à spring.mail.username
            mail.setTo(Admin.getEmailAd());
            System.err.println("email: " + Admin.getEmailAd());
            mail.setSubject("verification de votre compte");
            String contenuMessage = "Bonjour " + Admin.getNomAd() + "\n" +
                    "Copiez puis collez le code ci-dessous dans le formulaire pour activer votre compte sans ca, vous ne pourrez pas vous connecter\n" +
                    "code: " + activationCompte.getCode();
            mail.setText(contenuMessage);
            // Envoyez le message
            mailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }
}