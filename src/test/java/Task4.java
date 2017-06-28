import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Task4 {
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

    public boolean isElementPresent (By locator) {
        return driver.findElements(locator).size() >0;
    }


    @Test
    public void allSections() throws InterruptedException

    {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().deleteAllCookies();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-default")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector(".fa-check-circle")).size()>0);
        List<WebElement> listOfParentSections = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));

        for(int i =0;i<listOfParentSections.size();i++) {
            listOfParentSections = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));
            listOfParentSections.get(i).click();
           /* if (!isElementPresent(By.xpath ("//*[@id='main']/h1"))) {
                throw new InterruptedException("No header on "+ i + " menu element");
            }*/
           Assert.assertTrue("h1 element not found in parent menu", isElementPresent(By.cssSelector("h1")));
            List <WebElement> listOfChildSections = driver.findElements(By.xpath("//*[@class='docs']/li"));
            // for(int j =1;j<listOfChildSections.size();j++)
            for(int j =0;j<listOfChildSections.size();j++) {
                listOfChildSections = driver.findElements(By.xpath("//*[@class='docs']/li"));
                listOfChildSections.get(j).click();
                /*if (!isElementPresent(By.xpath("//*[@id='main']/h1"))) {
                    throw new InterruptedException("No header on " + j + " submenu element" + "in" + i + "parent menu");
                }*/
                Assert.assertTrue("h1 element not found in child menu", isElementPresent(By.cssSelector("h1")));
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}