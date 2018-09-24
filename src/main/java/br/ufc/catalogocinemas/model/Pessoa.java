package br.ufc.catalogocinemas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
//@DiscriminatorColumn(name = "papel")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nome;
    private String sobre;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String sobre) {
        this.id = id;
        this.nome = nome;
        this.sobre = sobre;
    }


    public Pessoa(String nome, String sobre) {
        this.nome = nome;
        this.sobre = sobre;
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

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return id == pessoa.id;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobre='" + sobre + '\'' +
                '}';
    }
}
