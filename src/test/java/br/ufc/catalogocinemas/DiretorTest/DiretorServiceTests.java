package br.ufc.catalogocinemas.DiretorTest;

import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.service.DiretorService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiretorServiceTests {

    @Autowired
    DiretorService service;


    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarDiretorTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Assert.assertNotNull(service.adicionarDiretor(diretor));
    }

    @Test
    public void removerDiretorTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarDiretor(diretor);

        Assert.assertNotNull(service.removerDiretor(diretor.getId()));
    }

    @Test
    public void removerDiretorNãoExistenteTest(){
        Assert.assertNull(service.removerDiretor(9999));
    }

    @Test
    public void buscarTodosOsDiretores(){
        databaseUtils.deleteAllDiretores();

        service.adicionarDiretor(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarDiretor(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarDiretor(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertEquals(3, service.getAllDiretores().size());
    }

    @Test
    public void erroAoBuscarTodosOsDiretores(){
        databaseUtils.deleteAllDiretores();

        service.adicionarDiretor(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarDiretor(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        service.adicionarDiretor(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertNotEquals(4, service.getAllDiretores().size());
    }

    @Test
    public void atualizarDiretorCorretamente(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarDiretor(diretor);

        diretor.setSobre("EU sou atualiado");

        Assert.assertNotNull(service.atualizarDiretor(diretor));
    }

    @Test
    public void erroAoAtualizarAtorNaoExistenteNoBanco(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        service.adicionarDiretor(diretor);

        diretor.setId(9999);
        diretor.setSobre("Sobre atualizado");

        Assert.assertNull(service.atualizarDiretor(diretor));
    }

}
