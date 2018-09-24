package br.ufc.catalogocinemas.AdminTest;

import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.repository.AdminRepository;
import br.ufc.catalogocinemas.service.AdminService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTests {

    @Autowired
    AdminService service;

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
    public void LogarCrretamenteTest(){
        Admin admResponse = service.logar(adminTeste.getLogin(), adminTeste.getSenha());

        Assert.assertNotNull(admResponse);
    }

    @Test
    public void falhaAoLogarComLoginIncorretoTest(){
        Admin adminResopnse = service.logar("verificação", adminTeste.getSenha());

        Assert.assertNull(adminResopnse);
    }

    @Test
    public void falhaAoLogarComSenhaIncorretaTest(){
        Admin adminResopnse = service.logar(adminTeste.getLogin(), "aaaa");

        Assert.assertNull(adminResopnse);
    }

    @Test
    public void atualizarAdminCorretamenteTest(){
        adminTeste.setSenha("4567");
        Boolean response = service.atualizarAdmin(adminTeste.getLogin(), adminTeste);

        Assert.assertTrue(response);
    }

    @Test
    public void falhaAoTentarAtualizarAdminInexistenteTest(){
        adminTeste.setSenha("4567");
        Boolean response = service.atualizarAdmin("verificação", adminTeste);

        Assert.assertFalse(response);
    }

}
