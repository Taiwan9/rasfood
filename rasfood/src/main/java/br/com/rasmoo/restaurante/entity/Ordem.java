package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordens")
public class Ordem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @ManyToOne
    private Cliente cliente;

    public Ordem(){}

    public Ordem(Integer id, BigDecimal valorTotal, LocalDate dataCriacao, Cliente cliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataCriacao=" + dataCriacao +
                ", cliente=" + cliente +
                '}';
    }
}
