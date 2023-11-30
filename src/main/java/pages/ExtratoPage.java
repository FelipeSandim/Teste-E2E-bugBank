package pages;

import org.openqa.selenium.By;
import java.text.NumberFormat;
import java.util.Locale;

public class ExtratoPage extends BasePage{
    private String valor;
    TransferenciaPage transferencia = new TransferenciaPage();
    CadastroPage cadastro = new CadastroPage();
    LoginPage login = new LoginPage();
    private static By botaoExtrato = By.cssSelector("#btn-EXTRATO");
    private static By fecharModal = By.cssSelector("div.styles__ContainerCloseButton-sc-8zteav-2.ffzYTz > a");
    private static By voltar = By.cssSelector("#btnBack");
    private static By botaoSair = By.cssSelector("#btnExit");
    public void extratoSimplesComSaldoNaConta(){
        cadastro.cadastrarUsuarioComSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        click(botaoExtrato);
    }
    public void extratoSimplesSemSaldoNaConta(){
        cadastro.cadastrarUsuarioSemSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        click(botaoExtrato);
    }
    public void extratoComTransferencia(String valor){
        transferencia.contaUm();
        this.valor = valor;
        transferencia.contaDois(this.valor, "");
        click(fecharModal);
        click(voltar);
        click(botaoExtrato);
    }
    public void extratoComTransferenciaRecebida(String valor){
        transferencia.contaUm();
        this.valor = valor;
        transferencia.contaDois(this.valor, "");
        click(fecharModal);
        click(botaoSair);
        login.login("teste@teste.com", "teste1234");
        click(botaoExtrato);
    }

    public String sobraEmConta(){
        double resultado = 1000 - Double.parseDouble(valor);
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String resultadoFormatado = formatoMoeda.format(resultado).toString().replace("\u00A0", " ").replace(".",",");
        return resultadoFormatado;
    }
    public String recebidoEmConta(){
        double resultado = 1000 + Double.parseDouble(valor);
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String resultadoFormatado = formatoMoeda.format(resultado).toString().replace("\u00A0", " ");
        return resultadoFormatado;
    }
    public String valores(){
        return valor.toString().replace(".",",");
    }
    public String getTextByCss(String seletor){
        return  driver.findElement(By.cssSelector(seletor)).getText();
    }
}
