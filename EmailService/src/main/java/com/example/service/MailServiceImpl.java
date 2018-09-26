package com.example.service;

import java.util.Map;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.example.model.Mail;
 
 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Autowired
    VelocityEngine velocityEngine;
 
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
          //  mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setTo(InternetAddress.parse("pmidhun1728@gmail.com,shivananda11261@gmail.com"));
          mail.setMailContent(geContentFromTemplate(mail.getModel()));
           mimeMessageHelper.setText(mail.getMailContent(), true);
            
        	Scanner scanner = new Scanner(System.in);
        	 System.out.println("Enter your file path: ");
        	 String path = scanner.nextLine();
        	FileSystemResource file = new FileSystemResource(path);
        	mimeMessageHelper.addAttachment(file.getFilename(), file);
        	
         //System.out.println("Enter your text: ");
  		 // String body = scanner.nextLine();
        	// mimeMessageHelper.setText(body);       
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
 
    public String geContentFromTemplate(Map < String, Object > model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/templates/email-template.vm", model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
 
}