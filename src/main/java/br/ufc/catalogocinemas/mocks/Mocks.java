package br.ufc.catalogocinemas.mocks;

import br.ufc.catalogocinemas.CatalogoCinemasApplication;
import br.ufc.catalogocinemas.controller.CinemaController;
import br.ufc.catalogocinemas.controller.FilmeController;
import br.ufc.catalogocinemas.controller.GeneroController;
import br.ufc.catalogocinemas.controller.SalaController;
import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.model.Genero;
import br.ufc.catalogocinemas.model.Sala;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.injection.MockInjection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mocks {

    static{
        MockitoAnnotations.initMocks(CatalogoCinemasApplication.class);

        initGeneroControllerMock();
        initFilmeControllerMock();
        initCinemaControllerMock();
        initSalaControllerMock();
    }

    private static FilmeController filmeControllerMock;
    private static CinemaController cinemaController;
    private static GeneroController generoController;
    private static SalaController salaController;

    private static void initFilmeControllerMock(){
        /*filmeControllerMock = Mockito.mock(FilmeController.class);

        Filme filme1 = new Filme(1);//, "Acampamento do Papai");
        Filme filme2 = new Filme(2);//, "O Espetacular Homem-Aranha");
        Filme filme3 = new Filme(3);//, "Karate Kid");

        Mockito.when(filmeControllerMock.buscarFilmeId(2)).thenReturn(filme1);
        Mockito.when(filmeControllerMock.buscarFilmeNome("Acampamento do Papai")).thenReturn(filme1);

        Mockito.when(filmeControllerMock.buscarFilmeId(1)).thenReturn(filme2);
        Mockito.when(filmeControllerMock.buscarFilmeNome("O Espetacular Homem-Aranha")).thenReturn(filme2);

        Mockito.when(filmeControllerMock.buscarFilmeId(3)).thenReturn(filme3);
        Mockito.when(filmeControllerMock.buscarFilmeNome("Karate Kid")).thenReturn(filme3);

        List<Filme> filmes = new ArrayList<>(Arrays.asList(filme1, filme2, filme3));

        Mockito.when(filmeControllerMock.buscarTodosOsFilmes()).thenReturn(filmes);*/
    }

    private static void initSalaControllerMock(){
        /*salaController = Mockito.mock(SalaController.class);

        Sala sala1 = new Sala(1);
        Sala sala2 = new Sala(2);
        Sala sala3 = new Sala(3);

        List<Sala> salasQuixada   = new ArrayList<>(Arrays.asList(sala1, sala2));
        List<Sala> salasFortaleza = new ArrayList<>(Arrays.asList(sala3));
        List<Sala> salas = new ArrayList<>(Arrays.asList(sala1, sala2, sala3));

        Mockito.when(salaController.buscarSalasPorCidade("Quixada")).thenReturn(salasQuixada);
        Mockito.when(salaController.buscarSalasPorCidade("Fortaleza")).thenReturn(salasFortaleza);

        Mockito.when(salaController.buscarTodasAsSalas()).thenReturn(salas);

      /*  Mockito.when(salaController.buscarSalaId(1)).thenReturn(sala1);
        Mockito.when(salaController.buscarSalaId(2)).thenReturn(sala2);
        Mockito.when(salaController.buscarSalaId(3)).thenReturn(sala3);*/
    }

    private static void initCinemaControllerMock(){
        cinemaController = Mockito.mock(CinemaController.class);

        //Mockito.when(cinemaController.buscarCinema(1)).thenReturn(new Cinema());
    }

    private static void initGeneroControllerMock() {
        /*generoController = Mockito.mock(GeneroController.class);

        Genero generoA = new Genero(1,"Ação");
        Genero generoB = new Genero(2,"Drama");
        Genero generoC = new Genero(3,"Fantasia");

        List<Genero> generos = new ArrayList<>(Arrays.asList(generoA, generoB, generoC));

        Mockito.when(generoController.buscarGeneroId(1)).thenReturn(generoA);
        Mockito.when(generoController.buscarGeneroId(2)).thenReturn(generoB);
        Mockito.when(generoController.buscarGeneroId(3)).thenReturn(generoC);


        Mockito.when(generoController.buscarGeneroNome("Ação")).thenReturn(generoA);
        Mockito.when(generoController.buscarGeneroNome("Drama")).thenReturn(generoB);
        Mockito.when(generoController.buscarGeneroNome("Fantasia")).thenReturn(generoC);


        Mockito.when(generoController.getAllGeneros()).thenReturn(generos);*/
    }


    public static SalaController getSalaControllerMock(){
        return salaController;
    }

    public static CinemaController getCinemaControllerMock(){
        return cinemaController;
    }

    public static FilmeController getFilmeControllerMock(){
        return filmeControllerMock;
    }

    public static GeneroController getGeneroControllerMock(){
        return generoController;
    }

    public static SalaController getSalaController(){
        return salaController;
    }
}
