package pages;

import model.ContaModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TransferenciaPage extends BasePage {
    CadastroPage cadastro = new CadastroPage();
    LoginPage login = new LoginPage();
    private ContaModel contaOrigem;
    private static final By botaoSair = By.cssSelector("div.home__Header-sc-1auj767-1.cVGUzT > div");
    private static final By botaoTransferencia = By.cssSelector("#btn-TRANSFERÊNCIA");
    private static final By numeroDaConta = By.cssSelector("div:nth-child(1) > input");
    private static final By digitoDaConta = By.cssSelector("div.account__data > div:nth-child(2) > input");
    private static final By valorTransferencia = By.cssSelector("div.style__ContainerFieldInput-sc-s3e9ea-0.kOeYBn.input__child > input");
    private static final By descricaoTransferencia = By.cssSelector("div:nth-child(3) > input");
    private static final By botaoTransferir = By.cssSelector("form > button");

    public void contaUm(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste@teste.com", "teste1234");
        contaOrigem = informacoesDaConta();
        click(botaoSair);
    }
    public void contaDois(String valor, String descricao){
        cadastro.cadastrarUsuarioComSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        click(botaoTransferencia);
        sendKeys(numeroDaConta, contaOrigem.getNumero());
        sendKeys(digitoDaConta, contaOrigem.getDigito());
        click(botaoTransferir);
        sendKeys(valorTransferencia, valor);
        sendKeys(descricaoTransferencia, descricao);
        click(botaoTransferir);
    }
    public void contaSemSaldo(String valor, String descricao){
        cadastro.cadastrarUsuarioSemSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        click(botaoTransferencia);
        sendKeys(numeroDaConta, contaOrigem.getNumero());
        sendKeys(digitoDaConta, contaOrigem.getDigito());
        click(botaoTransferir);
        sendKeys(valorTransferencia, valor);
        sendKeys(descricaoTransferencia, descricao);
        click(botaoTransferir);
    }
    public void MesmaConta(String valor, String descricao){
        cadastro.cadastrarUsuarioComSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        contaOrigem = informacoesDaConta();
        click(botaoTransferencia);
        sendKeys(numeroDaConta, contaOrigem.getNumero());
        sendKeys(digitoDaConta, contaOrigem.getDigito());
        click(botaoTransferir);
        sendKeys(valorTransferencia, valor);
        sendKeys(descricaoTransferencia, descricao);
        click(botaoTransferir);
    }
    public void contaUnica(String conta, String digito,String valor, String descricao){
        cadastro.cadastrarUsuarioComSaldo("teste2@teste.com", "Seu Madruga", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste2@teste.com", "teste1234");
        click(botaoTransferencia);
        sendKeys(numeroDaConta, conta);
        sendKeys(digitoDaConta, digito);
        click(botaoTransferir);
        sendKeys(valorTransferencia, valor);
        sendKeys(descricaoTransferencia, descricao);
        click(botaoTransferir);
    }
    private ContaModel informacoesDaConta() {
        WebElement elementoConta = driver.findElement(By.cssSelector("#textAccountNumber > span"));
        String numeroEDigito = elementoConta.getText();
        String[] partes = numeroEDigito.split("-");
        return new ContaModel(partes[0], partes[1]);
    }
    public String modalTransferencia(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("styles__ContainerContent-sc-8zteav-1")));
        WebElement modalDiv = driver.findElement(By.className("styles__ContainerContent-sc-8zteav-1"));
        WebElement modalText = modalDiv.findElement(By.id("modalText"));
        return modalText.getText();
    }
    public String getTextByCss(){
        return driver.findElement(By.cssSelector("div.style__ContainerFieldInput-sc-s3e9ea-0.kOeYBn.input__child > p")).getText();
    }
}
