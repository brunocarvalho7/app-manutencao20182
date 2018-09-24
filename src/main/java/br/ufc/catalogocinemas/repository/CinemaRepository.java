package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
	
}
