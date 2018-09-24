package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.mocks.Mocks;
import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.model.Sala;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PrincipalController {
 //implements ErrorController
	
	@RequestMapping(path = "/index/")
    public String index(){
        return "index";
    }

//
//    @RequestMapping(path = "/error")
//    public ModelAndView getError(){
//        //TODO PAGINA DE ERRO
//        ModelAndView model = new ModelAndView("index");
//        model.getModelMap().addAttribute("msg", "Ocorreu um erro ao tentar realizar a solicitação desejada");
//
//        return model;
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
