package br.ufc.catalogocinemas.AdminTest;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.repository.AdminRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRepositoryTests {

    @Autowired
    AdminRepository repository;

    @Autowired
    DatabaseUtils databaseUtils;

    Admin adminTeste;

    @Before
    public void setUp(){
        adminTeste = new Admin("admin", "1234");

        //Limpar todas as tuplas do banco de dados e inserir um usuário padrão
        databaseUtils.deleteAllAdmin();
        repository.save(adminTeste);
    }

    @Test
    public void adicionarAdminCorretamenteTest(){
        Admin admResponse = repository.save(adminTeste);

        Assert.assertNotNull(admResponse);
    }

    @Test
    public void logarCorretamenteTest(){
        Optional<Admin> adminResponse = repository.logar(adminTeste.getLogin(), adminTeste.getSenha());
        
        Assert.assertTrue(adminResponse.isPresent());
    }

    @Test
    public void falhaAoLogarComLoginIncorretoTest(){
        Optional<Admin> adminResopnse = repository.logar("verificacao", adminTeste.getSenha());

        Assert.assertFalse(adminResopnse.isPresent());
    }

    @Test
    public void falhaAoLogarComSenhaIncorretaTest(){
        Optional<Admin> adminResopnse = repository.logar(adminTeste.getLogin(), "aaaa");

        Assert.assertFalse(adminResopnse.isPresent());
    }

    @Test
    public void atualizarAdminTest(){
        adminTeste.setSenha("1234");

        Admin adminResponse = repository.save(adminTeste);

        Assert.assertEquals(adminTeste, adminResponse);
    }
}
