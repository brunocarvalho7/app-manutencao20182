package br.ufc.catalogocinemas.enums;

public enum TipoSala {
    SALA_2D("Sala 2D"), SALA_3D("Sala 3D");

    private String descricao;

    TipoSala(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public String toString(){
        return descricao;
    }
}
