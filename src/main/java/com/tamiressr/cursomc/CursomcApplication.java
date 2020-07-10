package com.tamiressr.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
