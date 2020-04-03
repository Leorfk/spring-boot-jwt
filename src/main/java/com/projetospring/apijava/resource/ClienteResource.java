package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Cliente> findById(@RequestParam(value="id" ) Integer id){
        Cliente cat = clienteService.buscar(id);
        //return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(cat);
    }
}
