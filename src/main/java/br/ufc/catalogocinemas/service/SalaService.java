package br.ufc.catalogocinemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.SalaRepository;

@Service
public class SalaService{

    @Autowired
    private SalaRepository sRepository;

    public Sala addSala(Sala sala) {
        return sRepository.save(sala);
    }

    public Sala removerSala(int id) {
        Optional<Sala> salaResponse = sRepository.findById(id);

        if(salaResponse.isPresent()) {
        	sRepository.deleteById(id);
        	return salaResponse.get();
        }
            
        return null;
    }

    public Sala atualizarSala(Sala sala) {
        Optional<Sala> salaSearch = sRepository.findById(sala.getId());

        Sala salaResponse = null;

        if(salaSearch.isPresent())
            salaResponse = sRepository.save(sala);

        return salaResponse;
    }

    public List<Sala> getAll(){
        return sRepository.findAll();
    }
    public Sala buscarSala(Integer id) {
        return sRepository.findById(id).get();
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
