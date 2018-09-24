package br.ufc.catalogocinemas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "generos")
    private List<Filme> filmes;

    @NotNull
    private String descricao;

    public Genero(){

    }

   /* public Genero(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.filmes = new ArrayList<>();
    }*/

    public Genero(String descricao) {
        this.descricao = descricao;
        this.filmes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean adicionarFilme(Filme filme){
        this.filmes.add(filme);
        return filme.adicionarGenero(this);
    }

    public boolean removerFilme(Filme filme){
        this.filmes.remove(filme);
        return filme.removerGenero(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genero genero = (Genero) o;

        return id == genero.id;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
