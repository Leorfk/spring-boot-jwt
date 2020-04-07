package com.projetospring.apijava.dto;

import com.projetospring.apijava.domain.Categoria;
import com.projetospring.apijava.domain.Produto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CategoriaDTO implements Serializable {
    private static long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        name = categoria.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
