package br.ufc.catalogocinemas.AtorTest;

import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.service.AtorService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtorServiceTests {

    @Autowired
    AtorService service;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarAtorTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Assert.assertNotNull(service.adicionarAtor(ator));
    }

    @Test
    public void removerAtorTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarAtor(ator);

        Assert.assertNotNull(service.removerAtor(ator.getId()));
    }

    @Test
    public void removerAtorNãoExistenteTest(){
        Assert.assertNull(service.removerAtor(9999));
    }

    @Test
    public void buscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        service.adicionarAtor(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarAtor(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarAtor(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertEquals(3, service.getAllAtores().size());
    }

    @Test
    public void erroAoBuscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        service.adicionarAtor(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarAtor(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarAtor(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertNotEquals(4, service.getAllAtores().size());
    }

    @Test
    public void atualizarAtorCorretamente(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarAtor(ator);

        ator.setSobre("EU sou atualiado");

        Assert.assertNotNull(service.atualizarAtor(ator));
    }

    @Test
    public void erroAoAtualizarAtorNaoExistenteNoBanco(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarAtor(ator);

        ator.setId(9999);
        ator.setSobre("Sobre atualizado");

        Assert.assertNull(service.atualizarAtor(ator));
    }


}
