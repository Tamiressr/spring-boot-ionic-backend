package com.tamiressr.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.Cidade;
import com.tamiressr.cursomc.domain.Cliente;
import com.tamiressr.cursomc.domain.Endereco;
import com.tamiressr.cursomc.domain.enums.TipoCliente;
import com.tamiressr.cursomc.dto.ClienteDTO;
import com.tamiressr.cursomc.dto.ClienteNewDTO;
import com.tamiressr.cursomc.repositories.ClienteRepository;
import com.tamiressr.cursomc.repositories.EnderecoRepository;
import com.tamiressr.cursomc.services.exceptions.DataIntegrityException;
import com.tamiressr.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj=clienteRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+id+",Tipo: "+ Cliente.class.getName()));
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		//garante que está salvando um obj novo
		obj.setId(null);
		obj= clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	public Cliente update(Cliente obj) {
		//busca o obj, caso não exista lança excessão
		Cliente newObj=find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um  cliente que possui pedidos associados");
			
		}
	}
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente>findPage(Integer page,Integer linesPerPage,String orderBy, String direction){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
		
	}
	//instancia um cliente  a partir de um dto
	public Cliente fromDTO(ClienteDTO objDTO) {
	return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null, null);
	}
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente=new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cidade=new Cidade(objDTO.getCidadeId(), null, null);
		Endereco endereco=new Endereco(null, objDTO.getLogradouro(),objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		return cliente;
	}
	//metodo auxiliar para atualizar os dados de um obj com base em um obj já criado
	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
