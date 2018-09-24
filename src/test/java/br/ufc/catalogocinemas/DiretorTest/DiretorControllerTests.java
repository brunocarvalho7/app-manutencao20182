package br.ufc.catalogocinemas.DiretorTest;

import br.ufc.catalogocinemas.controller.DiretorController;
import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiretorControllerTests {
    @Autowired
    DiretorController controller;


    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarDiretorCorretamenteTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.addDiretor(diretor).getModel().get("diretor");

        Assert.assertNotNull(diretorResponse);
    }

    @Test
    public void erroAoAdicionarDiretorNuloTest(){
        Diretor diretor = null;

        Diretor diretorResponse = (Diretor) controller.addDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoAdicionarDiretorComNomeNulaTest(){
        Diretor diretor = new Diretor(null, "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.addDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoAdicionarDiretorComNomeVaziaTest() {
        Diretor diretor = new Diretor("", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.addDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoAdicionarDiretorComNomeSoComEspacosTest(){
            Diretor diretor = new Diretor("   ", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

            Diretor diretorResponse = (Diretor) controller.addDiretor(diretor).getModel().get("diretor");

            Assert.assertNull(diretorResponse);
    }

    @Test
    public void removerDiretorCorretamente(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        controller.addDiretor(diretor);

        Diretor diretorResponse = (Diretor) controller.removerDiretor(diretor.getId()).getModel().get("diretor");

        Assert.assertNotNull(diretorResponse);
    }

    @Test
    public void ErroAoRemoverDiretorNaoCadastrado(){
        Diretor diretorResponse = (Diretor) controller.removerDiretor(9999).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoRemoverDiretorComIdNegativo() {
        Diretor diretorResponse = (Diretor) controller.removerDiretor(-50).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void atualizarDiretorCorretamenteTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");
        controller.addDiretor(diretor);

        diretor.setNome("Bruno");
        diretor.setSobre("eu upp");

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");

        Assert.assertNotNull(diretorResponse);
    }

    @Test
    public void erroAoAtualizarDiretorNuloTest(){
        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(null).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoAtualizarDiretorComNomeNulaTest(){
        Diretor diretor = new Diretor(null, "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);
    }

    @Test
    public void erroAoAtualizarDiretorComNomeVaziaTest(){
        Diretor diretor = new Diretor("", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);

    }
    @Test
    public void erroAoAtualizarDiretorComNomeSoComEspacosTest(){
        Diretor diretor = new Diretor("   ", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");

        Assert.assertNull(diretorResponse);

    }

    @Test
    public void erroAoAtualizarDiretorComIdNaoCadastradoTest(){
        Diretor diretor = new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        diretor.setId(90);

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");


        Assert.assertNull(diretorResponse);

    }

    @Test
    public void erroAoAtualizarAtorComIdNegativoTest(){
        Diretor diretor = new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        diretor.setId(-500);

        Diretor diretorResponse = (Diretor) controller.atualizarDiretor(diretor).getModel().get("diretor");


        Assert.assertNull(diretorResponse);
    }

    @Test
    public void buscarTodosOsAtores(){
        databaseUtils.deleteAllDiretores();

        controller.addDiretor(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addDiretor(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addDiretor(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        List<Diretor> diretores = (List<Diretor>) controller.getAllDiretores().getModel().get("diretores");

        Assert.assertEquals(3, diretores.size());
    }

    @Test
    public void erroAoBuscarTodosOsAtores(){
        databaseUtils.deleteAllDiretores();

        controller.addDiretor(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addDiretor(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addDiretor(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        List<Diretor> diretores = (List<Diretor>) controller.getAllDiretores().getModel().get("diretores");
        Assert.assertNotEquals(4, diretores.size());
    }

}
