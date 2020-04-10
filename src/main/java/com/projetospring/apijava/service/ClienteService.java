package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Cidade;
import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.domain.Endereco;
import com.projetospring.apijava.domain.enums.TipoCliente;
import com.projetospring.apijava.dto.ClienteDTO;
import com.projetospring.apijava.dto.ClienteNewDTO;
import com.projetospring.apijava.repository.ClienteRepository;
import com.projetospring.apijava.repository.EnderecoRepository;
import com.projetospring.apijava.service.exception.DataIntegrityException;
import com.projetospring.apijava.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente buscar(Integer id){
        Optional<Cliente> cliente =  clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente inserir(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public void atualizar(Cliente cliente){
        Cliente newCliente = buscar(cliente.getId());
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
        clienteRepository.save(newCliente);
    }

    public void deletar(Integer id){
        buscar(id);
        try {
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma cliente que possui pedidos");
        }
    }

    public List<Cliente> listarTodas(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> listarPagina(Integer pagina, Integer linhas, String ordenar, String direcao){
        PageRequest paginas = PageRequest.of(pagina, linhas, Sort.Direction.valueOf(direcao), ordenar);
        return clienteRepository.findAll(paginas);
    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfCnpj(),
                TipoCliente.toEnum(objDto.getTipo()));

        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cliente.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cliente.getTelefones().add(objDto.getTelefone3());
        }
        return cliente;
    }

}
