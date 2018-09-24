package br.ufc.catalogocinemas.SessaoTest;


import br.ufc.catalogocinemas.enums.TipoSala;
import br.ufc.catalogocinemas.mocks.Mocks;
import br.ufc.catalogocinemas.model.Cinema;
import br.ufc.catalogocinemas.model.Filme;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.model.Sessao;
import br.ufc.catalogocinemas.repository.SalaRepository;
import br.ufc.catalogocinemas.service.CinemaService;
import br.ufc.catalogocinemas.service.FilmeService;
import br.ufc.catalogocinemas.service.SalaService;
import br.ufc.catalogocinemas.service.SessaoService;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class SessaoServiceTests {
    @Autowired
    SessaoService service;

    @Autowired
    FilmeService filmeService;

    @Autowired
    SalaService salaService;

    @Autowired
    CinemaService cinemaService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    DatabaseUtils databaseUtils;

    @Before
    public void setUp(){
        databaseUtils.deleteAllSalasECinemasEFilmesESessao();

        Cinema cinemaQuixada = new Cinema("Cinema Quixadá", "Endereço de Quixadá", "Quixada");
        Cinema cinemaFortaleza = new Cinema("Cinema Fortaleza", "Endereço de Fortaleza", "Fortaleza");

        cinemaService.addCinema(cinemaQuixada);
        cinemaService.addCinema(cinemaFortaleza);

        Sala sala1 = new Sala("Sala 01", TipoSala.SALA_2D, 100);
        Sala sala2 = new Sala("Sala 02", TipoSala.SALA_2D, 200);
        Sala sala3 = new Sala("Sala 03", TipoSala.SALA_3D, 300);

        salaService.addSala(sala1);
        salaService.addSala(sala2);
        salaService.addSala(sala3);

        cinemaService.vincularSala(sala1.getId(), cinemaQuixada.getId());
        cinemaService.vincularSala(sala2.getId(), cinemaQuixada.getId());
        cinemaService.vincularSala(sala3.getId(), cinemaFortaleza.getId());

        Filme filmeA = new Filme("Acampamento do Papai", "Sinopse A", 180);
        Filme filmeB = new Filme("O Espetacular Homem-Aranha", "Sinopse B", 190);
        Filme filmeC = new Filme("Karate Kid", "Sinopse C", 200);

        filmeService.addFilme(filmeA);
        filmeService.addFilme(filmeB);
        filmeService.addFilme(filmeC);

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

    @Test
    public void AdicionarNovaSessaoComSucessoTest() {
        System.out.println(filmeService.buscarFilmeId(1));
        System.out.println(salaService.buscarSala(1));
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));
        Sessao sessaoRetornada = service.addSessao(sessaoEsperada);

        Assert.assertEquals(sessaoEsperada, sessaoRetornada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(999),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);

    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoEsperada);
    }

    @Test
    public void BuscarSessaoPorIdCorretamenteTest() {
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 05));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 05));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(16, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 06));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);

        Integer idSessaoDesejada = sessaoB.getId();

        Sessao sessaoRecebida = service.getSessaoPorId(idSessaoDesejada);

        Assert.assertEquals(sessaoRecebida.getId(), idSessaoDesejada);
    }

    @Test
    public void ErroAoBuscarSessaoPorIdTest() {
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        service.addSessao(sessaoA);

        int idSessaoDesejada = 99999;
        Sessao sessaoRecebida = service.getSessaoPorId(idSessaoDesejada);

        Assert.assertNull(sessaoRecebida);
    }

    @Test
    public void RemoverSessaoCorretamenteTest() {
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        service.addSessao(sessaoA);

        int idSessaoASerRemovida = databaseUtils.getMaxIdSessao();

        service.removerSessao(idSessaoASerRemovida);

        Sessao sessaoRemovida = service.getSessaoPorId(idSessaoASerRemovida);

        Assert.assertNull(sessaoRemovida);
    }

    @Test
    public void BuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = service.todas();
        int qtdAntes = sessoes.size();

        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 03),
                LocalDate.of(2018, 05, 10));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);

        sessoes = service.todas();

        Assert.assertEquals(sessoes.size(), qtdAntes + 2);
    }

    @Test
    public void ErroAoBuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = service.todas();
        int qtdAntes = sessoes.size();

        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 03),
                LocalDate.of(2018, 05, 10));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);

        sessoes = service.todas();

        Assert.assertNotEquals(sessoes.size() + 1, qtdAntes + 2);
    }

    @Test
    public void AtualizarSessaoComSucessoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoRetornada = service.addSessao(sessaoAtualizar);

        Assert.assertEquals(sessaoAtualizar.getId(), sessaoRetornada.getId());
        Assert.assertEquals(sessaoAtualizar.getFilme().getId(), sessaoRetornada.getFilme().getId());
        Assert.assertEquals(sessaoAtualizar.getHorario(), sessaoRetornada.getHorario());
        Assert.assertEquals(sessaoAtualizar.getDataInicio(), sessaoRetornada.getDataInicio());
        Assert.assertEquals(sessaoAtualizar.getDataFim(), sessaoRetornada.getDataFim());
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(999),
                salaService.buscarSala(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(999),
                salaService.buscarSala(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoAtualizar);

    }

    @Test
    public void ErroAoAtualizarSessaoComSalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        service.addSessao(sessaoAtualizar);

    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));


        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = service.addSessao(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 15, 15));

        service.addSessao(sessaoAtualizar);
    }

    @Test
    public void buscarTodasAsSessoesPorCidadeCorretamenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);


        List<Sessao> sessoesQuixada = service.todasPorCidade("Quixada");


        Assert.assertEquals(sessoesQuixada.size(), 2);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesPorCidadeErroneamenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);

        List<Sessao> sessoesQuixada = service.todasPorCidade("Quixada");

        Assert.assertNotEquals(sessoesQuixada.size(), 3);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesDeUmaCidadeInexistenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);


        List<Sessao> sessoesQuixada = service.todasPorCidade("Quixeramobim");

        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesDeUmaCidadeComNomeNuloTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);


        List<Sessao> sessoesQuixada = service.todasPorCidade(null);

        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    public void buscarTodasAsSessoesDeUmaCidadeComNomeVazioTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(2),
                salaService.buscarSala(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);


        List<Sessao> sessoesQuixada = service.todasPorCidade("");


        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    @Test
    public void listarTodasAsSessoesPorDataCorretamente() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);


        List<Sessao> sessoes = service.todasPorData(LocalDate.parse("2018-05-01"), LocalDate.parse("2018-05-15"));

        Assert.assertEquals(sessoes.size(), 2);
    }

    @Test
    public void erroAoBuscarSessaoComDataInicioInvalido() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);

        thrown.expect(DateTimeException.class);
        List<Sessao> sessoes = service.todasPorData(LocalDate.parse("aaaa"), LocalDate.parse("2018-05-15"));
    }

    @Test
    public void erroAoBuscarSessaoComDataFimInvalido() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(filmeService.buscarFilmeId(1),
                salaService.buscarSala(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        service.addSessao(sessaoA);
        service.addSessao(sessaoB);
        service.addSessao(sessaoC);

        thrown.expect(DateTimeException.class);
        List<Sessao> sessoes = service.todasPorData(LocalDate.parse("2018-05-01"), LocalDate.parse("bbb"));
    }
}
