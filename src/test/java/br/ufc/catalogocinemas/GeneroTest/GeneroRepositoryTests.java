package br.ufc.catalogocinemas.GeneroTest;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.repository.GeneroRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneroRepositoryTests {

    @Autowired
    GeneroRepository repository;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarGeneroTest(){
        Genero genero = new Genero("Ação");

        Assert.assertNotNull(repository.save(genero));
    }

    @Test
    public void removerGeneroTest(){
        Genero genero = new Genero("Drama");

        repository.save(genero);

        repository.delete(genero.getId());

        Assert.assertNull(repository.findOne(genero.getId()));
    }

    @Test
    public void buscarGeneroTest(){
        Genero genero = new Genero("Drama");

        repository.save(genero);

        Assert.assertNotNull(repository.findOne(genero.getId()));
    }

    @Test
    public void erroAobuscarGeneroInexistenteTest(){
        Assert.assertNull(repository.findOne(987));
    }

    @Test
    public void buscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        repository.save(new Genero("Ação"));
        repository.save(new Genero("Drama"));
        repository.save(new Genero("Comédia"));

        Assert.assertEquals(3, repository.findAll().size());
    }

    @Test
    public void erroAoBuscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        repository.save(new Genero("Ação"));
        repository.save(new Genero("Drama"));
        repository.save(new Genero("Comédia"));

        Assert.assertNotEquals(4, repository.findAll().size());
    }



}
