package com.projetospring.apijava.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projetospring.apijava.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) &&
                estado == pagamento.estado &&
                Objects.equals(pedido, pagamento.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado, pedido);
    }
}