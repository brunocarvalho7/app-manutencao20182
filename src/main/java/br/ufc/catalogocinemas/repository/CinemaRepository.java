package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
	
}
