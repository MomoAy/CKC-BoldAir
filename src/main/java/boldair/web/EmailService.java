package boldair.web; // ou boldair.web selon ton choix

import boldair.data.Equipe;
import boldair.data.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import boldair.data.Benevole;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
    private static final String FROM_EMAIL = "noreply@boldair.com";
    private static final String FROM_NAME = "BoldAir";

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerEmailSimple(String destinataire, String sujet, String contenu) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinataire);
        message.setSubject(sujet);
        message.setText(contenu);
        message.setFrom(FROM_EMAIL);
        message.setReplyTo(FROM_EMAIL);

        mailSender.send(message);

    }


    public void envoyerEmailHtml(String destinataire, String sujet, String contenuHtml) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setTo(destinataire);
        helper.setSubject(sujet);
        helper.setText(contenuHtml, true);
        helper.setFrom(FROM_EMAIL);
        helper.setReplyTo(FROM_EMAIL);
        
        mailSender.send(message);
    }

    public void envoyerEmailConfirmationBenevole(Benevole benevole) {
        String sujet = "Confirmation de votre inscription - BoldAir";
        String contenu = String.format(
            "Bonjour %s,\n\n" +
            "Votre inscription en tant que bénévole a bien été enregistrée.\n" +
            "Email: %s\n\n" +
            "Merci de votre engagement !\n\n" +
            "L'équipe BoldAir",
            benevole.getNomBen(),
            benevole.getEmail()
        );
        
        envoyerEmailSimple(benevole.getEmail(), sujet, contenu);
    }

    public void envoyerEmailConfirmationParticipants(Participant p1, Participant p2, Equipe equipe) {
        String sujet = "Confirmation de votre inscription - BoldAir";
        String contenu = String.format(
                "Bonjour %s,\n\n" +
                        "Votre inscription en tant que participant a bien été enregistrée.\n" +
                        "Equipe : %s\n\n" +
                        "Coequipier : %s\n\n" +
                        "Email: %s\n\n" +
                        "Merci de votre engagement !\n\n" +
                        "L'équipe BoldAir",
                p1.getNomPart(),
                equipe.getNomEquipe(),
                p2.getNomPart(),
                p1.getEmailPart()
        );

        envoyerEmailSimple(p1.getEmailPart(), sujet, contenu);
    }

    public void envoyerNotificationAdmin(String emailAdmin, Benevole benevole) {
        String sujet = "Nouveau bénévole inscrit - BoldAir";
        String contenu = String.format(
            "Un nouveau bénévole s'est inscrit :\n\n" +
            "Nom: %s\n" +
            "Email: %s\n" +
            "ID: %d\n\n" +
            "Connexion à l'administration requise.",
            benevole.getNomBen(),
            benevole.getEmail(),
            benevole.getIdBen()
        );
        
        envoyerEmailSimple(emailAdmin, sujet, contenu);
    }
}