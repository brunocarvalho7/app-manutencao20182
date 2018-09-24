package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Cinema cinemaResponse = sRepository.findOne(id);

        if(cinemaResponse != null)
            sRepository.delete(id);

        return cinemaResponse;
    }

    public Cinema atualizarCinema(Cinema cinema) {
        Cinema cinemaSearch = sRepository.findOne(cinema.getId());

        Cinema cinemaResponse = null;

        if(cinemaSearch != null)
            cinemaResponse = sRepository.save(cinema);

        return cinemaResponse;
    }

    public Cinema buscarCinema(int id) {
        return sRepository.findOne(id);
    }

    public List<Cinema> buscarTodosOsCinemas() {
        return sRepository.findAll();
    }

    public boolean vincularSala(int idSala, int idCinema) {
        try {
            Sala sala = salaService.buscarSala(idSala);

            Cinema cinema = sRepository.findOne(idCinema);

            cinema.vincularSala(sala);
            Cinema cinemaResponse = sRepository.save(cinema);
            Sala salaResponse = salaService.atualizarSala(sala);

            if (cinemaResponse != null && salaResponse != null) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean desvincularSala(int idSala, int idCinema) {
        Sala sala = salaService.buscarSala(idSala);
        Cinema cinema = sRepository.findOne(idCinema);

        cinema.desvincularSala(sala);
        Cinema cinemaResponse = sRepository.save(cinema);
        Sala salaResponse = salaService.atualizarSala(sala);

        if(cinemaResponse != null && salaResponse != null){
            return true;
        }

        return false;
    }
}
