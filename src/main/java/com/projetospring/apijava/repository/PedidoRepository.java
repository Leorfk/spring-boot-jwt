package com.projetospring.apijava.repository;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    List<Pedido> findByCliente(Cliente cliente);

}