package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.repository.ClienteRepository;
import com.projetospring.apijava.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id){
        Optional<Cliente> categoria =  clienteRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
