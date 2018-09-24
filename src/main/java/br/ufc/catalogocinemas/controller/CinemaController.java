package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.service.CinemaService;
import br.ufc.catalogocinemas.service.SalaService;
import br.ufc.catalogocinemas.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cinema/")
public class CinemaController {
    @Autowired
    private CinemaService sService;

    @Autowired
    private SalaController salaController;

    @PostMapping("add")
    public ModelAndView addCinema(Cinema cinema) {
        ModelAndView model = new ModelAndView("cinema");

        if(cinema != null && cinema.getNome() != null && !cinema.getNome().trim().isEmpty()){
            if(cinema.getEndereco() != null && !cinema.getEndereco().trim().isEmpty()){
                if(cinema.getCidade() != null && !cinema.getCidade().trim().isEmpty()){
                    Cinema cinemaResponse = sService.addCinema(cinema);

                    model.addObject("cinema", cinemaResponse);
                }else{
                    model.getModelMap().addAttribute("msg","Informe uma cidade válida!!");
                }
            }else{
                model.getModelMap().addAttribute("msg","Informe um endereço válido!!");
            }
        }else{
            model.getModelMap().addAttribute("msg","Informe um nome válido!!");
        }

        model.addObject("cinemas",sService.buscarTodosOsCinemas());

        return model;
    }

    @RequestMapping(path = "remover/{id}")
    public ModelAndView removerCinema(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("cinema");

        if(id > 0){
            Cinema cinemaResponse = sService.removerCinema(id);

            if(cinemaResponse != null){
                model.addObject("cinema", cinemaResponse);
                model.addObject("msg","Cinema " +cinemaResponse.getNome()+" removido com sucesso!!");
            }else{
                model.getModelMap().addAttribute("Cinema não localizado!!");
            }
        }else{
            model.getModelMap().addAttribute("msg","Código inválido!!");
        }

        model.addObject("cinemas",sService.buscarTodosOsCinemas());

        return model;
    }

    @PostMapping("atualizar")
    public ModelAndView atualizarCinema(Cinema cinema) {
        ModelAndView model = new ModelAndView("cinema");

        if(cinema != null && cinema.getNome() != null && !cinema.getNome().trim().isEmpty()){
            if(cinema.getEndereco() != null && !cinema.getEndereco().trim().isEmpty()){
                if(cinema.getCidade() != null && !cinema.getCidade().trim().isEmpty()){
                    Cinema cinemaResponse = sService.atualizarCinema(cinema);

                    model.addObject("cinema", cinemaResponse);
                    model.getModelMap().addAttribute("msg", "Cinema " + cinemaResponse.getNome() + " atualizado com sucesso!!");
                }else{
                    model.getModelMap().addAttribute("msg","Informe uma cidade válida!!");
                }
            }else{
                model.getModelMap().addAttribute("msg","Informe um endereço válido!!");
            }
        }else{
            model.getModelMap().addAttribute("msg","Informe um nome válido!!");
        }

        model.addObject("cinemas", sService.buscarTodosOsCinemas());

        return model;
    }

    @RequestMapping(path = "/{id}")
    public ModelAndView buscarCinema(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView("cinema");

        if(id > 0){
            model.addObject("cinema", sService.buscarCinema(id));
            model.addObject("comando", "atualizar");
        }else{
            model.getModelMap().addAttribute("msg", "Informe um código válido!!");
        }

        model.addObject("salasDisponiveis", salaController.salasDisponiveisParaOCinema(id));
        model.addObject("cinemas",sService.buscarTodosOsCinemas());

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView buscarTodosOsCinemas() {
        ModelAndView model = new ModelAndView("cinema");

        model.addObject("cinema", new Cinema());

        model.addObject("cinemas", sService.buscarTodosOsCinemas());

        return model;
    }

    @RequestMapping(path = "/vincular")
    public ModelAndView vincularSala(@RequestParam("idSala") int idSala, @RequestParam("idCinema") int idCinema) {
        ModelAndView model = new ModelAndView("cinema");

        System.out.println("ID sala: "+idSala);
        System.out.println("ID Cinema:" +idCinema);

        if(idSala > 0 && idCinema > 0){
            if(sService.vincularSala(idSala, idCinema)){
                model.addObject("msg", Constantes.MSG_SUCESSO_VINCULAR_SALA_CINEMA);
            }else{
                model.addObject("msg", Constantes.MSG_ERRO_VINCULAR_SALA_CINEMA);
            }
        }else{
            model.addObject("msg", Constantes.MSG_ERRO_VINCULAR_SALA_CINEMA);
        }

        model.addObject("cinema", sService.buscarCinema(idCinema));
        model.addObject("cinemas",sService.buscarTodosOsCinemas());
        model.addObject("comando", "atualizar");

        return model;
    }

    @RequestMapping(path = "/desvincular/{idSala}/{idCinema}")
    public ModelAndView desvincularSala(@PathVariable("idSala") int idSala, @PathVariable("idCinema") int idCinema) {
        ModelAndView model = new ModelAndView("cinema");

        if(idSala > 0 && idCinema > 0){
            if(sService.desvincularSala(idSala, idCinema)){
                model.addObject("msg", Constantes.MSG_SUCESSO_DESVINCULAR_SALA_CINEMA);
            }else{
                model.addObject("msg", Constantes.MSG_ERRO_DESVINCULAR_SALA_CINEMA);
            }
        }else{
            model.addObject("msg", Constantes.MSG_ERRO_DESVINCULAR_SALA_CINEMA);
        }

        model.addObject("cinema", sService.buscarCinema(idCinema));
        model.addObject("cinemas",sService.buscarTodosOsCinemas());
        model.addObject("comando", "atualizar");

        return model;
    }

}
