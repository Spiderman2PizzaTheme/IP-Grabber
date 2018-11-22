package ipchecker;

//Imports

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Class

public class SendMail extends TimedExecution{

    public static void main(String[] args) {
        
        /*In the two String fields below, please enter the email address
        and password of the email account you wish to send emails from.
        */
     
        final String username = "your_email_here@email.com";
        final String password = "your_password_here";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            String RefreshedIp = null;
            try {
                RefreshedIp = TimedExecution.getIp();
            } catch (Exception ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*You will need to enter your email address, the reciepent's
            email address, subject line and message text in the fields below.
            */            
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email_here@email.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("recipient_email_here@email.com"));
            message.setSubject("Updated External IP");
            message.setText("Dear User,"
                + "\n\n Your new external IP is "+RefreshedIp);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}