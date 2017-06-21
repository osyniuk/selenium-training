import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Task5 {
    WebDriver driver;

    private void fontsCheck(String greyChrome, String greyFF, String redChrome, String redFF) {
        if (driver instanceof ChromeDriver) {
            String discountFont = driver.findElement(By.className("campaign-price")).getCssValue("font-weight");
            Assert.assertEquals("DiscFontIsBold", discountFont, "bold");

            String regularFont = driver.findElement(By.className("regular-price")).getCssValue("text-decoration");
            Assert.assertEquals("RegFontIsStrike", regularFont, "line-through");
            //Assert.assertEquals("RegFontOnMainIsStrike", regularFont, "line-through solid rgb(51, 51, 51)"); //for MAC

            String discountColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
            Assert.assertEquals("DiscColorIsRed", discountColor, redChrome);

            String regularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
            Assert.assertEquals("RegColorIsGrey", regularColor, greyChrome);
        }

        if (driver instanceof FirefoxDriver) {
            String discountFont = driver.findElement(By.className("campaign-price")).getCssValue("font-weight");
            Assert.assertEquals("DiscFontIsBold", discountFont, "700");

            String regularFont = driver.findElement(By.className("regular-price")).getCssValue("text-decoration");
            Assert.assertEquals("RegFontIsStrike", regularFont, "line-through");

            String discountColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
            Assert.assertEquals("DiscColorIsRed", discountColor, redFF);

            String regularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
            Assert.assertEquals("RegColorIsGrey", regularColor, greyFF);
        }

        if (driver instanceof InternetExplorerDriver) {
            String discountFont = driver.findElement(By.className("campaign-price")).getCssValue("font-weight");
            Assert.assertEquals("DiscFontIsBold", discountFont, "700");

            String regularFont = driver.findElement(By.className("regular-price")).getCssValue("text-decoration");
            Assert.assertEquals("RegFontIsStrike", regularFont, "line-through");

            String discountColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
            Assert.assertEquals("DiscColorIsRed", discountColor, redChrome);

            String regularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
            Assert.assertEquals("RegColorIsGrey", regularColor, greyChrome);
        }
    }

    public void itemsCheck() {
        driver.get("http://localhost/litecart/");
        String productNameOnMain = driver.findElement(By.className("name")).getText();

        String discountPriceOnMain = driver.findElement(By.className("campaign-price")).getText();
        String regularPriceOnMain = driver.findElement(By.className("regular-price")).getText();

        fontsCheck("rgba(51, 51, 51, 1)", "rgb(51, 51, 51)", "rgba(204, 0, 0, 1)", "rgb(204, 0, 0)");
        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1']")).click();
        //driver.findElement(By.xpath("//*[@id='content']/ul/li[1]")).click();
        String productNameOnItem = driver.findElement(By.xpath("//*[@id='box-product']//h1")).getText();
        Assert.assertEquals("NamesEquality", productNameOnMain, productNameOnItem);

        String discountPriceOnItem = driver.findElement(By.className("campaign-price")).getText();
        Assert.assertEquals("DiscountPricesEquality", discountPriceOnMain, discountPriceOnItem);

        String regularPriceOnItem = driver.findElement(By.className("regular-price")).getText();
        Assert.assertEquals("RegularPricesEquality", regularPriceOnMain, regularPriceOnItem);


        fontsCheck("rgba(51, 51, 51, 1)", "rgb(51, 51, 51)", "rgba(204, 0, 0, 1)", "rgb(204, 0, 0)");
    }

    @Test
    public void ChromeTest() {
        //ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        itemsCheck();
        driver.quit();

    }

    @Test
    public void FirefoxTest() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        itemsCheck();
        driver.quit();

    }

    @Test
    public void IETest() {
        InternetExplorerDriverManager.getInstance().setup();
        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        itemsCheck();
        driver.quit();

    }


    @After
    public void stop() {
        driver = null;
    }
}