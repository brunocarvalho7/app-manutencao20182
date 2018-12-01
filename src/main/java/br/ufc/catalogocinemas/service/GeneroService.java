package br.ufc.catalogocinemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.repository.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository sRepository;

    public Genero addGenero(Genero genero){
        return sRepository.save(genero);
    }

    public Genero removerGenero(int id){    	
        Optional<Genero> generoResponse = sRepository.findById(id);

        if(generoResponse.isPresent()) {
        	sRepository.deleteById(id);
        	return generoResponse.get();
        }
           
        return null;
    }

    public Genero atualizarGenero(Genero genero){
        Optional<Genero> generoSearch = sRepository.findById(genero.getId());

        Genero generoResponse = null;

        if(generoSearch.isPresent())
            generoResponse = sRepository.save(genero);

        return generoResponse;
    }

    public List<Genero> getAllGeneros(){
        return sRepository.findAll();
    }

    public Genero buscarGenero(int id){
        return sRepository.findById(id).get();
    }
}
