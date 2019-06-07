package br.com.gastronomia.tests;

import static br.com.gastronomia.util.TipoDeUsuario.ADMIN;
import static br.com.gastronomia.util.TipoDeUsuario.PROFESSOR;
import static br.com.gastronomia.util.TipoDeUsuario.USUARIO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.SendMail;
import br.com.gastronomia.util.TipoDeUsuario;
import org.junit.Test;

import br.com.gastronomia.util.Validator;
import br.com.gastronomia.util.Util;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

public class TestUsuarioBO {


    UsuarioBO usuarioBO;

    @Mock
    UsuarioDAO usuarioDAO;


    @Test
    public void testCPFTrue() {
        assertEquals(true, Validator.validaCpf.fazConta(("61642096091")));
    }

    @Test
    public void testCPFFalse() {
        assertEquals(false, Validator.validaCpf.fazConta(("85172596021")));
    }

    @Test
    public void testCpfNaoAceitaLetras() {
        assertEquals(false, Validator.validaCpf.fazConta("abcdefghijk"));
    }

    @Test
    public void testCpfNaoAceitaMenosDeOnzeNumeros() {
        assertEquals(false, Validator.validaCpf.fazConta("12345678910"));
    }

    @Test
    public void testCpfNaoAceitaMaisDeOnzeNumeros() {
        assertEquals(false, Validator.validaCpf.fazConta("123456789011"));
    }

    @Test
    public void testNaoAceitaSequenciaDeNumerosIguais() {

        assertEquals(false, Util.isCPF("1111111111"));
    }

    @Test
    public void testUsuarioNome() {
        Usuario aluno = new Usuario();
        aluno.setNome("Testando Usuario");
        {
            assertEquals("Testando Usuario", aluno.getNome());
        }
    }

    @Test
    public void testUsuarioCpf() {
        Usuario aluno = new Usuario();
        aluno.setCpf("0987654321");
        {
            assertEquals("0987654321", aluno.getCpf());
        }
    }

    @Test
    public void testUsuarioSenha() {
        Usuario aluno = new Usuario();
        aluno.setSenha("teste123");
        {
            assertEquals("teste123", aluno.getSenha());
        }
    }

    @Test
    public void testUsuarioStatus() {
        Usuario aluno = new Usuario();
        aluno.setStatus(true);
        {
            assertEquals(true, aluno.isStatus());
        }
    }

    @Test
    public void testUsuarioEmail() {
        Usuario aluno = new Usuario();
        aluno.setEmail("aluno@ufcspa.com.br");
        {
            assertEquals("aluno@ufcspa.com.br", aluno.getEmail());
        }
    }

    @Test
    public void testUsuarioTipoADMIN() {
        Usuario aluno = new Usuario();
        aluno.setTipo(ADMIN);
        {
            assertEquals(ADMIN, aluno.getTipo());
        }
    }

    @Test
    public void testUsuarioTipoPROFESSOR() {
        Usuario aluno = new Usuario();
        aluno.setTipo(PROFESSOR);
        {
            assertEquals(PROFESSOR, aluno.getTipo());
        }
    }

    @Test
    public void testUsuarioTipoUSUARIO() {
        Usuario aluno = new Usuario();
        aluno.setTipo(USUARIO);
        {
            assertEquals(USUARIO, aluno.getTipo());
        }
    }


//    @Test //1 - OK
//    public void testCreateUsuario() throws NoSuchAlgorithmException, ValidationException {
//        Usuario usuario = new Usuario();
//        Mockito.when(usuarioDAO.save(usuario)).thenReturn((long) 0);
//        boolean retorno = usuarioBO.createUser(usuario);
//        assertTrue(retorno);
//    }

//    @Test //2 - OK
//    public void testUpdateUser() throws ValidationException {
//        Usuario usuario = new Usuario();
//        Mockito.when(usuarioDAO.updateUser(usuario)).thenReturn((long) 1);
//        long retorno = 1;
//        try {
//            retorno = usuarioBO.updateUser(usuario);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        assertEquals(1, retorno);
//    }

//    @Test // 3 - OK
//    public void testActiveUser() throws ValidationException {
//        Mockito.when(usuarioDAO.alterStatus(0, true)).thenReturn((long) 0);
//        long result = usuarioBO.activateUser(0);
//        assertEquals(result, 0);
//    }

//    @Test // 4 - OK
//    public void testGroupDeactiveUser() throws ValidationException {
//        Mockito.when(usuarioDAO.alterStatus(0, true)).thenReturn((long) 0);
//        long result = usuarioBO.deactivateUser(0);
//        assertEquals(result, 0);
//    }

//    @Test // 5 - OK
//    public void testGetUserByCod() throws ValidationException {
//        Usuario usuario = new Usuario();
//        Mockito.when(usuarioDAO.findUserByID(1)).thenReturn(usuario);
//        Usuario usuario2 = usuarioBO.getUserById(1);
//        assertEquals(usuario, usuario2);
//    }


}

