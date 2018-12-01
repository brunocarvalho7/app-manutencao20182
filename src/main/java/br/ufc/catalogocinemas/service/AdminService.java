package br.ufc.catalogocinemas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository sRepository;

    public Admin logar(String login, String senha){
    	Optional<Admin> adminResponse = sRepository.logar(login, senha);
    	
    	if(adminResponse.isPresent()) {
    		return adminResponse.get();
    	}
    	
        return null;
    }

    public boolean atualizarAdmin(String login, Admin admin){
        if(sRepository.findById(login).isPresent() && sRepository.findById(admin.getLogin()).isPresent()){
            if(sRepository.save(admin) != null){
                return true;
            }
        }

        return false;
    }

}
