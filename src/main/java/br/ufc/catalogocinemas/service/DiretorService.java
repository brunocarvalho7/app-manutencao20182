package br.ufc.catalogocinemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.repository.DiretorRepository;

@Service
public class DiretorService {
    @Autowired
    private DiretorRepository sRepository;

    public Diretor adicionarDiretor(Diretor diretor){
        return sRepository.save(diretor);
    }

    public Diretor buscarDiretor(int id){
        return sRepository.findById(id).get();
    }

    public Diretor removerDiretor(int id){
        Optional<Diretor> diretorResponse = sRepository.findById(id);
        if(diretorResponse.isPresent()){
            sRepository.deleteById(id);
            return diretorResponse.get();
        }

        return null;
    }

    public Diretor atualizarDiretor(Diretor diretor){
        Optional<Diretor> diretorSearch = sRepository.findById(diretor.getId());

        Diretor diretorResponse = null;

        if(diretorSearch.isPresent())
            diretorResponse = sRepository.save(diretor);

        return diretorResponse;
    }

    public List<Diretor> getAllDiretores(){
        return sRepository.findAll();
    }
}
