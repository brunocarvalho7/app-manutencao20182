package br.ufc.catalogocinemas.SalaTest;

import br.ufc.catalogocinemas.enums.TipoSala;
import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.service.CinemaService;
import br.ufc.catalogocinemas.service.SalaService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaServiceTests {

    @Autowired
    SalaService salaService;

    @Autowired
    CinemaService cinemaService;
    Cinema cinema;

    @Autowired
    DatabaseUtils databaseUtils;

    @Before
    public void setUp(){
        cinema = new Cinema("Pinheiro Quixada", "Avenida A", "Quixadá");
        cinema = cinemaService.addCinema(cinema);
    }
    @After
    public void setDown(){
//        cinemaService.removerCinema(cinema.getId());
    }

    @Test
    public void adicionarSalaTest(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        Assert.assertNotNull(salaService.addSala(sala));
    }


    @Test
    public void removerSalaTest(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        salaService.addSala(sala);

        Assert.assertNotNull(salaService.removerSala(sala.getId()));
    }

    @Test
    public void removerGeneroNãoExistenteTest(){
        Assert.assertNull(salaService.removerSala(9999));
    }

    @Test
    public void buscarTodasAsSalas(){
        databaseUtils.deleteAllSalas();

        salaService.addSala(new Sala("Sala1", TipoSala.SALA_3D, cinema, 100));
        salaService.addSala(new Sala("Sala2", TipoSala.SALA_3D, cinema, 100));
        salaService.addSala(new Sala("Sala3", TipoSala.SALA_3D, cinema, 100));

        Assert.assertEquals(3, salaService.buscarTodasAsSalas().size());
    }

    @Test
    public void erroAoBuscarTodosOsGeneros(){
        databaseUtils.deleteAllSalas();

        salaService.addSala(new Sala("Sala1", TipoSala.SALA_3D, cinema, 100));
        salaService.addSala(new Sala("Sala2", TipoSala.SALA_3D, cinema, 100));
        salaService.addSala(new Sala("Sala3", TipoSala.SALA_3D, cinema, 100));

        Assert.assertNotEquals(4, salaService.buscarTodasAsSalas().size());
    }

    @Test
    public void atualizarGeneroCorretamente(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        salaService.addSala(sala);

        sala.setNome("tt");

        Assert.assertNotNull(salaService.atualizarSala(sala));
    }

    @Test
    public void erroAoAtualizarGeneroNaoExistenteNoBanco(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        salaService.addSala(sala);
        sala.setId(9999);
        sala.setNome("tt");


        Assert.assertNull( salaService.atualizarSala(sala));
    }

}
