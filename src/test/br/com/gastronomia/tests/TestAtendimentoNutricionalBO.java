package br.com.dietoterapia.tests;

import br.com.gastronomia.bo.GrupoReceitasBO;
import br.com.gastronomia.bo.dietoterapia.AtendimentoNutricionalBO;
import br.com.gastronomia.dao.GrupoReceitasDAO;
import br.com.gastronomia.dao.dietoterapia.AtendimentoNutricionalDAO;
import br.com.gastronomia.dto.AtendimentoNutricionalDTO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.GrupoReceitas;
import br.com.gastronomia.model.dietoterapia.AtendimentoNutricional;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestAtendimentoNutricionalBO {

    AtendimentoNutricionalBO atendimentoNutricionalBO;

    @Mock
    AtendimentoNutricionalDAO atendimentoNutricionalDAO;

    @Before
    public void init(){
        atendimentoNutricionalBO = new AtendimentoNutricionalBO();
    }

    @Test
    public void testCreateAtendimentoNutricional() throws NoSuchAlgorithmException,  ValidationException {
        AtendimentoNutricional atendimentoNutricional = new AtendimentoNutricional();
        Mockito.when(atendimentoNutricionalDAO.save(atendimentoNutricional)).thenReturn((long) 0);
        long retorno = atendimentoNutricionalDAO.criarAtendimentoNutricional(atendimentoNutricional);

        assertEquals(0, retorno);
    }


    @Test
    public void testUpdateAtendimento() throws ValidationException, NoSuchAlgorithmException{
        AtendimentoNutricional atendimentoNutricional = new AtendimentoNutricional();
       AtendimentoNutricionalDTO atendimentoNutricionalDTO = new AtendimentoNutricionalDTO();
        Mockito.when(atendimentoNutricionalDAO.updateAtendimento(atendimentoNutricional)).thenReturn((long)1);
        long retorno = atendimentoNutricionalBO.atualizarAtendimento(atendimentoNutricionalDTO);
        assertEquals(0,retorno);
    }


    @Test
    public void testFindAtendimentoById() throws ValidationException {
        AtendimentoNutricional atendimentoNutricional = new AtendimentoNutricional();
        Mockito.when(atendimentoNutricionalDAO.findAtendimentoByID(1)).thenReturn(atendimentoNutricional);
        AtendimentoNutricional atendimentoRetorno = atendimentoNutricionalBO.getById(1);
        assertEquals(atendimentoNutricional, atendimentoRetorno);
    }

//   

}
