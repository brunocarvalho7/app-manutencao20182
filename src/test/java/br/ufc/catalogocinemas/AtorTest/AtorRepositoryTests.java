package br.ufc.catalogocinemas.AtorTest;

import br.ufc.catalogocinemas.model.Ator;
import br.ufc.catalogocinemas.repository.AtorRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtorRepositoryTests {

    @Autowired
    AtorRepository repository;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarAtorTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        Assert.assertNotNull(repository.save(ator));
    }

    @Test
    public void removerAtorTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        repository.save(ator);

        repository.delete(ator.getId());

        Assert.assertNull(repository.findOne(ator.getId()));
    }

    @Test
    public void buscarAtorTest(){
        Ator ator = new Ator("Antonio", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf");

        repository.save(ator);

        Assert.assertNotNull(repository.findOne(ator.getId()));
    }

    @Test
    public void erroAobuscarAtorInexistenteTest(){
        Assert.assertNull(repository.findOne(987));
    }

    @Test
    public void buscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        repository.save(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertEquals(3, repository.findAll().size());
    }

    @Test
    public void erroAoBuscarTodosOsAtores(){
        databaseUtils.deleteAllAtores();

        repository.save(new Ator("Bruno", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Ator("Isaac", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));
        repository.save(new Ator("Iuri", "iasuhfdiuahdfiuahsidfhauishduiffhausidfhuiasduifhaiusdhfiahsiudhfiahsidufhauisdf"));

        Assert.assertNotEquals(4, repository.findAll().size());
    }

}
