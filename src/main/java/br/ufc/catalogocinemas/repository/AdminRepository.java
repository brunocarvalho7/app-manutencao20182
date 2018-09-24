package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

    @Query(value = "select * from admin where login = ?1 and senha = ?2", nativeQuery = true)
    Admin logar(String login, String senha);
}
