package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Elements;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeEach
    public void abrirNavegador(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 6);
        Elements.driver = driver;
        Elements.wait = wait;
        driver.get("https://bugbank.netlify.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
    }
    @AfterEach
    public void fecharNavegador(){
            driver.quit();
        }
}
