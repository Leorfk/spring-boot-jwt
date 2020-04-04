package com.projetospring.apijava.repository;

import com.projetospring.apijava.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}