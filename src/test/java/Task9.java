import com.google.common.io.Files;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Task9 {
    static EventFiringWebDriver driver;

    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Test
    public void allSections() throws InterruptedException, IOException {
        //login
        driver.get("http://localhost/litecart/admin/");
        driver.manage().deleteAllCookies();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-default")).click();

        //Verify successful login message is present
        driver.findElement(By.cssSelector(".fa-check-circle"));

        //get the list of parent menu sections
        List<WebElement> listOfParentSections = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));

        //click through the parent menu
        for (int i = 0; i < listOfParentSections.size(); i++) {
            listOfParentSections = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));
            listOfParentSections.get(i).click();

            //throw exception if header wasn't found
            if (!isElementPresent(By.xpath("//*[@id='main']/h1"))) {
                throw new InterruptedException("No header on " + i + " menu element");
            }

            //get the list of child menu sections
            List<WebElement> listOfChildSections = driver.findElements(By.xpath("//*[@class='docs']/li"));

            // for(int j =1;j<listOfChildSections.size();j++)
            //click through the child menu. if starting from the first element, Modules-Customer Modules is not clicked
            for (int j = 0; j < listOfChildSections.size(); j++) {
                listOfChildSections = driver.findElements(By.xpath("//*[@class='docs']/li"));
                listOfChildSections.get(j).click();

                //this locator doesn't find anything and throws an exception to get a screenshot
                driver.findElement((By.xpath("//*[@id='main']/h1TestExcept")));

                ///throw exception if header wasn't found
                if (!isElementPresent(By.xpath("//*[@id='main']/h1"))) {
                    throw new InterruptedException("A header on " + j + " submenu element" + " in " + i + " parent menu");
                }
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public static class MyListener extends AbstractWebDriverEventListener {

        //Add logging before and after find element
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);

        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            takeScreenshot();
        }
    }

    //method for adding screenshot capture on exceptions
    public static void takeScreenshot() {
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File("screenshot" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}