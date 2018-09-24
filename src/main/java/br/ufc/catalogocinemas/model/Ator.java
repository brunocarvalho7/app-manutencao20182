package br.ufc.catalogocinemas.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ator extends Pessoa {

    @ManyToMany(mappedBy = "atores")
    private List<Filme> filmes;

    public Ator() {
    }

    public Ator(String nome, String sobre) {
        super(nome, sobre);
        this.filmes = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getSobre() {
        return super.getSobre();
    }

    @Override
    public void setSobre(String sobre) {
        super.setSobre(sobre);
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public boolean addFilme(Filme filme){
        this.getFilmes().add(filme);
        return filme.adicionarAtor(this);
    }

    public boolean removerFilme(Filme filme){
        this.getFilmes().remove(filme);
        return filme.removerAtor(this);
    }

    @Override
    public String toString() {
        String aux = "";
      /*  for(Filme f:this.filmes){
            aux += f.getId()+" - "+f.getNome();
        }*/

        return "{{ "+super.toString() + aux+" }}";
    }



}
