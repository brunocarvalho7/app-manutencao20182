package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiretorService {
    @Autowired
    private DiretorRepository sRepository;

    public Diretor adicionarDiretor(Diretor diretor){
        return sRepository.save(diretor);
    }

    public Diretor buscarDiretor(int id){
        return sRepository.findOne(id);
    }

    public Diretor removerDiretor(int id){
        Diretor diretorResponse = sRepository.findOne(id);
        if(diretorResponse != null){
            sRepository.delete(id);
        }

        return diretorResponse;
    }

    public Diretor atualizarDiretor(Diretor diretor){
        Diretor diretorSearch = sRepository.findOne(diretor.getId());

        Diretor diretorResponse = null;

        if(diretorSearch != null)
            diretorResponse = sRepository.save(diretor);

        return diretorResponse;
    }

    public List<Diretor> getAllDiretores(){
        return sRepository.findAll();
    }
}
