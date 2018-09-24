package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository sRepository;

    public Genero addGenero(Genero genero){
        return sRepository.save(genero);
    }

    public Genero removerGenero(int id){
        Genero generoResponse = sRepository.findOne(id);

        System.out.println(generoResponse);

        if(generoResponse != null)
            sRepository.delete(id);

        return generoResponse;
    }

    public Genero atualizarGenero(Genero genero){
        Genero generoSearch = sRepository.findOne(genero.getId());

        Genero generoResponse = null;

        if(generoSearch != null)
            generoResponse = sRepository.save(genero);

        return generoResponse;
    }

    public List<Genero> getAllGeneros(){
        return sRepository.findAll();
    }

    public Genero buscarGenero(int id){
        return sRepository.findOne(id);
    }
}
