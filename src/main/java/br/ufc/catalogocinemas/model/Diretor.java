package br.ufc.catalogocinemas.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Diretor extends Pessoa {

    @ManyToMany(mappedBy = "diretores")
    private List<Filme> filmes;

    public Diretor() {
    }

    public Diretor(String nome, String sobre) {
        super(nome, sobre);
        this.filmes = new ArrayList<>();
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public boolean addFilme(Filme filme){
        this.getFilmes().add(filme);
        return filme.adicionarDiretor(this);
    }

    public boolean removerFilme(Filme filme){
        this.getFilmes().remove(filme);
        return filme.removerDiretor(this);
    }
}
