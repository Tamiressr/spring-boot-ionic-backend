package com.tamiressr.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.Categoria;
import com.tamiressr.cursomc.domain.Cidade;
import com.tamiressr.cursomc.domain.Cliente;
import com.tamiressr.cursomc.domain.Endereco;
import com.tamiressr.cursomc.domain.Estado;
import com.tamiressr.cursomc.domain.ItemPedido;
import com.tamiressr.cursomc.domain.Pagamento;
import com.tamiressr.cursomc.domain.PagamentoComBoleto;
import com.tamiressr.cursomc.domain.PagamentoComCartao;
import com.tamiressr.cursomc.domain.Pedido;
import com.tamiressr.cursomc.domain.Produto;
import com.tamiressr.cursomc.domain.enums.EstadoPagamento;
import com.tamiressr.cursomc.domain.enums.TipoCliente;
import com.tamiressr.cursomc.repositories.CategoriaRepository;
import com.tamiressr.cursomc.repositories.CidadeRepository;
import com.tamiressr.cursomc.repositories.ClienteRepository;
import com.tamiressr.cursomc.repositories.EnderecoRepository;
import com.tamiressr.cursomc.repositories.EstadoRepository;
import com.tamiressr.cursomc.repositories.ItemPedidoRepository;
import com.tamiressr.cursomc.repositories.PagamentoRepository;
import com.tamiressr.cursomc.repositories.PedidoRepository;
import com.tamiressr.cursomc.repositories.ProdutoRepository;
/*
 * responsável pela instanciação do banco de dados de test
 */
@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1= new Categoria(null, "informática");
		Categoria cat2= new Categoria(null, "Escritório");
		Categoria cat3= new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4= new Categoria(null, "Eletrônicos");
		Categoria cat5= new Categoria(null, "Jardinagem");
		Categoria cat6= new Categoria(null, "Decoração");
		Categoria cat7= new Categoria(null, "Perfumaria");
		
		Produto p1=new Produto(null,"Computador",2000.00);
		Produto p2=new Produto(null,"Impressora",800.00);
		Produto p3=new Produto(null,"Mouse",80.00);
		Produto p4=new Produto(null,"Mesa de escritorio",300.00);
		Produto p5=new Produto(null,"Toalha",50.00);
		Produto p6=new Produto(null,"Colcha",200.00);
		Produto p7=new Produto(null,"TV true color",1200.00);
		Produto p8=new Produto(null,"Rocadeira",800.00);
		Produto p9=new Produto(null,"Abajour",100.00);
		Produto p10=new Produto(null,"Pendente",180.00);
		Produto p11=new Produto(null,"Shampoo",90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
	
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado estado1=new Estado(null, "Paraíba");
		Estado estado2=new Estado(null, "Pernambuco");
		
		Cidade cidade1=new Cidade(null, "Monteiro", estado1);
		Cidade cidade2=new Cidade(null, "Recife", estado2);
		Cidade cidade3=new Cidade(null, "São José do Egito", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		
		Cliente cliente1=new Cliente(null,"Tamires","tamires@gmail.com","1121212",TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("33512021","3365252"));
		Endereco endereco1= new Endereco(null,"rua a","numero 1","complemento a","bairro a","cep 1",cliente1,cidade1);
		Endereco endereco2=new Endereco(null,"rua b","numero 2","complemento b","bairro b","cep 2",cliente1,cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
	
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedido1=new Pedido(null,sdf.parse("10/07/2020 13:32"),cliente1,endereco1);
		Pedido pedido2=new Pedido(null,sdf.parse("10/07/2020 13:34"),cliente1,endereco2);
		
		Pagamento pagamento1=new PagamentoComCartao(null,EstadoPagamento.QUITADO,pedido1,6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2=new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,pedido2,sdf.parse("20/07/2020 00:00"),null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));
		
		
		ItemPedido itemPedido1= new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2= new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido itemPedido3= new ItemPedido(pedido1, p2, 0.00, 1, 800.00);
		pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
		
	}
}
