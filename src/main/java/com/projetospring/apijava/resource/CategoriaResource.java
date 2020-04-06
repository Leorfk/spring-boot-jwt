package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Categoria;
import com.projetospring.apijava.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Categoria> findById(@RequestParam(value="id" ) Integer id){
        Categoria cat = categoriaService.buscar(id);
        //return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = categoriaService.iserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("?id={id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
