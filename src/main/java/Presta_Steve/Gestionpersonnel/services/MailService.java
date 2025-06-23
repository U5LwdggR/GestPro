package Presta_Steve.Gestionpersonnel.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.ActivationCompte;
import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.repositories.UtilisateursRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final UtilisateursRepository utilisateursRepository;

    public void sendEmail(ActivationCompte activationCompte) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            Utilisateurs utilisateurs = this.utilisateursRepository.findById(activationCompte.getUtilisateur().getIdUser()).orElseThrow(() -> new RuntimeException("Utilisateur pas trouvé"));

            // Configurez le message
            mail.setFrom("stevedarel107@gmail.com"); // Assurez-vous que cette adresse correspond à spring.mail.username
            mail.setTo(utilisateurs.getEmail());
            System.err.println("email: " + utilisateurs.getEmail());
            mail.setSubject("verification de votre compte");
            String contenuMessage = "Bonjour " + utilisateurs.getNom() + "\n" +
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
