package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.dto.ClienteDTO;
import com.projetospring.apijava.dto.ClienteNewDTO;
import com.projetospring.apijava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @RequestParam Integer id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        clienteService.atualizar(cliente);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
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
