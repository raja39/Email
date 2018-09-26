package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.example.configuration.ApplicationConfig;
import com.example.model.Mail;
import com.example.service.MailService;

public class EmailService {

	public static void main(String[] args) {
		 
		  System.out.println("Enter your email: ");
		  @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		  String fromaddress = scanner.nextLine();
		  
//		  System.out.println("Enter number of recepients: ");
//		  int num=scanner.nextInt();
//		  
//		  String toaddress[]= new String[num];
//		  System.out.println("Enter recepient email: ");
		 
	        Mail mail = new Mail();
	        mail.setMailFrom(fromaddress);
	      //  mail.setMailTo(toaddress);
	        mail.setMailSubject("New Beginnings !! ");
	        
	        Map < String, Object > model = new HashMap < String, Object > ();
	        model.put("firstName", "Friend");
	        model.put("lastName", " ");
	        model.put("signature", "Harish.");
	        mail.setModel(model);
	        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	        MailService mailService = (MailService) context.getBean("mailService");
	        mailService.sendEmail(mail);
	        context.close();
	      
	}
}
