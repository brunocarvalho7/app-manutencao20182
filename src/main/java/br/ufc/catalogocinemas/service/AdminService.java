package br.ufc.catalogocinemas.service;

import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository sRepository;

    public Admin logar(String login, String senha){
        return sRepository.logar(login, senha);
    }

    public boolean atualizarAdmin(String login, Admin admin){
        if(sRepository.findOne(login) != null && sRepository.findOne(admin.getLogin()) != null){
            if(sRepository.save(admin) != null){
                return true;
            }
        }

        return false;
    }

}
