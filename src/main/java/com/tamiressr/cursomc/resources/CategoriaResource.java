package com.tamiressr.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService CategoriaService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
			Categoria obj=CategoriaService.buscar(id);
			
			return ResponseEntity.ok().body(obj);
	}
}
