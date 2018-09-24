package br.ufc.catalogocinemas.controller;

import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class AdminController {

    @Autowired
    AdminService sService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView logar(@RequestParam("login") String login, @RequestParam("senha") String senha){
        ModelAndView model = new ModelAndView("login");
        Admin adminResponse = null;

        if(login != null && login.trim().isEmpty() == false){
            if(senha != null && senha.trim().isEmpty() == false){
                adminResponse = sService.logar(login, senha);
                httpSession.setAttribute("usuario", adminResponse);

                if(adminResponse != null){
                    model = new ModelAndView("admin");
                }
            }
        }

        model.addObject("admin", adminResponse);

        return model;
    }

    @RequestMapping(path = "/logoff", method = RequestMethod.POST)
    public ModelAndView deslogar(){
        ModelAndView model = new ModelAndView("index");
        if (httpSession.getAttribute("usuario") != null) {
            httpSession.setAttribute("usuario", null);
            model.addObject("deslogado", true);
        }else{
            model.addObject("deslogado", false);
        }

        return model;
    }

    @RequestMapping(path = "/atualizar", method = RequestMethod.POST)
    public ModelAndView atualizarAdmin(@RequestParam("login") String login, Admin admin){
        ModelAndView model = new ModelAndView("usuario");
        Boolean response   = false;

        if(login != null && login.trim().isEmpty() == false){
            if(admin.getLogin() != null && admin.getLogin().isEmpty() == false){
                if(admin.getSenha() != null && admin.getSenha().isEmpty() == false){
                    response = sService.atualizarAdmin(login, admin);
                }
            }
        }
        model.addObject("atualizado", response);

        return model;
    }

}
