package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ator/")
public class AtorController {

    @Autowired
    AtorService sService;

    @PostMapping("add")
    public ModelAndView addAtor(@ModelAttribute Ator ator){
        ModelAndView model = new ModelAndView("ator");

        if(ator != null && ator.getNome() != null && ator.getNome().trim().isEmpty() == false){
            Ator atorResponse = sService.adicionarAtor(ator);

            model.addObject("ator", atorResponse);
            model.getModelMap().addAttribute("msg", "Ator " + atorResponse.getNome() + " adicionado com sucesso!!");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar adicionar o ator!");
        }

        model.addObject("atores", sService.getAllAtores());
        return model;
    }

    @RequestMapping(path = "remover/{id}", method = RequestMethod.GET)
    public ModelAndView removerAtor(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("ator");

        if(id > 0){
            Ator atorResponse = sService.removerAtor(id);

            if(atorResponse != null){
                model.addObject("ator", atorResponse);
                model.getModelMap().addAttribute("msg", "Ator " + atorResponse.getNome() + " removido com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Ator não localizado!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar remover o ator!!");
        }

        model.addObject("atores", sService.getAllAtores());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarAtor(@ModelAttribute Ator ator){
        ModelAndView model = new ModelAndView("ator");

        if(ator != null && ator.getId() > 0){
            if(ator.getNome() != null && ator.getNome().trim().isEmpty() == false){
                Ator atorResponse = sService.atualizarAtor(ator);

                model.addObject("ator", atorResponse);
                model.getModelMap().addAttribute("msg", "Ator " + atorResponse.getNome() + " atualizado com sucesso!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar atualizar o gênero!");
        }

        model.addObject("atores", sService.getAllAtores());

        return model;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ModelAndView getAtor(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("ator");

        if(id > 0){
            model.addObject("ator", sService.buscarAtor(id));
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar localizar o registro");
        }

        model.addObject("atores", sService.getAllAtores());

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllAtores(){
        ModelAndView model = new ModelAndView("ator");

        model.addObject("ator",new Ator());

        model.addObject("atores", sService.getAllAtores());

        return model;
    }
}