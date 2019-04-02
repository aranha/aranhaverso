package br.com.gastronomia.tests;

import br.com.gastronomia.bo.GrupoReceitasBO;
import br.com.gastronomia.dao.GrupoReceitasDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.GrupoReceitas;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestGrupoReceitasBO {

    GrupoReceitasBO grupoReceitasBO;

    @Mock
    GrupoReceitasDAO grupoReceitasDAO;

    @Before
    public void init(){
        grupoReceitasBO = new GrupoReceitasBO();
        grupoReceitasBO.setGrupoReceitasDAO(grupoReceitasDAO);
    }

    @Test
    public void testCreateGrupoReceita() throws NoSuchAlgorithmException,  ValidationException {
        GrupoReceitas grupoReceitas = new GrupoReceitas();
        Mockito.when(grupoReceitasDAO.save(grupoReceitas)).thenReturn((long) 0);
        boolean retorno = grupoReceitasBO.createGroup(grupoReceitas);
        assertTrue(retorno);
    }


    @Test
    public void testUpdateGroup() throws ValidationException{
        GrupoReceitas grupoReceitas = new GrupoReceitas();
        Mockito.when(grupoReceitasDAO.updateGroup(grupoReceitas)).thenReturn((long)1);
        long retorno = grupoReceitasBO.updateGroup(grupoReceitas);
        assertEquals(1,retorno);
    }

    @Test
    public void testActiveGroup() throws ValidationException {
        Mockito.when(grupoReceitasDAO.alterStatus(0, true)).thenReturn((long)0);
        long result = grupoReceitasBO.activateGroup(0);
        assertEquals(result, 0);
    }

//    @Test
//    public void testGroupDeactiveGroup() throws ValidationException {
//        Mockito.when(grupoReceitasDAO.alterStatus(0, true)).thenReturn((long)0);
//        long result = grupoReceitasBO.deactivateGroup(0);
//        assertEquals(result, 0);
//    }

    @Test
    public void testGetGroupByCod() throws ValidationException {
        GrupoReceitas grupoReceitas = new GrupoReceitas();
        Mockito.when(grupoReceitasDAO.findGroupByID(1)).thenReturn(grupoReceitas);
        GrupoReceitas grupoReceitas2 = grupoReceitasBO.getGroupByCod(1);
        assertEquals(grupoReceitas, grupoReceitas2);
    }

}
