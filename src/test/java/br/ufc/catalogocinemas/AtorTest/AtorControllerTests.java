package br.ufc.catalogocinemas.AtorTest;

import br.ufc.catalogocinemas.controller.AtorController;
import br.ufc.catalogocinemas.model.Ator;
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
public class AtorControllerTests {

    @Autowired
    AtorController controller;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarAtorCorretamenteTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Ator atorResponse = (Ator) controller.addAtor(ator).getModel().get("ator");

        Assert.assertNotNull(atorResponse);
    }

    @Test
    public void erroAoAdicionarAtorNuloTest(){
        Ator ator= null;

        Ator atorResponse = (Ator) controller.addAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAdicionarAtorComNomeNulaTest(){
        Ator ator = new Ator(null, "oijafoidjifoajsiodfjasiodjfioasjdfoiajsd");

        Ator atorResponse = (Ator) controller.addAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAdicionarAtorComNomeVaziaTest(){
        Ator ator = new Ator("", "oijafoidjifoajsiodfjasiodjfioasjdfoiajsd");

        Ator atorResponse = (Ator) controller.addAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAdicionarAtorComNomeSoComEspacosTest(){
        Ator ator = new Ator("  ", "oijafoidjifoajsiodfjasiodjfioasjdfoiajsd");

        Ator atorResponse = (Ator) controller.addAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void removerAtorCorretamente(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        controller.addAtor(ator);

        Ator atorResponse = (Ator) controller.removerAtor(ator.getId()).getModel().get("ator");

        Assert.assertNotNull(atorResponse);
    }

    @Test
    public void ErroAoRemoverAtorNaoCadastrado(){
        Ator atorResponse = (Ator) controller.removerAtor(9999).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoRemoverAtorComIdNegativo(){
        Ator atorResponse = (Ator) controller.removerAtor(-50).getModel().get("ator");

        Assert.assertNull(atorResponse);    }

    @Test
    public void atualizarAtorCorretamenteTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        controller.addAtor(ator);

        ator.setNome("Bruno");
        ator.setSobre("eu upp");

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNotNull(atorResponse);
    }

    @Test
    public void erroAoAtualizarAtorNuloTest(){
        Ator atorResponse = (Ator) controller.atualizarAtor(null).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAtualizarAtorComNomeNulaTest(){
        Ator ator = new Ator(null, "aosdafiojdfojasiodjfasdf");

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAtualizarAtorComNomeVaziaTest(){
        Ator ator = new Ator("", "aosdafiojdfojasiodjfasdf");

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);

    }
    @Test
    public void erroAoAtualizarAtorComNomeSoComEspacosTest(){
        Ator ator = new Ator("   ", "aosdafiojdfojasiodjfasdf");

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);

    }

    @Test
    public void erroAoAtualizarAtorComIdNaoCadastradoTest(){
        Ator ator = new Ator(" iii ", "aosdafiojdfojasiodjfasdf");


        ator.setId(90);

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void erroAoAtualizarAtorComIdNegativoTest(){
        Ator ator = new Ator(" mIchael", "aosdafiojdfojasiodjfasdf");
        ator.setId(-500);

        Ator atorResponse = (Ator) controller.atualizarAtor(ator).getModel().get("ator");

        Assert.assertNull(atorResponse);
    }

    @Test
    public void buscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        controller.addAtor(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addAtor(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addAtor(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));


        List<Ator> atores = (List<Ator>) controller.getAllAtores().getModel().get("atores");

        Assert.assertEquals(3, atores.size());
    }

    @Test
    public void erroAoBuscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        controller.addAtor(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addAtor(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        controller.addAtor(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));


        List<Ator> atores = (List<Ator>) controller.getAllAtores().getModel().get("atores");

        Assert.assertNotEquals(4, atores.size());
    }
}
