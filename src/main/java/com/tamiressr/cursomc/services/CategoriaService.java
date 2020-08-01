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

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.dto.CategoriaDTO;
import com.tamiressr.cursomc.repositories.CategoriaRepository;
import com.tamiressr.cursomc.services.exceptions.DataIntegrityException;
import com.tamiressr.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	public Categoria find(Integer id) {
		Optional<Categoria> obj=categoriaRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+id+",Tipo: "+ Categoria.class.getName()));
	}
	@Transactional
	public Categoria insert(Categoria obj) {
		//garante que está salvando um obj novo
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	public Categoria update(Categoria obj) {
		//busca o obj, caso não exista lança excessão
		Categoria newObj=find(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			
			categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados");
			
		}
	}
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria>findPage(Integer page,Integer linesPerPage,String orderBy, String direction){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return categoriaRepository.findAll(pageRequest);
		
	}
	//instancia uma categoria a partir de um dto
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
	//metodo auxiliar para atualizar os dados de um obj com base em um obj já criado
	private void updateData(Categoria newObj, Categoria obj){
			newObj.setNome(obj.getNome());

			
		}
	
}
