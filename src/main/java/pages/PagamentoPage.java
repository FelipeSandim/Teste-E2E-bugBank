package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PagamentoPage extends BasePage{
        CadastroPage cadastro = new CadastroPage();
        LoginPage login = new LoginPage();
    private static final By pagamento = By.cssSelector("#btn-PAGAMENTOS");
    public void pagamento(){
        cadastro.cadastrarUsuarioComSaldo("teste@teste.com", "Homer Simpson", "teste1234", "teste1234");
        cadastro.fecharModalCadastro();
        login.login("teste@teste.com", "teste1234");
        click(pagamento);
    }
    public String modalPagamento(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("styles__ContainerContent-sc-8zteav-1")));
        WebElement modalDiv = driver.findElement(By.className("styles__ContainerContent-sc-8zteav-1"));
        WebElement modalText = modalDiv.findElement(By.id("modalText"));
        return modalText.getText();
    }
}
