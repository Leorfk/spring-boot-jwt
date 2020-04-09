package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Categoria;
import com.projetospring.apijava.repository.CategoriaRepository;
import com.projetospring.apijava.service.exception.DataIntegrityException;
import com.projetospring.apijava.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria =  categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
    public Categoria iserir(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public void atualizar(Categoria categoria){
        Categoria newCategoria = buscar(categoria.getId());
        newCategoria.setName(categoria.getName());
        categoriaRepository.save(newCategoria);
    }

    public void deletar(Integer id){
        buscar(id);
        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> listarPagina(Integer pagina, Integer linhas, String ordenar, String direcao){
        PageRequest paginas = PageRequest.of(pagina, linhas, Direction.valueOf(direcao), ordenar);
        return categoriaRepository.findAll(paginas);
    }
}
