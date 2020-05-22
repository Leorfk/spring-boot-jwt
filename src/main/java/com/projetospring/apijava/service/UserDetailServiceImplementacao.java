package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementacao implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    //Vai retornar o UserSpringSecurity
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteService.buscarPorEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPefis());
    }
}
