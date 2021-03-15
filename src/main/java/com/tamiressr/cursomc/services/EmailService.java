package com.tamiressr.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.tamiressr.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido p);
	void SendEmail(SimpleMailMessage msg);
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
}
