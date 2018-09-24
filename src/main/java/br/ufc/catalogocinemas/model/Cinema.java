package br.ufc.catalogocinemas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cinema{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    @NotNull
    private String cidade;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Sala> salas;

    public Cinema(){
    }

    public Cinema(String nome, String endereco, String cidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.salas  = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public boolean vincularSala(Sala sala){
        if(sala.getCinema() != this){
            sala.setCinema(this);
        }
        return this.salas.add(sala);
    }

    public boolean desvincularSala(Sala sala){
        sala.setCinema(null);
        return this.salas.remove(sala);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cinema cinema = (Cinema) o;

        return id == cinema.id;
    }

    @Override
    public String toString() {
        return nome + " - " + cidade;
    }
}