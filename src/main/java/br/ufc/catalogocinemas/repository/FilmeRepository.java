package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    List<Filme> findByNome(String nome);

}
