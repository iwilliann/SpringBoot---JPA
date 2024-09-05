package com.exemplo.tarefa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 5, message = "O título deve ter no mínimo 5 caracteres")
    private String titulo;

    @Column(length = 500)
    private String descricao;

    @NotNull(message = "A data prevista de finalização é obrigatória")
    private LocalDate dataPrevistaFinalizacao;

    private LocalDate dataFinalizacao;

    private boolean finalizado = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPrevistaFinalizacao() {
        return dataPrevistaFinalizacao;
    }

    public void setDataPrevistaFinalizacao(LocalDate dataPrevistaFinalizacao) {
        this.dataPrevistaFinalizacao = dataPrevistaFinalizacao;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}
