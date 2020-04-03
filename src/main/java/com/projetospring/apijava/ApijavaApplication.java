package com.projetospring.apijava;

import com.projetospring.apijava.domain.*;
import com.projetospring.apijava.domain.enums.TipoCliente;
import com.projetospring.apijava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ApijavaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApijavaApplication.class, args);
		System.out.println("\nWelcome " +
				"\n  _____" +
				"\n  |0V0|" +
				"\n /| O |\\" +
				"\n  |_|_|");
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);


		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado estado1 = new Estado(null, "São Paulo");
		Estado estado2 = new Estado(null, "Rio de Janeiro");

		Cidade cidade1 = new Cidade(null, "Suzano", estado1);
		Cidade cidade2 = new Cidade(null, "Ipanema", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado1);

		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade2));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Ozzy Osbourne", "ozzy@batman.com", "00000000191",
				TipoCliente.PESSOAFISICA);

		cliente1.getTelefones().addAll(Arrays.asList("123456789", "999999999"));

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apto 203",
				"Jardins", "12345678", cliente1, cidade1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "2800", "Casa 27",
				"Centro", "0000000000", cliente1, cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
