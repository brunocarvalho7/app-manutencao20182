package br.ufc.catalogocinemas.repository;

import br.ufc.catalogocinemas.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer>{

    /*    Listar todas as sessões cuja o seu inicio seja maior ou igual a dataInicio(parâmetro da função)
      e que o fim seja menor igual a dataFim(parâmetro da função)
*/
    List<Sessao> findByDataInicioGreaterThanEqualAndDataFimLessThanEqual(LocalDate dataInicio, LocalDate dataFim);

    List<Sessao> findByFilme_id(int idFilme);

    List<Sessao> findBySala_id(int idSala);

    @Query(value = "select se.* from sessao as se left join sala as sa on(se.sala_id = sa.id) left join cinema as c on(sa.cinema_id = c.id) where c.cidade = ?1",
           nativeQuery = true)
    List<Sessao> buscarPorCidade(String cidade);

    @Query(value = "select s.* from sessao as s left join filme as f on(s.filme_id = f.id) and f.nome = ?1",
            nativeQuery = true)
    List<Sessao> buscarPorFilme(String nome);




}
