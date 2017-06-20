import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Task3 {
    WebDriver driver;
    //public WebDriverWait wait;

    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        // wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().deleteAllCookies();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        //driver.findElement(By.xpath("//*[@id=\"box-login\"]/form/div[2]/button")).click();
        driver.findElement(By.cssSelector(".btn-default")).click();
        //Thread.sleep(3000);
        //driver.getPageSource().contains("You are now logged in as admin");
        //driver.findElement(By.className("alert-success"));
        //driver.findElement(By.xpath("//*[@id=\"notices\"]/div[2]/a"));
        //driver.findElement(By.cssSelector(".alert-success"));
        driver.findElement(By.cssSelector(".fa-check-circle"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}