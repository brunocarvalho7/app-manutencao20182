package br.ufc.catalogocinemas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nome;

    private String sinopse;
    private Integer duracao;

    @ManyToMany
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "filme_atores",
            joinColumns = @JoinColumn(name="filme_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id", referencedColumnName = "id"))
    private List<Ator> atores;

    @ManyToMany
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "filme_diretores",
            joinColumns = @JoinColumn(name="filme_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diretor_id", referencedColumnName = "id"))
    private List<Diretor> diretores;

    @ManyToMany
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "filme_generos",
            joinColumns = @JoinColumn(name="filme_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"))
    private List<Genero> generos;

    public Filme() {
    }

    public Filme(String nome, String sinopse, Integer duracao) {
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.generos = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.diretores = new ArrayList<>();
    }

    public Filme(String nome, String sinopse, Integer duracao, List<Ator> atores, List<Diretor> diretores, List<Genero> generos) {
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.atores = atores;
        this.diretores = diretores;
        this.generos = generos;
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

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public boolean adicionarAtor(Ator ator){
        return this.getAtores().add(ator);
    }

    public boolean removerAtor(Ator ator){
        return this.getAtores().remove(ator);
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public boolean adicionarDiretor(Diretor diretor){
        return this.getDiretores().add(diretor);
    }

    public boolean removerDiretor(Diretor diretor){
        return this.getDiretores().remove(diretor);
    }

    public List<Genero> getGenero() {
        return generos;
    }

    public boolean adicionarGenero(Genero genero){
        return this.generos.add(genero);
    }

    public boolean removerGenero(Genero genero){
        return this.generos.remove(genero);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filme filme = (Filme) o;

        return id == filme.id;
    }

    @Override
    public String toString() {
        return nome;
    }
}
