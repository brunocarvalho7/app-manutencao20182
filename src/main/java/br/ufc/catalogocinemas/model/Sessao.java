package br.ufc.catalogocinemas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Sessao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    private Filme filme;

    @NotNull
    @OneToOne
    private Sala sala;

    @NotNull
    private LocalTime horario;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    public Sessao() {
    }

    public Sessao(Integer id, Filme filme, Sala sala, LocalTime horario, LocalDate dataInicio, LocalDate dataFim) {
        this(filme,sala,horario,dataInicio,dataFim);
        this.id = id;
    }

    public Sessao(Filme filme, Sala sala, LocalTime horario, LocalDate dataInicio, LocalDate dataFim) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", filme=" + filme.getId() +
                ", sala=" + sala.getId() +
                ", horario=" + horario +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }

    public boolean equals(Sessao s) {
       return this.id == s.getId();
    }

}
