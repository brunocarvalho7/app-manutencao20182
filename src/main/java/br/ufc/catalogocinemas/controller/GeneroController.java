package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/generos/")
public class GeneroController {

    @Autowired
    GeneroService sService;

    @PostMapping("add")
    public ModelAndView addGenero(@ModelAttribute Genero genero){
        ModelAndView model = new ModelAndView("genero");

        if(genero != null && genero.getDescricao() != null && genero.getDescricao().trim().isEmpty() == false){
            Genero generoResponse = sService.addGenero(genero);

            model.addObject("genero", generoResponse);
            model.getModelMap().addAttribute("msg", "Gênero " + generoResponse.getDescricao() + " adicionado com sucesso!!");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar adicionar o gênero!");
        }

        model.addObject("generos", sService.getAllGeneros());

        return model;
    }

    @RequestMapping(path = "remover/{id}", method = RequestMethod.GET)
    public ModelAndView removerGenero(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("genero");

        if(id > 0){
            Genero generoResponse = sService.removerGenero(id);

            if(generoResponse != null){
                model.addObject("genero", generoResponse);
                model.getModelMap().addAttribute("msg", "Gênero " + generoResponse.getDescricao() + " removido com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Gênero não localizado!!");
            }

        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar remover o gênero!!");
        }

        model.addObject("generos", sService.getAllGeneros());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarGenero(@ModelAttribute Genero genero){
        ModelAndView model = new ModelAndView("genero");

        if(genero != null && genero.getId() > 0){
            if(genero.getDescricao() != null && genero.getDescricao().trim().isEmpty() == false){
                Genero generoResponse = sService.atualizarGenero(genero);

                model.addObject("genero", generoResponse);
                model.getModelMap().addAttribute("msg", "Gênero " + generoResponse.getDescricao() + " atualizado com sucesso!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar atualizar o gênero!");
        }

        model.addObject("generos", sService.getAllGeneros());

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllGeneros(){
        ModelAndView model = new ModelAndView("genero");
        model.addObject("genero", new Genero());

        model.addObject("generos", sService.getAllGeneros());

        return model;
    }
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ModelAndView buscarGenero(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("genero");
        if(id > 0){
            model.addObject("genero", sService.buscarGenero(id));
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar localizar o registro");
        }

        model.addObject("generos", sService.getAllGeneros());

        return model;
    }

}
