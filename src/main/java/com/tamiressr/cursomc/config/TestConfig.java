package com.tamiressr.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tamiressr.cursomc.services.DBService;

/*
 * Configurações específicas do profile de teste
 * especifica que todos os beans dentro da classe
 *  serão ativados apenas quando
 * o profile for o de test 
 * que estiver ativo no aplication.properties 
 */
@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
}
