package com.tamiressr.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.tamiressr.cursomc.domain.Cliente;
import com.tamiressr.cursomc.dto.ClienteDTO;
import com.tamiressr.cursomc.repositories.ClienteRepository;
import com.tamiressr.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	/*
	 * httpServletRequest injetado p/ pegar o i do objeto da uri
	 */
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
      @SuppressWarnings("unchecked")
		Map <String,String> map=(Map<String,String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		List<FieldMessage> list = new ArrayList<>();
		Integer uriId= Integer.parseInt( map.get("id"));
		
		Cliente aux= clienteRepository.findByEmail(objDto.getEmail());
		if(aux!=null && !aux.getId().equals(uriId)) {
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
