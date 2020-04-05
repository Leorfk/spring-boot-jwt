package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Pedido;
import com.projetospring.apijava.repository.PedidoRepository;
import com.projetospring.apijava.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id){
        Optional<Pedido> pedido =  pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
