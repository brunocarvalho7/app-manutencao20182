package br.ufc.catalogocinemas;

import br.ufc.catalogocinemas.utils.DatabaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertsNoBancoDeDados {

    @Autowired
    DatabaseUtils databaseUtils;

    @Test
    public void inserirNoBancoDeDados(){
        databaseUtils.inserts();
    }
}
