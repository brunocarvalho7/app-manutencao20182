package br.ufc.catalogocinemas.CinemaTest;

import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Diretor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.service.CinemaService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaServiceTests {

	@Autowired
	private CinemaService service;
	
    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void adicionarCinemaTest(){
        Cinema cinema = new Cinema("Teste cinema", "Endereço a", "Quixada");

        Assert.assertNotNull(cinema);
    }
    
    @Test
    public void removerCinemaTest(){
        Cinema cinema = new Cinema("Cinema Pinheiro", "Rua 1", "Quixadá");

        service.addCinema(cinema);

        Assert.assertNotNull(service.removerCinema(cinema.getId()));
    }

    @Test
    public void removerCinemarNãoExistenteTest(){
        Assert.assertNull(service.removerCinema(9999));
    }

    @Test
    public void buscarTodosOsCinemas(){
        databaseUtils.deleteAllCinemas();

        service.addCinema(new Cinema("Cinema Pinheiro", "Rua 1", "Quixadá"));
        service.addCinema(new Cinema("Cinemark", "Av. Washington Soares", "Fortaleza"));
        service.addCinema(new Cinema("UCI", "Av. Washington Soares", "Fortaleza"));

        Assert.assertEquals(3, service.buscarTodosOsCinemas().size());
    }

    @Test
    public void erroAoBuscarTodosOsCinemas(){
        databaseUtils.deleteAllCinemas();

        service.addCinema(new Cinema("Cinema Pinheiro", "Rua 1", "Quixadá"));
        service.addCinema(new Cinema("Cinemark", "Av. Washington Soares", "Fortaleza"));
        service.addCinema(new Cinema("UCI", "Av. Washington Soares", "Fortaleza"));

        Assert.assertNotEquals(4, service.buscarTodosOsCinemas().size());
    }

    @Test
    public void atualizarCinemaCorretamente(){
        Cinema cinema = new Cinema("Cinema Pinheiro", "Rua 1", "Quixadá");

        service.addCinema(cinema);

        cinema.setCidade("Cidade atualizada");

        Assert.assertNotNull(service.atualizarCinema(cinema));
    }

    @Test
    public void erroAoAtualizarCinemaNaoExistenteNoBanco(){
        Cinema cinema = new Cinema("Cinema Pinheiro", "Rua 1", "Quixadá");

        service.addCinema(cinema);

        cinema.setId(9999);
        cinema.setNome("Nome atualizado");
        cinema.setEndereco("Endereço atualizado");
        cinema.setCidade("Cidade atualizada");
        
        Assert.assertNull(service.atualizarCinema(cinema));
    }
}
