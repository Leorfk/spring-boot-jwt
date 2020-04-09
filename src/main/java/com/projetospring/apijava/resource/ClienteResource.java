package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.dto.ClienteDTO;
import com.projetospring.apijava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Cliente> findById(@RequestParam(value="id" ) Integer id){
        Cliente cliente = clienteService.buscar(id);
        //return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @RequestParam Integer id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        clienteService.atualizar(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ClienteDTO>> getAll(){
        List<Cliente> clientes = clienteService.listarTodas();
        List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping(value = "/paginas")
    public ResponseEntity<Page<ClienteDTO>> toma(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "12") Integer linhas,
            @RequestParam(defaultValue = "name") String ordem,
            @RequestParam(defaultValue = "ASC") String direcao
    ){
        Page<Cliente> clientes = clienteService.listarPagina(pagina, linhas, ordem, direcao);
        Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(clientesDTO);
    }
}
