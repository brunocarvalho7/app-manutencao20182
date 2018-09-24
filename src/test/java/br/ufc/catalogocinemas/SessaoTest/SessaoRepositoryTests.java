package br.ufc.catalogocinemas.SessaoTest;

import br.ufc.catalogocinemas.mocks.Mocks;
import br.ufc.catalogocinemas.model.Sala;
import br.ufc.catalogocinemas.model.Sessao;
import br.ufc.catalogocinemas.repository.SessaoRepository;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
Consideramos esse teste ao mesmo tempo como unitario e de integração, já que ele testa a integração entre as
diversas camadas de sessão
* */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoRepositoryTests {
  /*  @Autowired
    SessaoRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void AdicionarNovaSessaoComSucessoTest() {
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));
        Sessao sessaoRetornada = repository.save(sessaoEsperada);

        Assert.assertEquals(sessaoEsperada, sessaoRetornada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);

    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaInvalidoTest() {
        thrown.expect(Exception.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoEsperada);
    }

    @Test
    public void BuscarSessaoPorIdCorretamenteTest() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 05));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 05));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(16, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 06));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        int idSessaoDesejada = sessaoB.getId();

        Sessao sessaoRecebida = repository.findOne(idSessaoDesejada);

        Assert.assertEquals(sessaoRecebida.getId(), idSessaoDesejada);
    }

    @Test
    public void ErroAoBuscarSessaoPorIdTest() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        repository.save(sessaoA);

        int idSessaoDesejada = databaseUtils.getMaxIdSessao() + 1;
        Sessao sessaoRecebida = repository.findOne(idSessaoDesejada);

        Assert.assertNull(sessaoRecebida);
    }

    @Test
    public void RemoverSessaoCorretamenteTest() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        repository.save(sessaoA);

        int idSessaoASerRemovida = databaseUtils.getMaxIdSessao();

        repository.delete(idSessaoASerRemovida);

        Sessao sessaoRemovida = repository.findOne(idSessaoASerRemovida);

        Assert.assertNull(sessaoRemovida);
    }

    @Test
    public void BuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = repository.findAll();
        int qtdAntes = sessoes.size();

        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 03),
                LocalDate.of(2018, 05, 10));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);

        sessoes = repository.findAll();

        Assert.assertEquals(sessoes.size(), qtdAntes + 2);
    }

    @Test
    public void ErroAoBuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = repository.findAll();
        int qtdAntes = sessoes.size();

        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 03),
                LocalDate.of(2018, 05, 10));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);

        sessoes = repository.findAll();

        Assert.assertNotEquals(sessoes.size() + 1, qtdAntes + 2);
    }

    @Test
    public void AtualizarSessaoComSucessoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoRetornada = repository.save(sessaoAtualizar);

        Assert.assertEquals(sessaoAtualizar.getId(), sessaoRetornada.getId());
        Assert.assertEquals(sessaoAtualizar.getFilme().getId(), sessaoRetornada.getFilme().getId());
        Assert.assertEquals(sessaoAtualizar.getHorario(), sessaoRetornada.getHorario());
        Assert.assertEquals(sessaoAtualizar.getDataInicio(), sessaoRetornada.getDataInicio());
        Assert.assertEquals(sessaoAtualizar.getDataFim(), sessaoRetornada.getDataFim());
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoAtualizar);

    }

    @Test
    public void ErroAoAtualizarSessaoComSalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        repository.save(sessaoAtualizar);

    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));


        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = repository.save(sessaoOriginal);

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 15, 15));

        repository.save(sessaoAtualizar);
    }

    @Test
    public void buscarTodasAsSessoesPorCidadeCorretamenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaController().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sala> salas = Mocks.getSalaController().buscarSalasPorCidade("Quixada");

        List<Sessao> sessoesQuixada = new ArrayList<>();

        for (Sala s : salas) {
            sessoesQuixada.addAll(repository.findBySala_id(s.getId()));
        }

        Assert.assertEquals(sessoesQuixada.size(), 2);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesPorCidadeErroneamenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaController().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sala> salas = Mocks.getSalaController().buscarSalasPorCidade("Quixada");

        List<Sessao> sessoesQuixada = new ArrayList<>();

        for (Sala s : salas) {
            sessoesQuixada.addAll(repository.findBySala_id(s.getId()));
        }

        Assert.assertNotEquals(sessoesQuixada.size(), 3);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesDeUmaCidadeInexistenteTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaController().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sala> salas = Mocks.getSalaController().buscarSalasPorCidade("Quixeramobim");

        List<Sessao> sessoesQuixada = new ArrayList<>();

        for (Sala s : salas) {
            sessoesQuixada.addAll(repository.findBySala_id(s.getId()));
        }

        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesDeUmaCidadeComNomeNuloTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaController().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sala> salas = Mocks.getSalaController().buscarSalasPorCidade(null);

        List<Sessao> sessoesQuixada = new ArrayList<>();

        for (Sala s : salas) {
            sessoesQuixada.addAll(repository.findBySala_id(s.getId()));
        }

        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    public void buscarTodasAsSessoesDeUmaCidadeComNomeVazioTest() {
        //Remover todas as sessões do banco de dados
        databaseUtils.deleteAllSessao();

        //****************** Quixadá[Salas 1, 2] ************************
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaController().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(2),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(2),
                Mocks.getSalaController().buscarSalaId(3),
                LocalTime.of(20, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sala> salas = Mocks.getSalaController().buscarSalasPorCidade("");

        List<Sessao> sessoesQuixada = new ArrayList<>();

        for (Sala s : salas) {
            sessoesQuixada.addAll(repository.findBySala_id(s.getId()));
        }

        Assert.assertEquals(sessoesQuixada.size(), 0);
        //===================================================
    }

    @Test
    public void listarTodasAsSessoesPorDataCorretamente() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        List<Sessao> sessoes = repository.findByDataInicioGreaterThanEqualAndDataFimLessThanEqual(LocalDate.parse("2018-05-01"), LocalDate.parse("2018-05-15"));

        Assert.assertEquals(sessoes.size(), 2);
    }

    @Test
    public void erroAoBuscarSessaoComDataInicioInvalido() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        thrown.expect(DateTimeException.class);
        List<Sessao> sessoes = repository.findByDataInicioGreaterThanEqualAndDataFimLessThanEqual(LocalDate.parse("aaaa"), LocalDate.parse("2018-05-15"));
    }

    @Test
    public void erroAoBuscarSessaoComDataFimInvalido() {
        databaseUtils.deleteAllSessao();

        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoB = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 14));

        Sessao sessaoC = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 02),
                LocalDate.of(2018, 05, 16));

        repository.save(sessaoA);
        repository.save(sessaoB);
        repository.save(sessaoC);

        thrown.expect(DateTimeException.class);
        List<Sessao> sessoes = repository.findByDataInicioGreaterThanEqualAndDataFimLessThanEqual(LocalDate.parse("2018-05-01"), LocalDate.parse("bbb"));
    }*/
}
