package com.projetospring.apijava.dto;

import com.projetospring.apijava.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class CategoriaDTO implements Serializable {
    private static long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Nome é de preenchimento obrigatório")
    @Length(min = 1, max = 30, message = "Nome entre 1 e 30 caracteres")
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
