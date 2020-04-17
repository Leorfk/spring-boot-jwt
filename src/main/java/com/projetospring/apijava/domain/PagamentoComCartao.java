package com.projetospring.apijava.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.projetospring.apijava.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoComCartao that = (PagamentoComCartao) o;
        return Objects.equals(numeroDeParcelas, that.numeroDeParcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDeParcelas);
    }
}