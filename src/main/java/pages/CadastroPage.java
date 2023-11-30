package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CadastroPage extends BasePage{
    private static final By botaoCadastro = By.cssSelector(" div.pages__FormBackground-sc-1ee1f2s-2.jNpkvU > div > div.card__login > form > div.login__buttons > button.style__ContainerButton-sc-1wsixal-0.ihdmxA.button__child");
    private static final By campoEmail = By.cssSelector("div:nth-child(2) > input");
    private static final By campoNome = By.cssSelector(" div:nth-child(3) > input");
    private static final By campoSenha = By.cssSelector("div:nth-child(4) > div > input");
    private static final By campoConfirmarSenha = By.cssSelector("div:nth-child(5) > div > input");
    private static final By contaComSaldo = By.id("toggleAddBalance");
    private static final By cadastrar = By.cssSelector(" div.card__register > form > button");
    private static final By fecharModal = By.cssSelector("div.styles__ContainerCloseButton-sc-8zteav-2.ffzYTz > a");

    public void cadastrarUsuarioComSaldo(String email, String nome, String senha, String confirmaSenha){
        click(botaoCadastro);
        sendKeys(campoEmail, email);
        sendKeys(campoNome, nome);
        sendKeys(campoSenha, senha);
        sendKeys(campoConfirmarSenha, confirmaSenha);
        click(contaComSaldo);
        click(cadastrar);
    }
    public void cadastrarUsuarioSemSaldo(String email, String nome, String senha, String confirmaSenha){
        click(botaoCadastro);
        sendKeys(campoEmail, email);
        sendKeys(campoNome, nome);
        sendKeys(campoSenha, senha);
        sendKeys(campoConfirmarSenha, confirmaSenha);
        click(cadastrar);
    }
    public String modalCadastro(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("styles__Container-sc-8zteav-0")));
        WebElement modalDiv = driver.findElement(By.className("styles__Container-sc-8zteav-0"));
        WebElement modalText = modalDiv.findElement(By.id("modalText"));
        return modalText.getText();
    }
    public void fecharModalCadastro(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("styles__Container-sc-8zteav-0")));
        click(fecharModal);
    }
    public String getTextByCss(String seletor){
        return driver.findElement(By.cssSelector(seletor)).getText();
    }
}
