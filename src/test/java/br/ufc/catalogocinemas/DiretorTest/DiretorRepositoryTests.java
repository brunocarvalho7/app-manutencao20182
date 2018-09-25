package br.ufc.catalogocinemas.DiretorTest;

import br.ufc.catalogocinemas.model.Diretor;
import br.ufc.catalogocinemas.repository.DiretorRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiretorRepositoryTests {

    @Autowired
    DiretorRepository repository;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarDiretorTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Assert.assertNotNull(repository.save(diretor));
    }

    @Test
    public void removerDiretorTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        repository.save(diretor);

        repository.delete(diretor.getId());

        Assert.assertNull(repository.findOne(diretor.getId()));
    }

    @Test
    public void buscarDiretorTest(){
        Diretor diretor = new Diretor("Michaelll", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        repository.save(diretor);

        Assert.assertNotNull(repository.findOne(diretor.getId()));
    }

    @Test
    public void erroAobuscarDiretorInexistenteTest(){
        Assert.assertNull(repository.findOne(987));
    }

    @Test
    public void buscarTodosOsDiretores(){
        databaseUtils.deleteAllDiretores();

        repository.save(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertEquals(3, repository.findAll().size());
    }

    @Test
    public void erroAoBuscarTodosOsDiretores(){
        databaseUtils.deleteAllDiretores();

        repository.save(new Diretor("Michaell", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Diretor("Jacksonn", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Diretor("Timm Maia", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

    }
}
