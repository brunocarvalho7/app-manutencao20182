package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository sRepository;

    @Autowired
    private SalaService salaService;

    public Cinema addCinema(Cinema cinema) {
        return sRepository.save(cinema);
    }

    public Cinema removerCinema(int id) {
        Optional<Cinema> cinemaResponse = sRepository.findById(id);

        if(cinemaResponse.isPresent()) {
            sRepository.deleteById(id);
            return cinemaResponse.get();
        }

        return null;
    }

    public Cinema atualizarCinema(Cinema cinema) {
        Optional<Cinema> cinemaSearch = sRepository.findById(cinema.getId());

        Cinema cinemaResponse = null;

        if(cinemaSearch.isPresent())
            cinemaResponse = sRepository.save(cinema);

        return cinemaResponse;
    }

    public Cinema buscarCinema(int id) {
        return sRepository.findById(id).get();
    }

    public List<Cinema> buscarTodosOsCinemas() {
        return sRepository.findAll();
    }

    public boolean vincularSala(int idSala, int idCinema) {
        try {
            Sala sala = salaService.buscarSala(idSala);
            
            Cinema cinema = sRepository.findById(idCinema).get();

            cinema.vincularSala(sala);
            Cinema cinemaResponse = sRepository.save(cinema);
            Sala salaResponse = salaService.atualizarSala(sala);

            if (cinemaResponse != null && salaResponse != null) {
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean desvincularSala(int idSala, int idCinema) {
        Sala sala = salaService.buscarSala(idSala);
        Cinema cinema = sRepository.findById(idCinema).get();

        cinema.desvincularSala(sala);
        Cinema cinemaResponse = sRepository.save(cinema);
        Sala salaResponse = salaService.atualizarSala(sala);

        if(cinemaResponse != null && salaResponse != null){
            return true;
        }

        return false;
    }
}
