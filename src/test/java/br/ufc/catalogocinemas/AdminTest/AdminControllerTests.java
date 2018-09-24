package br.ufc.catalogocinemas.AdminTest;

import br.ufc.catalogocinemas.controller.AdminController;
import br.ufc.catalogocinemas.model.Admin;
import br.ufc.catalogocinemas.repository.AdminRepository;
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
public class AdminControllerTests {

    @Autowired
    AdminController controller;

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
    public void logarCorretamenteTest(){
        Admin adminResponse = (Admin) controller.logar(adminTeste.getLogin(), adminTeste.getSenha()).getModel().get("admin");

        Assert.assertNotNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComLoginIncorretoTest(){
        Admin adminResponse = (Admin) controller.logar("Verificação", adminTeste.getSenha()).getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComSenhaIncorretaTest(){
        Admin adminResponse = (Admin) controller.logar(adminTeste.getLogin(), "4567").getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComLoginVazio(){
        Admin adminResponse = (Admin) controller.logar("", adminTeste.getSenha()).getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComLoginNulo(){
        Admin adminResponse = (Admin) controller.logar(null, adminTeste.getSenha()).getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComSenhaVazia(){
        Admin adminResponse = (Admin) controller.logar(adminTeste.getLogin(), "").getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComSenhaNula(){
        Admin adminResponse = (Admin) controller.logar(adminTeste.getLogin(), null).getModel().get("admin");

        Assert.assertNull(adminResponse);
    }

    @Test
    public void deslogarComSucessoTest(){
        controller.logar(adminTeste.getLogin(), adminTeste.getSenha());

        Boolean response = (Boolean) controller.deslogar().getModel().get("deslogado");

        Assert.assertTrue(response);
    }

    @Test
    public void falhaAoDeslogarSemEstaLogadoTest(){
        Boolean response = (Boolean) controller.deslogar().getModel().get("deslogado");

        Assert.assertFalse(response);
    }

    @Test
    public void atualizarAdminComSucessoTest(){
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin(adminTeste.getLogin(), adminTeste).getModel().get("atualizado");

        Assert.assertTrue(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginInvalidoTest(){
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin("Verificação", adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginNuloTest(){
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin(null, adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginVazioTest(){
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin("", adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginDoObjetoInvalidoTest(){
        String userOriginal = adminTeste.getLogin();

        adminTeste.setLogin("Verificação");
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin(userOriginal, adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginDoObjetoNuloTest(){
        String userOriginal = adminTeste.getLogin();

        adminTeste.setLogin(null);
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin(userOriginal, adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComLoginDoObjetoVazioTest(){
        String userOriginal = adminTeste.getLogin();

        adminTeste.setLogin("");
        adminTeste.setSenha("4567");
        Boolean response = (Boolean) controller.atualizarAdmin(userOriginal, adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComSenhaDoObjetoNuloTest(){
        adminTeste.setSenha(null);
        Boolean response = (Boolean) controller.atualizarAdmin(adminTeste.getLogin(), adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }

    @Test
    public void falhaAoAtualizarAdminComSenhaDoObjetoVazioTest(){
        adminTeste.setSenha("");
        Boolean response = (Boolean) controller.atualizarAdmin(adminTeste.getLogin(), adminTeste).getModel().get("atualizado");

        Assert.assertFalse(response);
    }
}
