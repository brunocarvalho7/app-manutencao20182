package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer>{

}
