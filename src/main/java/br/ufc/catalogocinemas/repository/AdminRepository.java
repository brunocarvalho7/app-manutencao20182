package br.ufc.catalogocinemas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.catalogocinemas.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

    @Query(value = "select * from admin where login = ?1 and senha = ?2", nativeQuery = true)
    Optional<Admin> logar(String login, String senha);
}
