package br.com.gastronomia.tests;

import br.com.gastronomia.bo.IngredienteBO;
import br.com.gastronomia.dao.IngredienteDAO;
import br.com.gastronomia.dto.IngredienteCadastroDTO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static br.com.gastronomia.util.TipoDeIngrediente.PRIVADO;

import javax.inject.Inject;
import javax.validation.constraints.AssertTrue;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.gastronomia.util.TipoDeUsuario.ADMIN;
import static br.com.gastronomia.util.TipoDeUsuario.PROFESSOR;
import static br.com.gastronomia.util.TipoDeUsuario.USUARIO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestIngredienteBO {

    @Mock
    IngredienteBO ingredienteBO;

    @Mock
    IngredienteDAO ingredienteDAO;

    @Mock
    Ingrediente ingrediente;

    @Before
    public void init() throws ValidationException, NoSuchAlgorithmException {
        ingredienteBO = new IngredienteBO();

        ingrediente = new Ingrediente();
        ingrediente.setNome("Farinha");
        ingrediente.setCriador(new Usuario(1));
        ingrediente.setOrigem("TACO");
        ingrediente.setStatus(true);
        ingrediente.setTipo(PRIVADO);

        ingredienteBO.createIngrediente(ingrediente);
    }


//    @Test
//    public void testListaIngredientes() {
//        ArrayList<Ingrediente> listaDeIngredientes = new ArrayList<>();
//        Ingrediente ingrediente = new Ingrediente();
//        listaDeIngredientes.add(ingrediente);
//
//        assertEquals(listaDeIngredientes.size(), 1);
//    }
//
//    @Test
//    public void testInactiveIngredient() throws ValidationException {
//
//        Mockito.when(ingredienteDAO.alterStatus(1, false)).thenReturn((long)1);
//
//		assertEquals(false, ingredienteDAO);
//
//    }
}