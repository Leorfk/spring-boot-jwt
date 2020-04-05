package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Pedido;
import com.projetospring.apijava.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Pedido> findById(@RequestParam(value="id" ) Integer id){
        Pedido pedido = pedidoService.buscar(id);
        //return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(pedido);
    }
}
