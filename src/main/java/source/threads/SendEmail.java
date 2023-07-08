package source.threads;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail extends Thread{
    private static final String senderEmail = "approjectshopapplication@zohomail.com";
    private static final String senderPassword = ""; //password has been changed after repository went public
    private final String recipientEmail;
    private final String subject;
    private final String text;
    private static final boolean sendEmail = false;

    public SendEmail(String recipientEmail, String subject, String text) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.text = text;
    }
    @Override
    public void run() {
        if (!sendEmail) {
            System.out.println("Sending Email is turned off right now");
            return;
        }
        sendEmail();
    }

    public void sendEmail() {
        String emailHost = "smtp.zoho.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", emailHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
