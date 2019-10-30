package com.vortexbird.pusay.utilities;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.vortexbird.pusay.servlet.ParamServlet;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class EnviarCorreo{
	
	private static Logger logger = Logger.getLogger(EnviarCorreo.class);
	/*
	public static final String hostServer = "emailhub.tcbuen.co";
	public static final String userServer = "nocontestar@tcbuen.com";
	public static final String passServer = "";
	public static final String auth = "false";
	*/
	public static final String PRE_SUBJECT = ".::PUSAY::. ";
	/*
	public static final String PIE_PAGINA = "Favor abstenerse de responder este correo, ya que no sera verificado <br/> <br/>" + 
											 "Recuerde que todos los tramites electronicos con Tcbuen estan sujetos a los siguientes terminos de uso: <br/><br/>" +
											 "http://portnet01.tcbuen.com/PortnetV2/TerminosDeUso <br/><br/>" +
											 "ADVERTENCIA SOBRE CONFIDENCIALIDAD La informacion contenida en este correo electronico, incluyendo sus anexos, esta dirigida <br/>" + 
											 "exclusivamente a su destinatario y puede contener datos de caracter confidencial protegidos por la ley. <br/>" +
											 "Si usted no es el destinatario de este mensaje, por favor informenos y eliminelo a la mayor brevedad. <br/>" + 
											 "Cualquier retencion, difusion, divulgacion o copia de este mensaje es prohibido y sera sancionado por la ley. (las tildes fueron omitidas por compatibilidad) <br/><br/>" +
											 "CONFIDENTIAL WARNING The information contained in this e-mail, with all its attachments is intended <br/>" +
											 "for the use of the individual or entity to whom it is addressed, and may have confidential data. <br/>" + 
											 "If you are not the intended recipient, please immediately send back and delete the received message. <br/>" + 
											 "Any retention, dissemination, distribution or copying of this message is strictly prohibited an sanctioned by law. <br/>";
											*/ 

	
	public static void sendMail(String hostServer, String auth, String userServer, String passServer, String esGmail, String recipients[], String subject, String message, File attachment, String recipientesOcultos[]) throws Exception{
		
		//char[] smimePw = new String("Tcbuen2015").toCharArray();  
		
		try {
			logger.info("############ INICIANDO PROCESO PARA ENVIAR ELECTRONICO ################");
			logger.info(hostServer + " - " + auth + " - " + userServer + " - " + passServer + " - " + esGmail);
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", hostServer);
			props.setProperty("mail.smtp.auth", auth);
			props.setProperty("mail.smtp.user", userServer);
			
		   if (esGmail.equals("true")) {
			   props.put("mail.smtp.starttls.enable", esGmail);
			   props.put("mail.smtp.port", "587");
		   }

			Session session = Session.getDefaultInstance(props);

			MimeMessage message2 = new MimeMessage(session);
			message2.setFrom(new InternetAddress(userServer));
			
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			message2.setRecipients(Message.RecipientType.TO, addressTo);
			
			//Si hay recipientes ocultos
			if (recipientesOcultos != null && recipientesOcultos.length>0) {
				InternetAddress[] addressBcc = new InternetAddress[recipientesOcultos.length];
				for (int i = 0; i < recipientesOcultos.length; i++) {
					addressBcc[i] = new InternetAddress(recipientesOcultos[i]);
				}
				message2.setRecipients(Message.RecipientType.BCC, addressBcc);
			}
			
			message2.setSubject(PRE_SUBJECT + subject);
			message2.setText(message);
			message2.setHeader("X-Mailer", "Pusay");

            //TODO para proyectos web
			Configuration cfg = new Configuration();
			String rutaProyecto = ParamServlet.RUTA_MAIL_TEMPLATE;
			cfg.setDirectoryForTemplateLoading(new File(rutaProyecto));
			Template template = cfg.getTemplate(File.separator + "mailTemplate.html");
			Map<String, String> rootMap = new HashMap<String, String>();
			rootMap.put("message", message);
			rootMap.put("subject", subject);
			Writer out = new StringWriter();
			template.process(rootMap, out);
            // freemarker stuff ends.
            
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(out.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			// Part two is attachment
			if (attachment!=null) {
				
				if (attachment.isFile() && attachment.exists()){
					messageBodyPart = new MimeBodyPart();
					String filename = attachment.getName();
					DataSource source = new FileDataSource(attachment);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);
				}
			}
			// Put parts in message
			message2.setContent(multipart);
			message2.saveChanges();
			
			// Send the message
			Transport transport = session.getTransport("smtp");
			transport.connect(userServer, passServer);
			transport.sendMessage(message2, message2.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			logger.error("Error enviando el correo electronico, el error fue " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("############ PROCESO PARA ENVIAR CORREO ELECTRONICO FINALIZADO CON EXITO ################");
	}

	
//	public static void main(String[] args) {
//		
//		String[] recipiente = {"avargas@vortexbird.com"};
//		String hostServer = "smtp.gmail.com";//"smtp.gmail.com"
//		String auth = "true";//"true"
//		String userServer = "dams9011@gmail.com";
//		String passServer = "1144040220";
//		String esGmail = "true";//"true"
//		
//		String subject = "Prueba Correo Pusay";
//		String mensaje = "Mensaje de Prueba";
//		File attachment = null;
//		
//		try {
//			EnviarCorreo.sendMail(hostServer, auth, userServer, passServer, esGmail, recipiente, subject, mensaje, attachment, null);
//		} catch (Exception e) {
//			logger.error("Error enviando el correo electronico, el error fue " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
}
