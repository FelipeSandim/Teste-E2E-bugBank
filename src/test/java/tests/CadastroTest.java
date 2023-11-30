package tests;

import commons.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CadastroPage;
import utils.SeletoresAssert;

public class CadastroTest extends BaseTest {
    SeletoresAssert seletor = new SeletoresAssert();
    CadastroPage cadastro = new CadastroPage();
    @Test
    public void testCadastroComSaldoComSucesso() {
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        Assertions.assertTrue(cadastro.modalCadastro().toString().contains("A conta "));
        Assertions.assertTrue(cadastro.modalCadastro().toString().contains(" foi criada com sucesso"));
    }
    @Test
    public void testCadastroSemSaldoComSucesso() {
        cadastro.cadastrarUsuarioSemSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        Assertions.assertTrue(cadastro.modalCadastro().toString().contains("A conta "));
        Assertions.assertTrue(cadastro.modalCadastro().toString().contains(" foi criada com sucesso"));
    }
    @Test
    public void testCadastroCamposVazios() {
        cadastro.cadastrarUsuarioComSaldo("", "", "", "");
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_SENHA));
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_CONFIRMAR_SENHA));
    }
    @Test
    public void testCadastroCampoEmailVazio() {
        cadastro.cadastrarUsuarioComSaldo("", "Homer Simpson", "teste1234", "teste1234");
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
    @Test
    public void testCadastroCampoNomeVazio() {
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "", "teste1234", "teste1234");
        Assertions.assertTrue(cadastro.modalCadastro().toString().contains("Nome não pode ser vazio."));
    }
    @Test
    public void testCampoSenhaVazio(){
        cadastro.cadastrarUsuarioComSaldo("teste2teste.com", "Homer Simpson", "", "teste1234");
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_SENHA));
    }
    @Test
    public void testCampoConfirmarSenhaVazio(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "");
        Assertions.assertEquals("É campo obrigatório", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_CONFIRMAR_SENHA));
    }
    @ParameterizedTest
    @MethodSource("dataProvider.ModelDataProvider#emailModel")
    @DisplayName("Teste parametrizado com diferentes inserções de email em formato invalido.")
    public void testCampoEmailForaDoPAdrao(String email, String nome, String senha, String confirmaSenha){
        cadastro.cadastrarUsuarioSemSaldo(email, nome, senha, confirmaSenha);
        Assertions.assertEquals("Formato inválido", cadastro.getTextByCss(seletor.CADASTRO_MENSAGEM_DE_ERRO_CAMPO_EMAIL));
    }
}

