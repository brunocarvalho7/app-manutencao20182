package br.ufc.catalogocinemas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Admin {
    @Id
    private String login;

    @NotNull
    private String senha;

    public Admin(){
    }

    public Admin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (login != null ? !login.equals(admin.login) : admin.login != null) return false;
        return senha != null ? senha.equals(admin.senha) : admin.senha == null;
    }
}
