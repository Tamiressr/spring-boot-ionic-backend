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
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		}

}
