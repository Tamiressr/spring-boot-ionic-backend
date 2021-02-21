package com.tamiressr.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tamiressr.cursomc.domain.Cliente;
import com.tamiressr.cursomc.domain.enums.TipoCliente;
import com.tamiressr.cursomc.dto.ClienteNewDTO;
import com.tamiressr.cursomc.repositories.ClienteRepository;
import com.tamiressr.cursomc.resources.exception.FieldMessage;
import com.tamiressr.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		Cliente aux= clienteRepository.findByEmail(objDto.getEmail());
		if(aux!=null) {
			list.add(new FieldMessage("email","Email já existe"));
		}
		for (FieldMessage e : list) {
			// pega as mensagens personalizadas do fieldmessage e adapta ao framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
