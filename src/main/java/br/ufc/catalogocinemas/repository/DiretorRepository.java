package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DiretorRepository extends JpaRepository<Diretor, Integer>{
}
