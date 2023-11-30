package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SaquePage extends BasePage{
    CadastroPage cadastro = new CadastroPage();
    LoginPage login = new LoginPage();
    private static final By saque = By.cssSelector("#btn-SAQUE");
    public void saque(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste@teste.com", "teste1234");
        click(saque);
    }
    public String modalSaque(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("styles__ContainerContent-sc-8zteav-1")));
        WebElement modalDiv = driver.findElement(By.className("styles__ContainerContent-sc-8zteav-1"));
        WebElement modalText = modalDiv.findElement(By.id("modalText"));
        return modalText.getText();
    }
}
