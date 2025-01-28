package mail;

import utils.Printer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {

    private static final Printer log = new Printer(EmailClient.class);

    public static Boolean sendEmail (String subject, String content, String receiver, String ID, String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ID, password);
            }
        });
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(ID));

            // Set To: header field of the header.
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receiver));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(content+"\n");

            log.info("Sending the email...");
            Transport.send(message);
            log.success("Email sent!");
            return true;
        }
        catch (javax.mail.MessagingException e) {log.warning(e.getMessage());}
        return false;
    }

}
