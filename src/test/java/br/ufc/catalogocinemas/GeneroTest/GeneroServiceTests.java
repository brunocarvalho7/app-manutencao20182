package br.ufc.catalogocinemas.GeneroTest;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.service.GeneroService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneroServiceTests {

    @Autowired
    GeneroService service;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarGeneroTest(){
        Genero genero = new Genero("Ação");

        Assert.assertNotNull(service.addGenero(genero));
    }

    @Test
    public void removerGeneroTest(){
        Genero genero = new Genero("Drama");

        service.addGenero(genero);

        Assert.assertNotNull(service.removerGenero(genero.getId()));
    }

    @Test
    public void removerGeneroNãoExistenteTest(){
        Assert.assertNull(service.removerGenero(9999));
    }

    @Test
    public void buscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        service.addGenero(new Genero("Ação"));
        service.addGenero(new Genero("Drama"));
        service.addGenero(new Genero("Comédia"));

        Assert.assertEquals(3, service.getAllGeneros().size());
    }

    @Test
    public void erroAoBuscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        service.addGenero(new Genero("Ação"));
        service.addGenero(new Genero("Drama"));
        service.addGenero(new Genero("Comédia"));

        Assert.assertNotEquals(4, service.getAllGeneros().size());
    }

    @Test
    public void atualizarGeneroCorretamente(){
        Genero genero = new Genero("Romanse");

        service.addGenero(genero);

        genero.setDescricao("Romance");

        Assert.assertNotNull(service.atualizarGenero(genero));
    }

    @Test
    public void erroAoAtualizarGeneroNaoExistenteNoBanco(){
        Genero genero = new Genero("Romanse");

        service.addGenero(genero);

        genero.setId(9999);
        genero.setDescricao("Romance");

        Assert.assertNull(service.atualizarGenero(genero));
    }
}
