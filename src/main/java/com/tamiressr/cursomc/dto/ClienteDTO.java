package com.tamiressr.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.tamiressr.cursomc.domain.Cliente;
import com.tamiressr.cursomc.services.validation.ClienteUpdate;
@ClienteUpdate
public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//atributos que podem mudar
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;


	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id=cliente.getId();
		this.nome=cliente.getNome();
		this.email=cliente.getEmail();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
