package br.ufc.catalogocinemas.CinemaTest;

import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.repository.CinemaRepository;
import br.ufc.catalogocinemas.repository.SalaRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaRepositoryTests {

    @Autowired
    CinemaRepository repository;

    @Autowired
    SalaRepository salaRepository;

    Cinema cinemaTest;
    
    @Autowired
    DatabaseUtils databaseUtils;
    
    @Before
    public void setUp(){
        cinemaTest = new Cinema("Pinheiro Fortaleza", "Algum lugar de fortaleza", "Fortaleza");
    }

    @Test
    public void salvarCinemaCorretamenteTest(){
        Cinema cinemaResponse = repository.save(cinemaTest);

        Assert.assertNotNull(cinemaResponse);
    }  
    
    @Test
    public void removerCinemaCorretamenteTest(){
    	
    	Cinema cinema = new Cinema("Pinheiro", "Avenida da Universidade", "Quixadá");
    	
    	repository.save(cinema);
    	
    	repository.delete(cinema);
    	
    	Assert.assertNull(repository.findOne(cinema.getId()));
    	
    }
    
    @Test
    public void buscarCinemaTest(){
    	Cinema cinema = new Cinema("Pinheiro", "Avenida da Universidade", "Quixadá");
    	
    	repository.save(cinema);
    	
    	Assert.assertNotNull(repository.findOne(cinema.getId()));
    }
    
    @Test
    public void erroAoBuscarCinemaInexistenteTest(){
    	Assert.assertNull(repository.findOne(900));
    }
    
    @Test
    public void buscarTodosOsCinemasTest(){
    	databaseUtils.deleteAllCinemas();
    	
    	repository.save(new Cinema("Pinheiro", "Avenida da Universidade", "Quixadá"));
    	repository.save(new Cinema("UCI Iguatemi", "Avenida WS", "Fortaleza"));
    	repository.save(new Cinema("Kinoplex Via Sul", "Avenida WS", "Fortaleza"));
    	
    	Assert.assertEquals(3, repository.findAll().size());
    }
    
    public void erroAoBuscarTodosOsCinemasTest(){
    	databaseUtils.deleteAllCinemas();
    	
    	repository.save(new Cinema("Pinheiro", "Avenida da Universidade", "Quixadá"));
    	repository.save(new Cinema("UCI Iguatemi", "Avenida WS", "Fortaleza"));
    	repository.save(new Cinema("Kinoplex Via Sul", "Avenida WS", "Fortaleza"));
    	
    	Assert.assertNotEquals(4, repository.findAll().size());
    }
     
}
