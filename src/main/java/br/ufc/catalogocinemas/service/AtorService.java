package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtorService {

    @Autowired
    private AtorRepository sRepository;

    public Ator adicionarAtor(Ator ator){
        return sRepository.save(ator);
    }

    public Ator buscarAtor(int id){
        return sRepository.findById(id).get();
    }

    public Ator removerAtor(int id){
        Optional<Ator> atorResponse = sRepository.findById(id);
        if(atorResponse.isPresent()){
            sRepository.deleteById(id);
            return atorResponse.get();
        }

        return null;
    }

    public Ator atualizarAtor(Ator ator){
        Optional<Ator> atorSearch = sRepository.findById(ator.getId());

        Ator atorResponse = null;

        if(atorSearch.isPresent())
            atorResponse = sRepository.save(ator);

        return atorResponse;
    }

    public List<Ator> getAllAtores(){
        return sRepository.findAll();
    }

}
