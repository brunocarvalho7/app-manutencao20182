package br.ufc.catalogocinemas.GeneroTest;

import br.ufc.catalogocinemas.controller.GeneroController;
import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneroControllerTests {

    @Autowired
    GeneroController controller;

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarGeneroCorretamenteTest(){
        Genero genero = new Genero("Ação");

        Genero generoResponse = (Genero) controller.addGenero(genero).getModel().get("genero");

        Assert.assertNotNull(generoResponse);
    }

    @Test
    public void erroAoAdicionarGeneroNuloTest(){
        Genero genero = null;

        Genero generoResponse = (Genero) controller.addGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoAdicionarGeneroComDescricaoNulaTest(){
        Genero genero = new Genero(null);

        Genero generoResponse = (Genero) controller.addGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoAdicionarGeneroComDescricaoVaziaTest(){
        Genero genero = new Genero("");

        Genero generoResponse = (Genero) controller.addGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }
    @Test
    public void erroAoAdicionarGeneroComDescricaoSoComEspacosTest(){
        Genero genero = new Genero("  ");

        Genero generoResponse = (Genero) controller.addGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void removerGeneroCorretamente(){
        Genero genero = new Genero("Terror");

        controller.addGenero(genero);

        Genero generoResponse = (Genero) controller.removerGenero(genero.getId()).getModel().get("genero");

        Assert.assertNotNull(generoResponse);
    }

    @Test
    public void ErroAoRemoverGeneroNaoCadastrado(){
        Genero generoResponse = (Genero) controller.removerGenero(9999).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoRemoverGeneroComIdNegativo(){
        Genero generoResponse = (Genero) controller.removerGenero(-50).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void atualizarGeneroCorretamenteTest(){
        Genero genero = new Genero("Assão");

        controller.addGenero(genero);

        genero.setDescricao("Ação");

        Genero generoResponse = (Genero) controller.atualizarGenero(genero).getModel().get("genero");

        Assert.assertNotNull(generoResponse);
    }

    @Test
    public void erroAoAtualizarGeneroNuloTest(){
        Genero generoResponse = (Genero) controller.atualizarGenero(null).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoAtualizarGeneroComDescricaoNulaTest(){
        Genero genero = new Genero(null);

        Genero generoResponse = (Genero) controller.atualizarGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoAtualizarGeneroComDescricaoVaziaTest(){
        Genero genero = new Genero("");

        Genero generoResponse = (Genero) controller.atualizarGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }
    @Test
    public void erroAoAtualizarGeneroComDescricaoSoComEspacosTest(){
        Genero genero = new Genero("  ");

        Assert.assertNull(controller.atualizarGenero(genero).getModel().get("genero"));
    }

    @Test
    public void erroAoAtualizarGeneroComIdNaoCadastradoTest(){
        Genero genero = new Genero("  ");

        genero.setId(90);

        Genero generoResponse = (Genero) controller.atualizarGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void erroAoAtualizarGeneroComIdNegativoTest(){
        Genero genero = new Genero("Ação");
        genero.setId(-500);

        Genero generoResponse = (Genero) controller.atualizarGenero(genero).getModel().get("genero");

        Assert.assertNull(generoResponse);
    }

    @Test
    public void buscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        controller.addGenero(new Genero("Ação"));
        controller.addGenero(new Genero("Drama"));
        controller.addGenero(new Genero("Comédia"));

        List<Genero> generos = (List<Genero>) controller.getAllGeneros().getModel().get("generos");

        Assert.assertEquals(3, generos.size());
    }

    @Test
    public void erroAoBuscarTodosOsGeneros(){
        databaseUtils.deleteAllGeneros();

        controller.addGenero(new Genero("Ação"));
        controller.addGenero(new Genero("Drama"));
        controller.addGenero(new Genero("Comédia"));

        List<Genero> generos = (List<Genero>) controller.getAllGeneros().getModel().get("generos");

        Assert.assertNotEquals(4, generos.size());
    }
}
