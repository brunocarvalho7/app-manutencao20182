package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

    Sala findByNome(String nome);

    @Query(value = "SELECT * FROM SALA WHERE CINEMA_ID IS NULL", nativeQuery = true)
    List<Sala> salasDisponiveisParaOCinema(int idCinema);

}
