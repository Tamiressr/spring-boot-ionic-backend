package com.tamiressr.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.repositories.CategoriaRepository;
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
	
	public Categoria insert(Categoria obj) {
		//garante que está salvando um obj novo
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		//busca o obj, caso não exista lança excessão
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
	
}
