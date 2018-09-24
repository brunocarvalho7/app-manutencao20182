package br.ufc.catalogocinemas.SessaoTest;

import br.ufc.catalogocinemas.controller.SessaoController;
import br.ufc.catalogocinemas.mocks.Mocks;
import br.ufc.catalogocinemas.model.Sessao;
import br.ufc.catalogocinemas.utils.Constantes;
import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;


import javax.validation.ConstraintViolationException;
import javax.validation.constraints.AssertTrue;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoControllerTests {
/*
    @Autowired
    private SessaoController controller;

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
        Sessao sessaoRetornada = (Sessao) controller.addSessao(sessaoEsperada).getModel().get("sessao");

        Assert.assertEquals(sessaoEsperada, sessaoRetornada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeInvalidoTest() {
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        String msgRetorno = (String) controller.addSessao(sessaoEsperada).getModel().get("msg");

        Assert.assertEquals(msgRetorno, Constantes.MSG_ERRO_INSERIR_SESSAO);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaInvalidoTest() {
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        String msgRetorno = (String) controller.addSessao(sessaoEsperada).getModel().get("msg");

        Assert.assertEquals(msgRetorno, Constantes.MSG_ERRO_INSERIR_SESSAO);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaInvalidoTest() {
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        String msgRetorno = (String) controller.addSessao(sessaoEsperada).getModel().get("msg");

        Assert.assertEquals(msgRetorno, Constantes.MSG_ERRO_INSERIR_SESSAO);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioInvalidoTest() {
        thrown.expect(DateTimeException.class);

        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 06, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 06, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataInicioEDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        controller.addSessao(sessaoEsperada);
    }

    @Test
    public void ErroAoAdicionarNovaSessaoComDataFimInvalidoTest() {
        thrown.expect(DateTimeException.class);
        Sessao sessaoEsperada = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 16, 15));

        controller.addSessao(sessaoEsperada);
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        int idSessaoDesejada = sessaoB.getId();

        Sessao sessaoRecebida = (Sessao) controller.getSessaoPorId(idSessaoDesejada).getModel().get("sessao");

        Assert.assertEquals(sessaoRecebida.getId(), idSessaoDesejada);
    }

    @Test
    public void ErroAoBuscarSessaoPorIdTest() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        controller.addSessao(sessaoA);

        int idSessaoDesejada = databaseUtils.getMaxIdSessao() + 1;
        Sessao sessaoRecebida = (Sessao) controller.getSessaoPorId(idSessaoDesejada).getModel().get("sessao");

        Assert.assertNull(sessaoRecebida);
    }

    @Test
    public void RemoverSessaoCorretamenteTest() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        controller.addSessao(sessaoA);

        int idSessaoASerRemovida = databaseUtils.getMaxIdSessao();

        Sessao sessaoRemovida = (Sessao) controller.removerSessao(idSessaoASerRemovida).getModel().get("sessao");

        Assert.assertEquals(sessaoRemovida.getId(), idSessaoASerRemovida);
    }

    @Test
    public void ErroAoTentarRemoverSessaoInexistente() {
        Sessao sessaoA = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(21, 00),
                LocalDate.of(2018, 05, 05),
                LocalDate.of(2018, 05, 10));

        controller.addSessao(sessaoA);

        int idSessaoASerRemovida = databaseUtils.getMaxIdSessao() + 1;

        Sessao sessaoRemovida = (Sessao) controller.removerSessao(idSessaoASerRemovida).getModel().get("sessao");

        Assert.assertNull(sessaoRemovida);
    }

    @Test
    public void BuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = (List<Sessao>) controller.todas().getModel().get("sessoes");
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);

        sessoes = (List<Sessao>) controller.todas().getModel().get("sessoes");

        Assert.assertEquals(sessoes.size(), qtdAntes + 2);
    }

    @Test
    public void ErroAoBuscarTodasAsSessoesCorretamenteTest() {
        List<Sessao> sessoes = (List<Sessao>) controller.todas().getModel().get("sessoes");
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);

        sessoes = (List<Sessao>) controller.todas().getModel().get("sessoes");

        Assert.assertNotEquals(sessoes.size() + 1, qtdAntes + 2);
    }

    @Test
    public void AtualizarSessaoComSucessoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        Sessao sessaoRetornada = (Sessao) controller.atualizarSessao(sessaoAtualizar).getModel().get("sessao");

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

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));

        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComFilmeESalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(999),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(30, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));

        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(18, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));

        thrown.expect(TransactionSystemException.class);
        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComSalaEHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(999),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 05, 01),
                LocalDate.of(2018, 05, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComHorarioEDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(28, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 05, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataInicioEDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 15, 01),
                LocalDate.of(2018, 16, 15));


        controller.atualizarSessao(sessaoAtualizar);
    }

    @Test
    public void ErroAoAtualizarSessaoComDataFimInvalidoTest() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        sessaoOriginal = (Sessao) controller.addSessao(sessaoOriginal).getModel().get("sessao");

        thrown.expect(DateTimeException.class);
        Sessao sessaoAtualizar = new Sessao(sessaoOriginal.getId(),
                Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(19, 30),
                LocalDate.of(2018, 01, 01),
                LocalDate.of(2018, 15, 15));


        controller.atualizarSessao(sessaoAtualizar);
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoesQuixada = (List<Sessao>) controller.todosPorCidade("Quixada").getModel().get("sessoes");
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoesQuixada = (List<Sessao>) controller.todosPorCidade("Quixada").getModel().get("sessoes");
        Assert.assertNotEquals(sessoesQuixada.size(), 3);
        //===================================================
    }

    @Test
    public void buscaarTodasAsSessoesPorCidadeErroneamenteTest() {
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoesQuixada = (List<Sessao>) controller.todosPorCidade("Quixeramobim").getModel().get("sessoes");
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoes = (List<Sessao>) controller.todasPorData("2018-05-01", "2018-05-15").getModel().get("sessoes");

        Assert.assertEquals(sessoes.size(), 2);
    }

    @Test
    public void erroAoBuscarSessaoComDataInicioInvalido() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        thrown.expect(DateTimeException.class);
        sessaoOriginal = (Sessao) controller.todasPorData("aa", "2018-01-01").getModel().get("sessao");
    }

    @Test
    public void erroAoBuscarSessaoComDataFimInvalido() {
        Sessao sessaoOriginal = new Sessao(Mocks.getFilmeControllerMock().buscarFilmeId(1),
                Mocks.getSalaControllerMock().buscarSalaId(1),
                LocalTime.of(23, 30),
                LocalDate.of(1996, 9, 2),
                LocalDate.of(1996, 9, 2));

        thrown.expect(DateTimeException.class);
        sessaoOriginal = (Sessao) controller.todasPorData("2018-05-01", "bbbbb").getModel().get("sessao");
    }

    @Test
    public void buscarTodasAsSessoesPorFilmeCorretamenteTest() {
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoesAcampamentoDoPapai = (List<Sessao>) controller.todosPorFilme("Acampamento do Papai").getModel().get("sessoes");
        Assert.assertEquals(sessoesAcampamentoDoPapai.size(), 2);
        //===================================================
    }

    @Test
    public void buscarTodasAsSessoesPorFilmeInexistenteTest() {
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

        controller.addSessao(sessaoA);
        controller.addSessao(sessaoB);
        controller.addSessao(sessaoC);

        List<Sessao> sessoesAcampamentoDoPapai = (List<Sessao>) controller.todosPorFilme("Matrix").getModel().get("sessoes");
        Assert.assertEquals(sessoesAcampamentoDoPapai.size(), 0);
        //===================================================

    }

*/
}