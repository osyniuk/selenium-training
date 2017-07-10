package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage open (String baseURL) {
        driver.get(baseURL + "/admin");
        return this;
    }

    public boolean isOnLoginPage () {
        return driver.findElements(By.id("box-login")).size()>0;
    }

    public LoginPage enterUsername (String username) {
        driver.findElement(By.name("username")).sendKeys("admin");
        return this;
    }

    public LoginPage enterPassword (String password) {
        driver.findElement(By.name("password")).sendKeys("admin");
        return this;
    }

    public void submitLogin () {
        driver.findElement(By.cssSelector(".btn-default")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector(".fa-check-circle")));
    }



}
