package com.tamiressr.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal= Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		//Acrescenta 7 dias 
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
		// situação real: troca pela chamada de webservice para gerar boleto por exemplo...
	}
}
