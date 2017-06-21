import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Task8 {
    WebDriver driver;

    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void countryExtLink() {

        //login

        driver.get("http://localhost/litecart/admin/");
        driver.manage().deleteAllCookies();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-default")).click();
        driver.findElement(By.cssSelector(".fa-check-circle"));

        //Open Countries-"Add New Country"

        driver.findElement(By.xpath("//span[contains(text(), 'Countries')]")).click();
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=countries&doc=edit_country']")).click();

        //Open All External Links

        List<WebElement> listOfExtLinks = driver.findElements(By.xpath("//label/a/i"));
        for (int i = 0; i < listOfExtLinks.size(); i++) {
            listOfExtLinks = driver.findElements(By.xpath("//label/a/i"));
            listOfExtLinks.get(i).click();
            //Assert.assertTrue("Number of browser windows", driver.getWindowHandles().size() == i+2);
        }

        Assert.assertTrue("Number of browser windows", driver.getWindowHandles().size() == 8);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}