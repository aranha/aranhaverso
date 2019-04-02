package br.com.gastronomia.tests;


import br.com.gastronomia.dao.ReceitaDAO;
import org.junit.Before;
import org.junit.Test;
import br.com.gastronomia.bo.ReceitaBO;
import br.com.gastronomia.model.Receita;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static  org.junit.Assert.assertEquals;

public class TestReceitaBO {

    @Mock
    private Receita receita;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        ReceitaBO receitaBO = new ReceitaBO();

    }

    @Test
    public void testActivateReceita() {

    }
//
//    @Test
//    public void testListReceita(){
//        ReceitaDAO receitaDAO = new ReceitaDAO();
//        assertEquals(, receitaDAO.findReceitaByIdUsuario(1));
//    }
}
