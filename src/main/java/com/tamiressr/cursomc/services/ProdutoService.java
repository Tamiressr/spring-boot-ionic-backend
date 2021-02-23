package com.tamiressr.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.domain.Produto;
import com.tamiressr.cursomc.repositories.CategoriaRepository;
import com.tamiressr.cursomc.repositories.ProdutoRepository;
import com.tamiressr.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto buscar(Integer id) {
		Optional<Produto> obj=produtoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+id+",Tipo: "+ Produto.class.getName()));
	
}
	public Page<Produto>search(String nome,List<Integer> ids, Integer page,Integer linesPerPage,String orderBy, String direction){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias=categoriaRepository.findAllById(ids);
		
		//findDistinctBy<>Containing... Padrão de nome do Spring data, não precisa da query se aplicar esse padrão na consulta 
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	
}
}
