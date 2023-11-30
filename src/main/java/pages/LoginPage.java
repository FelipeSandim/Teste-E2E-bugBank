package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    private static final By campoEmail = By.cssSelector("#__next > div > div.pages__FormBackground-sc-1ee1f2s-2.jNpkvU > div > div.card__login > form > div.style__ContainerFieldInput-sc-s3e9ea-0.kOeYBn.input__child > input");
    private static final By campoSenha = By.cssSelector("div > div.card__login > form > div.login__password > div > input");
    private static final By acessar = By.cssSelector(" button.style__ContainerButton-sc-1wsixal-0.otUnI.button__child");
    public void login(String email, String senha){
        click(acessar);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, senha);
        click(acessar);
    }
    public String modalLogin(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("styles__ContainerContent-sc-8zteav-1")));
        WebElement modalDiv = driver.findElement(By.className("styles__ContainerContent-sc-8zteav-1"));
        WebElement modalText = modalDiv.findElement(By.id("modalText"));
        return modalText.getText();
    }
    public String getTextByCss(String seletor){
        return driver.findElement(By.cssSelector(seletor)).getText();
    }
}
