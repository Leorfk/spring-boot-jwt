package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Categoria;
import com.projetospring.apijava.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Categoria> findById(@RequestParam(value="id" ) Integer id){
        Categoria cat = categoriaService.buscar(id);
        return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.noContent().build();
    }
}
