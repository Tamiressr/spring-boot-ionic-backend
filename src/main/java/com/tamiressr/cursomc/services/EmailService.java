package com.tamiressr.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.tamiressr.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido p);
	void SendEmail(SimpleMailMessage msg);
}
