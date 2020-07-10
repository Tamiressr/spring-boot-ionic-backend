package com.tamiressr.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.domain.Cidade;
import com.tamiressr.cursomc.domain.Estado;
import com.tamiressr.cursomc.domain.Produto;
import com.tamiressr.cursomc.repositories.CategoriaRepository;
import com.tamiressr.cursomc.repositories.CidadeRepository;
import com.tamiressr.cursomc.repositories.EstadoRepository;
import com.tamiressr.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1= new Categoria(null, "informática");
		Categoria cat2= new Categoria(null, "Escritório");
		Produto p1=new Produto(null,"Computador",2000.00);
		Produto p2=new Produto(null,"Impressora",800.00);
		Produto p3=new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado estado1=new Estado(null, "Paraíba");
		Estado estado2=new Estado(null, "Pernambuco");
		
		Cidade cidade1=new Cidade(null, "Monteiro", estado1);
		Cidade cidade2=new Cidade(null, "Recife", estado2);
		Cidade cidade3=new Cidade(null, "São José do Egito", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		
		
		
		
	}

}
