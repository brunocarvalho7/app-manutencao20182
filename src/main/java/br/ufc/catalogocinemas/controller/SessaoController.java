package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.mocks.Mocks;
import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.model.Sessao;
import br.ufc.catalogocinemas.service.FilmeService;
import br.ufc.catalogocinemas.service.SalaService;
import br.ufc.catalogocinemas.service.SessaoService;
import br.ufc.catalogocinemas.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/sessao/")
public class SessaoController {

    @Autowired
    private SessaoService sService;

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private SalaService salaService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView todas(){
        ModelAndView model = new ModelAndView("sessao");
        List<Sessao> s = sService.todas();
        if (s.isEmpty()){
            model.addObject("mensagem", "Não há sessão cadastrada");
        }else {
            model.addObject("sessoes", s);
        }

        return model;
    }

    @RequestMapping(path= "/periodo", method = RequestMethod.GET)
    public ModelAndView todasPorData(@RequestParam String dataInicio,@RequestParam String dataFim){
        LocalDate dateStart = LocalDate.parse(dataInicio);
        LocalDate dateEnd   = LocalDate.parse(dataFim);

        ModelAndView model = new ModelAndView("sessao");
        model.addObject("sessoes", sService.todasPorData(dateStart, dateEnd));

        return model;
    }

    @RequestMapping(path = "/cidade")
    public ModelAndView todosPorCidade(String cidade){
        ModelAndView model = new ModelAndView("sessoes");

        if(cidade != null && cidade.trim().isEmpty() == false){
            model.addObject("sessoes", sService.todasPorCidade(cidade));
        }

        return model;
    }

    @RequestMapping(path = "/allFilme", method = RequestMethod.GET)
    public ModelAndView todosPorFilme(@RequestParam("filme") String nomeFilme){
        ModelAndView model = new ModelAndView("sessoes");

        if(nomeFilme != null && nomeFilme.trim().isEmpty() == false){
            model.addObject("sessoes", sService.todosPorFilme(nomeFilme));
        }

        return model;
    }

    public ModelAndView todosPorGenero(String genero){
        return null;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ModelAndView getSessaoPorId(int id){
        ModelAndView model = new ModelAndView("index");
        model.addObject("sessao", sService.getSessaoPorId(id));
        return model;
    }

    @RequestMapping(path = "/update")
    public ModelAndView atualizarSessao(Sessao sessao){
        ModelAndView model = new ModelAndView("sessao");

        String msgRetorno     = Constantes.MSG_SUCESSO_ATUALIZAR_SESSAO;
        Sessao sessaoResponse = sService.atualizarSessao(sessao);

        if(sessaoResponse == null){
            msgRetorno = Constantes.MSG_ERRO_ATUALIZAR_SESSAO;
        }

        model.addObject("sessao", sessaoResponse);
        model.addObject("msg", msgRetorno);

        return model;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ModelAndView addSessao(@RequestParam Integer filme,@RequestParam Integer sala,
                                  @RequestParam String horario,@RequestParam String dataInicio,@RequestParam String dataFim) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter TimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

        Sessao sessao = new Sessao(filmeService.buscarFilmeId(filme),
                salaService.buscarSala(sala), LocalTime.parse(horario, TimeFormatter),
                LocalDate.parse(dataInicio, dateTimeFormatter), LocalDate.parse(dataFim, dateTimeFormatter));

        ModelAndView model = new ModelAndView("index");

        String msgRetorno = Constantes.MSG_SUCESSO_INSERIR_SESSAO;
        Sessao sessaoResponse = sessao;

        try {
            sessaoResponse = sService.addSessao(sessao);
        } catch (ConstraintViolationException e) {
            msgRetorno = Constantes.MSG_ERRO_INSERIR_SESSAO;
        }
        model.addObject("salas", salaService.getAll()) ;
        model.addObject("filmes", filmeService.getAll()) ;
        model.addObject("sessao", sessaoResponse);
        model.addObject("msg", msgRetorno);

        return model;
    }
//    @PostMapping("add")
//    public ModelAndView addSessao(@RequestParam int filme,@RequestParam int sala,
//                                  @RequestParam String horario,@RequestParam String dataInicio,@RequestParam String dataFim){
//
//        ModelAndView model = new ModelAndView("adicionar-sessao");
//
//        if (filme != null && sala != null){
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
//            DateTimeFormatter TimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
//
//            Sessao sessao = new Sessao(filmeService.buscarFilmeId(filme),
//                    salaService.buscarSala(sala), LocalTime.parse(horario, TimeFormatter),
//                    LocalDate.parse(dataInicio, dateTimeFormatter), LocalDate.parse(dataFim, dateTimeFormatter));
//
//        }
//        String msgRetorno     = Constantes.MSG_SUCESSO_INSERIR_SESSAO;
//
//        Sessao sessaoResponse = null;
//
//        try{
//            sessaoResponse = sService.addSessao(sessao);
//        }catch(ConstraintViolationException e){
//            msgRetorno = Constantes.MSG_ERRO_INSERIR_SESSAO;
//        }
//        model.addObject("salas", salaService.getAll()) ;
//        model.addObject("filmes", filmeService.getAll()) ;
//        model.addObject("sessao", sessaoResponse);
//        model.addObject("msg", msgRetorno);
//
//        return model;
//    }

    @RequestMapping(path = "/delete/{id}")
    public ModelAndView removerSessao(int id){
        ModelAndView model = new ModelAndView("sessao");

        String msgRetorno     = Constantes.MSG_SUCESSO_REMOVER_SESSAO;
        Sessao sessaoResponse = sService.removerSessao(id);

        if(sessaoResponse == null){
            msgRetorno = Constantes.MSG_ERRO_REMOVER_SESSAO;
        }

        model.addObject("sessao", sessaoResponse);
        model.addObject("msg", msgRetorno);

        return model;
    }
}