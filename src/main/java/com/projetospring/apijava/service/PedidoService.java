package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.domain.ItemPedido;
import com.projetospring.apijava.domain.PagamentoComBoleto;
import com.projetospring.apijava.domain.Pedido;
import com.projetospring.apijava.domain.enums.EstadoPagamento;
import com.projetospring.apijava.repository.ItemPedidoRepository;
import com.projetospring.apijava.repository.PagamentoRepository;
import com.projetospring.apijava.repository.PedidoRepository;
import com.projetospring.apijava.security.UserSpringSecurity;
import com.projetospring.apijava.service.exception.AuthorizationException;
import com.projetospring.apijava.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido buscar(Integer id){
        Optional<Pedido> pedido =  pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }

    public List<Pedido> buscarPedidosPorCliente(){
        UserSpringSecurity user = UserService.authenticated();
        if (user == null){
            throw new AuthorizationException("Acesso negado");
        }
        Cliente cliente = clienteService.buscar(user.getId());
        return pedidoRepository.findByCliente(cliente);
    }
}
