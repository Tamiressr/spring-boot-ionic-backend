package com.tamiressr.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tamiressr.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm= prepareSimpleMailMessageFromPedido(pedido) ; 
		SendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
	
		SimpleMailMessage sm= new SimpleMailMessage ();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação de Pedido! Código:"+pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}
	//pega o template, processa o obj pedido e retorna o html na forma de String
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context= new Context();
		//nome/apelido do objeto passado no template 
		context.setVariable("pedido", obj);
		// retorna o html na forma de strig
		return templateEngine.process("email/confirmacaoPedido", context);
		
			}
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			
			MimeMessage mm= prepareMimeMessageFromPedido(obj) ; 
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
			}
		
	
	protected MimeMessage prepareMimeMessageFromPedido(Pedido p) throws MessagingException {
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mmh= new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(p.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código: "+p.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(p),true);
	return mimeMessage;
	}
}
