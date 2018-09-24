package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService{

    @Autowired
    private SalaRepository sRepository;

    public Sala addSala(Sala sala) {
        return sRepository.save(sala);
    }

    public Sala removerSala(int id) {
        Sala salaResponse = sRepository.findOne(id);

        if(salaResponse != null)
            sRepository.delete(id);

        return salaResponse;
    }

    public Sala atualizarSala(Sala sala) {
        Sala salaSearch = sRepository.findOne(sala.getId());

        Sala salaResponse = null;

        if(salaSearch != null)
            salaResponse = sRepository.save(sala);

        return salaResponse;
    }

    public List<Sala> getAll(){
        return sRepository.findAll();
    }
    public Sala buscarSala(Integer id) {
        return sRepository.findOne(id);
    }

    public List<Sala> buscarTodasAsSalas() {
        return sRepository.findAll();
    }

    public Sala buscarSalaNome(String nome) {
        return sRepository.findByNome(nome);
    }

    public List<Sala> salasDisponiveisParaOCinema(int id){
        return sRepository.salasDisponiveisParaOCinema(id);
    }

}
