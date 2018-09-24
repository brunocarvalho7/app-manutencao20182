package br.ufc.catalogocinemas.AdminTest;

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
        Admin adminResponse = repository.logar(adminTeste.getLogin(), adminTeste.getSenha());

        Assert.assertNotNull(adminResponse);
    }

    @Test
    public void falhaAoLogarComLoginIncorretoTest(){
        Admin adminResopnse = repository.logar("verificacao", adminTeste.getSenha());

        Assert.assertNull(adminResopnse);
    }

    @Test
    public void falhaAoLogarComSenhaIncorretaTest(){
        Admin adminResopnse = repository.logar(adminTeste.getLogin(), "aaaa");

        Assert.assertNull(adminResopnse);
    }

    @Test
    public void atualizarAdminTest(){
        adminTeste.setSenha("1234");

        Admin adminResponse = repository.save(adminTeste);

        Assert.assertEquals(adminTeste, adminResponse);
    }
}
