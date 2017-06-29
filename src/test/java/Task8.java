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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Task8 {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
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
        Assert.assertTrue(driver.findElements(By.cssSelector(".fa-check-circle")).size()>0);

        //Open Countries-"Add New Country"

        driver.findElement(By.xpath("//span[contains(text(), 'Countries')]")).click();
        driver.findElement(By.xpath("//*[@id='main']/ul/li/a")).click();

        //Get List of All External Links

        List<WebElement> listOfExtLinks = driver.findElements(By.xpath("//label/a/i"));
        for (int i = 0; i < listOfExtLinks.size(); i++) {

            //Get window handles - original and all

            String originalWindowHandle = driver.getWindowHandle();
            Set<String> listOfWindowHandles = driver.getWindowHandles();

            //click through all links

            listOfExtLinks = driver.findElements(By.xpath("//label/a/i"));
            listOfExtLinks.get(i).click();

            //wait for a new window to open, switch to it, close it

            String newWindow = wait.until(anyWindowOtherThan(listOfWindowHandles));
            driver.switchTo().window(newWindow);
            driver.close();

            //switch back to original window

            driver.switchTo().window(originalWindowHandle);

            /*String subWindowHandler = null;
           Iterator<String> iterator = listOfWindowHandles.iterator();
            while (iterator.hasNext()) {
                subWindowHandler = iterator.next();
                driver.switchTo().window(subWindowHandler);*/
        }
        // Assert.assertTrue("Number of browser windows", driver.getWindowHandles().size() == 8);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}