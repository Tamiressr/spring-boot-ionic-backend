package com.tamiressr.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamiressr.cursomc.domain.ItemPedido;
import com.tamiressr.cursomc.domain.PagamentoComBoleto;
import com.tamiressr.cursomc.domain.Pedido;
import com.tamiressr.cursomc.domain.enums.EstadoPagamento;
import com.tamiressr.cursomc.repositories.ItemPedidoRepository;
import com.tamiressr.cursomc.repositories.PagamentoRepository;
import com.tamiressr.cursomc.repositories.PedidoRepository;
import com.tamiressr.cursomc.repositories.ProdutoRepository;
import com.tamiressr.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj=pedidoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+id+",Tipo: "+ Pedido.class.getName()));
	}
	 @Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);//garante que está inserindo um novo pedido
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto=(PagamentoComBoleto) obj.getPagamento();
			//preenche a data de vencimento= uma semana após o pedido
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		obj=pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
	
	   for(ItemPedido ip: obj.getItens()) {
		   ip.setDesconto(0.0);
		   ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
		 ip.setPreco(ip.getProduto().getPreco());
	     ip.setPedido(obj);
	   }
	   itemPedidoRepository.saveAll(obj.getItens());
	   emailService.sendOrderConfirmationHtmlEmail(obj);
	   return obj;
	}
}
