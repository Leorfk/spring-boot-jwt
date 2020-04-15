package com.projetospring.apijava.resource;

import com.projetospring.apijava.domain.Categoria;
import com.projetospring.apijava.domain.Produto;
import com.projetospring.apijava.dto.CategoriaDTO;
import com.projetospring.apijava.dto.ProdutoDTO;
import com.projetospring.apijava.resource.utils.URL;
import com.projetospring.apijava.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Produto> findById(@RequestParam(value="id" ) Integer id){
        Produto produto = produtoService.buscar(id);
        //return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping(value = "/paginas")
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
