package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/diretor/")
public class DiretorController {

    @Autowired
    DiretorService sService;

    @PostMapping("add")
    public ModelAndView addDiretor(@ModelAttribute Diretor diretor){
        ModelAndView model = new ModelAndView("diretor");

        if(diretor != null && diretor.getNome() != null && diretor.getNome().trim().isEmpty() == false){
            Diretor diretorResponse = sService.adicionarDiretor(diretor);

            model.addObject("diretor", diretorResponse);
            model.getModelMap().addAttribute("msg", "Diretor " + diretorResponse.getNome() + " adicionado com sucesso!!");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar adicionar o diretor!");
        }

        model.addObject("diretores", sService.getAllDiretores());
        return model;
    }

    @RequestMapping(path = "remover/{id}", method = RequestMethod.GET)
    public ModelAndView removerDiretor(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("diretor");

        if(id > 0){
            Diretor diretorResponse = sService.removerDiretor(id);

            if(diretorResponse != null){
                model.addObject("diretor", diretorResponse);
                model.getModelMap().addAttribute("msg", "Diretor " + diretorResponse.getNome() + " removido com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Diretor não localizado!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar remover o diretor!!");
        }

        model.addObject("diretores", sService.getAllDiretores());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarDiretor(@ModelAttribute Diretor diretor){
        ModelAndView model = new ModelAndView("diretor");

        if(diretor != null && diretor.getId() > 0){
            if(diretor.getNome() != null && diretor.getNome().trim().isEmpty() == false){
                Diretor diretorResponse = sService.atualizarDiretor(diretor);

                model.addObject("diretor", diretorResponse);
                model.getModelMap().addAttribute("msg", "Diretor " + diretorResponse.getNome() + " atualizado com sucesso!!");
            }
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar atualizar o gênero!");
        }

        model.addObject("diretores", sService.getAllDiretores());

        return model;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ModelAndView getDiretor(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView("diretor");

        if(id > 0){
            model.addObject("diretor", sService.buscarDiretor(id));
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar localizar o registro");
        }

        model.addObject("diretores", sService.getAllDiretores());

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllDiretores(){
        ModelAndView model = new ModelAndView("diretor");

        model.addObject("diretor", new Diretor());
        model.addObject("diretores", sService.getAllDiretores());

        return model;
    }

}
