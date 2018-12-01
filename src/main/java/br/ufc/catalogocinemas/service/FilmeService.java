package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.model.Sessao;
import br.ufc.catalogocinemas.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository sRepository;

    public List<Filme> getAll(){
        return sRepository.findAll();

    }

    public Filme buscarFilmeId(Integer id) {
        return sRepository.findById(id).get();
    }

    public List<Filme> buscarFilmeNome(String nome) {
        return sRepository.findByNome(nome);
    }

    public Filme atualizarFilme(Filme filme) {
        Filme filmeSearch = sRepository.findById(filme.getId()).get();

        Filme filmeResponse = null;

        if(filmeSearch != null)
            filmeResponse = sRepository.save(filme);

        return filmeResponse;
    }

    public Filme addFilme(Filme filme) {
        return sRepository.save(filme);
    }

    public Filme removerFilme(int id) {
        Filme filmeResponse = sRepository.findById(id).get();

        if(filmeResponse != null)
            sRepository.deleteById(id);

        return filmeResponse;
    }

    public List<Filme> buscarTodos(){
        return sRepository.findAll();
    }
}
