package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Elements {

    public static WebDriver driver;

    public static WebDriverWait wait;

    public static WebElement element(By element){
        return driver.findElement(element);
    }

    public static void waitElement(By element){
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
