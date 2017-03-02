package mail_extentReport;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

import constant.Constant;

public class Mail_report{
	
	
   @Test
	 public static void send_report() {

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", Constant.MAILSMTP);
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Constant.MAILFROM,Constant.MAILPWD);
         }
      });

      try {
    	 String workspace = 	((new File(".").getAbsolutePath()).replace("\\", "/")).replace(".", "");
         String reportFileName = "report.html";
         
         DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
         String datetext = formatter.format(new Date());
         
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(Constant.MAILFROM));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(Constant.MAILTO));
         message.setSubject("Execution Report:"+datetext);
         
	     
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("Hi, PFA the consolidated report for the latest execution.");
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         messageBodyPart = new MimeBodyPart();
         DataSource source = new FileDataSource(workspace + Constant.ReportPath);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(reportFileName);
         multipart.addBodyPart(messageBodyPart);
         
         message.setContent(multipart);
         
         System.out.println("Test");
         Transport.send(message);
         System.out.println("Message sent!");   
         
      }
      catch (MessagingException e) {
            throw new RuntimeException(e);
      }
	 }
}
