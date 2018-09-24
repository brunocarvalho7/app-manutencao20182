package br.ufc.catalogocinemas.utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DatabaseUtils {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    public void deleteAllSessao(){
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //Remover todas as sessões
            statement.execute("DELETE FROM SESSAO");

            //Setar a sequencia de ID para 0
            statement.execute("ALTER SEQUENCE sessao_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteAllAdmin(){
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //Remover todas as sessões
            statement.execute("DELETE FROM ADMIN");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteAllGeneros(){
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM FILME_GENEROS");
            statement.execute("DELETE FROM GENERO");
            statement.execute("ALTER SEQUENCE genero_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteAllSalasECinemasEFilmesESessao(){
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM SESSAO");
            statement.execute("DELETE FROM CINEMA_SALAS");
            statement.execute("DELETE FROM SALA");
            statement.execute("DELETE FROM CINEMA");
            statement.execute("DELETE FROM FILME_ATORES");
            statement.execute("DELETE FROM FILME_DIRETORES");
            statement.execute("DELETE FROM FILME_GENEROS");
            statement.execute("DELETE FROM FILME");
            statement.execute("ALTER SEQUENCE sala_id_seq RESTART");
            statement.execute("ALTER SEQUENCE sessao_id_seq RESTART");
            statement.execute("ALTER SEQUENCE cinema_id_seq RESTART");
            statement.execute("ALTER SEQUENCE filme_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public int getMaxIdSessao(){
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select max(id) from sessao");

            int id = 0;
            while(rs.next()){
                id = rs.getInt(1);
            }

            return id;

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return -1;
    }

    public void inserts() {
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //Remover todas as sessões
            statement.execute("INSERT INTO SALA VALUES (1), (2), (3)");
            statement.execute("INSERT INTO FILME VALUES (1), (2), (3)");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

	public void deleteAllCinemas() {
		try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM cinema_salas");
            statement.execute("DELETE FROM cinema");
            statement.execute("ALTER SEQUENCE cinema_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
	}

    public void deleteAllAtores() {
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM FILME_ATORES");
            statement.execute("DELETE FROM ATOR");
            statement.execute("ALTER SEQUENCE ator_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public void deleteAllDiretores() {
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM FILME_DIRETORES");
            statement.execute("DELETE FROM DIRETOR");
            statement.execute("ALTER SEQUENCE diretor_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteAllSalas() {
        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM SALA");
            statement.execute("ALTER SEQUENCE sala_id_seq RESTART");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
