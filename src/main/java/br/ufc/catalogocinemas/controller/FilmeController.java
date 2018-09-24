package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/filme/")
public class FilmeController {

    @Autowired
    private FilmeService sService;

    @PostMapping("add")
    public ModelAndView addFilme(Filme filme) {
        ModelAndView model = new ModelAndView("filme");

        if(filme != null && filme.getNome() != null && filme.getNome().trim().isEmpty() == false){
            Filme filmeResponse = sService.addFilme(filme);

            model.addObject("filme", filmeResponse);
            model.getModelMap().addAttribute("msg", "Filme " + filmeResponse.getNome() + " adicionado com sucesso!!");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar adicionar o filme!");
        }

        model.addObject("filmes",sService.buscarTodos());
        return model;
    }

    @RequestMapping(path = "remover/{id}")
    public ModelAndView removerFilme(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("filme");

        if(id > 0){
            Filme filmeResponse = sService.removerFilme(id);

            if(filmeResponse != null){
                model.addObject("filme", filmeResponse);
                model.getModelMap().addAttribute("msg", "Filme " + filmeResponse.getNome() + " removido com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Filme não localizado!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar remover o filme!!");
        }

        model.addObject("filmes",sService.buscarTodos());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarFilme(Filme filme) {
        ModelAndView model = new ModelAndView("filme");

        if(filme != null && filme.getId() > 0){
            if(filme.getNome() != null && !filme.getNome().trim().isEmpty()){
                Filme filmeResponse = sService.atualizarFilme(filme);

                model.addObject("filme", filmeResponse);
                model.getModelMap().addAttribute("msg", "Filme " + filme.getNome() + " atualizado com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Informe um nome válido!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar atualizar o filme!");
        }

        model.addObject("filmes",sService.buscarTodos());

        return model;
    }

    @RequestMapping(path = "/{id}")
    public ModelAndView buscarFilmeId(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("filme");

        if(id > 0){
            model.addObject("filme", sService.buscarFilmeId(id));
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar localizar o registro");
        }

        model.addObject("filmes",sService.buscarTodos());

        return model;
    }

    @RequestMapping(path = "/porNome")
    public ModelAndView buscarFilmeNome(@RequestParam("nome") String nome) {
        ModelAndView model = new ModelAndView("filme");

        if(nome != null && nome.trim().isEmpty() == false){
            List<Filme> filmesResponse = sService.buscarFilmeNome(nome);

            model.addObject("filmes", filmesResponse);
        }

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView buscarTodosFilmes(){
        ModelAndView model = new ModelAndView("filme");

        model.addObject("filme",new Filme());
        model.addObject("filmes", sService.buscarTodos());

        return model;
    }
}
