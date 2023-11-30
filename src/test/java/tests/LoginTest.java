package tests;

import commons.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CadastroPage;
import pages.LoginPage;
import utils.SeletoresAssert;

public class LoginTest extends BaseTest {
    CadastroPage cadastro = new CadastroPage();
    LoginPage login = new LoginPage();
    SeletoresAssert seletor = new SeletoresAssert();
    @Test
    @DisplayName("Teste de login com sucesso, onde primeiro é cadasrtrado um usuário, após o cadastro é feito o logim.")
    public void testloginComSucesso(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste@teste.com", "teste1234");
        Assertions.assertEquals("bem vindo ao BugBank :)", login.getTextByCss(seletor.BEM_VINDO_AO_BUGBANK));
    }
    @Test
    @DisplayName("Teste de login com senha incorreta, onde é cadasrtrado um usuário, após o cadastro é feito o logim informando a senha incorreta.")
    public void testloginSenhaIncorreta(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste@teste.com", "teste09876");
        Assertions.assertEquals("Usuário ou senha inválido.\nTente novamente ou verifique suas informações!", login.modalLogin().toString());
    }
    @Test
    @DisplayName("Teste de login com dados de um usuário inexistente.")
    public void testLoginUsuarioInexistente(){
        login.login("user@teste.com", "123456");
        Assertions.assertEquals("Usuário ou senha inválido.\nTente novamente ou verifique suas informações!", login.modalLogin().toString());
    }
    @Test
    @DisplayName("Teste de login sem informar email")
    public void testSemEmail(){
        login.login("", "teste1234");
        Assertions.assertEquals("É campo obrigatório", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
    @Test
    @DisplayName("Teste de login sem informar senha")
    public void testSemSenha(){
        login.login("teste@teste.com", "");
        Assertions.assertEquals("É campo obrigatório", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_SENHA));
    }
    @Test
    @DisplayName("Teste de login sem informar email nem senha")
    public void testLoginComCamposEmBranco(){
        login.login("", "");
        Assertions.assertEquals("É campo obrigatório", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
        Assertions.assertEquals("É campo obrigatório", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_SENHA));
    }
    @Test
    @DisplayName("Teste de login sem inserir @ no email.")
    public void testEmailFormatoInvalido(){
        login.login("teste.teste.com", "teste1234");
        Assertions.assertEquals("Formato inválido", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
    @Test
    @DisplayName("Teste de login inserindo duas vezes o @ no email.")
    public void testEmailcomArrobaDuplicado(){
        login.login("teste@@teste.com", "teste1234");
        Assertions.assertEquals("Formato inválido", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
    @Test
    @DisplayName("Teste de login sem inserir .com no email.")
    public void testEmailImcompleto(){
        login.login("teste@teste", "teste1234");
        Assertions.assertEquals("Formato inválido", login.getTextByCss(seletor.LOGIN_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
}
