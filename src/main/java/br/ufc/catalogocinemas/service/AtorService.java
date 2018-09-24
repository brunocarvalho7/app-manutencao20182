package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository sRepository;

    public Ator adicionarAtor(Ator ator){
        return sRepository.save(ator);
    }

    public Ator buscarAtor(int id){
        return sRepository.findOne(id);
    }

    public Ator removerAtor(int id){
        Ator atorResponse = sRepository.findOne(id);
        if(atorResponse != null){
            sRepository.delete(id);
        }

        return atorResponse;
    }

    public Ator atualizarAtor(Ator ator){
        Ator atorSearch = sRepository.findOne(ator.getId());

        Ator atorResponse = null;

        if(atorSearch != null)
            atorResponse = sRepository.save(ator);

        return atorResponse;
    }

    public List<Ator> getAllAtores(){
        return sRepository.findAll();
    }

}
