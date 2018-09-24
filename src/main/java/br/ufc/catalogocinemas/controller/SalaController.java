package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sala/")
public class SalaController {

    @Autowired
    private SalaService sService;

    @PostMapping("add")
    public ModelAndView addSala(Sala sala) {
        ModelAndView model = new ModelAndView("sala");

        if(sala != null && sala.getTipo() != null && sala.getCapacidade() > 0){
            Sala salaResponse = sService.addSala(sala);

            model.addObject("sala", salaResponse);
            model.getModelMap().addAttribute("msg", "Sala "+salaResponse.getNome()+ " adicionada com sucesso!");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar adicionar a sala!");
        }

        model.addObject("salas", sService.buscarTodasAsSalas());

        return model;
    }

    @RequestMapping(path = "remover/{id}")
    public ModelAndView removerSala(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("sala");

        if(id > 0){
            Sala salaResponse = sService.removerSala(id);

            if(salaResponse != null){
                model.addObject("sala", salaResponse);
                model.getModelMap().addAttribute("msg", "Sala " + salaResponse.getNome() + " removida com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg", "Sala não localizada!!");
            }

            model.addObject("sala", salaResponse);
        }else{
            model.getModelMap().addAttribute("msg","Erro ao tentar remover a sala!!");
        }

        model.addObject("salas", sService.buscarTodasAsSalas());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarSala(Sala sala) {
        ModelAndView model = new ModelAndView("sala");

        if(sala != null && sala.getId() > 0){
            if(sala.getCapacidade() > 0){
                Sala salaResponse = sService.atualizarSala(sala);

                model.addObject("sala", salaResponse);
                model.getModelMap().addAttribute("msg", "Sala " + sala.getNome() + " atualizada com sucesso!!");
            }else{
                model.getModelMap().addAttribute("msg","Informe uma capacidade válida!!");
            }
        }else{
            model.getModelMap().addAttribute("msg","Erro ao tentar atualizar a sala!");
        }

        model.addObject("salas", sService.buscarTodasAsSalas());

        return model;
    }

    @RequestMapping(path = "{id}")
    public ModelAndView buscarSalaId(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("sala");

        if(id > 0){
            Sala salaResponse = sService.buscarSala(id);

            model.addObject("sala", salaResponse);
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Erro ao tentar localizar o registro");
        }

        model.addObject("salas",sService.buscarTodasAsSalas());

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView buscarTodasAsSalas() {
        ModelAndView model = new ModelAndView("sala");

        model.addObject("sala", new Sala());

        model.addObject("salas", sService.buscarTodasAsSalas());

        return model;
    }

    @RequestMapping(path = "buscarPorNome/{nome}")
    public ModelAndView buscarSalaNome(@PathVariable String nome) {
        ModelAndView model = new ModelAndView("sala");

        if(nome != null && nome.trim().isEmpty() == false){
            model.addObject("sala", sService.buscarSalaNome(nome));
        }else{
            model.getModelMap().addAttribute("msg","Informe um nome válido!!");
        }

        model.addObject("salas", sService.buscarSalaNome(nome));

        return model;
    }

    public List<Sala> salasDisponiveisParaOCinema(int idCinema){
        return sService.salasDisponiveisParaOCinema(idCinema);
    }

}
