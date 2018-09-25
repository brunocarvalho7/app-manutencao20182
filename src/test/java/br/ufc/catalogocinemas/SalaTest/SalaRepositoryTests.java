package br.ufc.catalogocinemas.SalaTest;

import br.ufc.catalogocinemas.enums.TipoSala;
import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.SalaRepository;
import br.ufc.catalogocinemas.service.CinemaService;
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
public class SalaRepositoryTests {

    @Autowired
    SalaRepository repository;

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

        Assert.assertNotNull(repository.save(sala));
    }

    @Test
    public void removerSalaTest(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        repository.save(sala);

        repository.delete(sala.getId());

        Assert.assertNull(repository.findOne(sala.getId()));
    }

    @Test
    public void buscarSalaTest(){
        Sala sala = new Sala("Sala1", TipoSala.SALA_3D, cinema, 100);

        repository.save(sala);

        Assert.assertNotNull(repository.findOne(sala.getId()));
    }

    @Test
    public void erroAobuscarSalaInexistenteTest(){
        Assert.assertNull(repository.findOne(987));
    }

    @Test
    public void buscarTodasAsSalas(){
        databaseUtils.deleteAllSalas();

        repository.save(new Sala("Sala1", TipoSala.SALA_3D, cinema, 100));
        repository.save(new Sala("Sala2", TipoSala.SALA_3D, cinema, 100));
        repository.save(new Sala("Sala3", TipoSala.SALA_3D, cinema, 100));

        Assert.assertEquals(3, repository.findAll().size());
    }

    @Test
    public void erroAoBuscarTodasAsSalas(){
        databaseUtils.deleteAllSalas();

        repository.save(new Sala("Sala1", TipoSala.SALA_3D, cinema, 100));
        repository.save(new Sala("Sala2", TipoSala.SALA_3D, cinema, 100));
        repository.save(new Sala("Sala3", TipoSala.SALA_3D, cinema, 100));


        Assert.assertNotEquals(4, repository.findAll().size());
    }




}
