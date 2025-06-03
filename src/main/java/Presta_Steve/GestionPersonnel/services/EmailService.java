package Presta_Steve.GestionPersonnel.services;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.ActivationCompte;
import Presta_Steve.GestionPersonnel.entities.MembrePersonnel;
import Presta_Steve.GestionPersonnel.entities.Utilisateur;
import Presta_Steve.GestionPersonnel.repositories.SuperAdminRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SuperAdminRepository superAdminRepository;

    public void sendEmail(ActivationCompte activationCompte) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            Utilisateur superAdmin = this.superAdminRepository.findById(activationCompte.getIdSuperAdmin()).orElseThrow(() -> new RuntimeException("SuperAdmin pas trouvé"));

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

    public void sendEmailEmploye(MembrePersonnel personnel) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();

            // Configurez le message
            mail.setFrom("stevedarel107@gmail.com"); // Assurez-vous que cette adresse correspond à spring.mail.username
            mail.setTo(personnel.getEmailper());
            System.err.println("email: " + personnel.getEmailper());
            mail.setSubject("Notification d'abscence au lieu de travail le "+ LocalDateTime.now());
            String contenuMessage = "Bonjour " + personnel.getNomPer() + "\n" +
                    "Nous avons constate que vous n'etes pas venu au travail aujourd'hui, alors nous vous prions de passer voir le responsable de votre service d'appartenant ou de le contacter pour justifier votre abscence.\n" +
                    "Dans le cas contraire une sanction vous serra attribue\n" +
                    "porte vous bien et a demain";
            mail.setText(contenuMessage);
            // Envoyez le message
            mailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }
}