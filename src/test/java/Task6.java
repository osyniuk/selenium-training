import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task6 {
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
    public void addNewProduct() {

        //login

        driver.get("http://localhost/litecart/admin/");
        driver.manage().deleteAllCookies();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-default")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector(".fa-check-circle")).size()>0);

        //Navigate to Catalog

        driver.findElement(By.xpath("//span[contains(text(), 'Catalog')]")).click();

        //Get the number of products in the Catalog

        int initialNumberOfProducts = driver.findElements(By.xpath("//*[@id='main']/form/table/tbody/tr")).size();

        //Click "Add new product"

        driver.findElement(By.xpath("//*[@id='main']/ul/li[3]/a")).click();

        //General tab

        driver.findElement(By.xpath("//label[contains(text(), 'Enabled')]")).click();
        driver.findElement(By.xpath("//input[@name='product_groups[]']")).click();
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("0202" + Keys.ARROW_RIGHT + "2016");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("2010" + Keys.ARROW_RIGHT + "2018");
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("03115");
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("Test Product");
        driver.findElement(By.xpath("//input[@name='sku']")).sendKeys("Manufacturer");
        driver.findElement(By.xpath("//input[@name='gtin']")).sendKeys("22332211");
        driver.findElement(By.xpath("//input[@name='taric']")).sendKeys("99887766");
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Keys.DELETE + "3");
        driver.findElement(By.xpath("//input[@name='weight']")).sendKeys(Keys.DELETE + "20");
        driver.findElement(By.xpath("//input[@name='dim_x']")).sendKeys(Keys.DELETE + "80");
        driver.findElement(By.xpath("//input[@name='dim_y']")).sendKeys(Keys.DELETE + "100");
        driver.findElement(By.xpath("//input[@name='dim_z']")).sendKeys(Keys.DELETE + "50");
        Select sel = new Select(driver.findElement(By.name("sold_out_status_id")));
        sel.selectByIndex(2);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Att_Sample1.png").getFile());
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(file.getAbsolutePath());

        //Information tab

        driver.findElement(By.xpath("//a[contains(text(), 'Information')]")).click();
        Select sel1 = new Select(driver.findElement(By.name("manufacturer_id")));
        sel1.selectByIndex(1);
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("50, " + "keyword1");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Short descript");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Description");
        driver.findElement(By.xpath("//textarea[@name='attributes[en]']")).sendKeys("Attributes");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("Head Title");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("Meta descript");

        //Prices tab

        driver.findElement(By.xpath("//a[contains(text(), 'Prices')]")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.DELETE + "99");
        Select sel2 = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        sel2.selectByValue("EUR");
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys("68");
        driver.findElement(By.xpath("//input[@name='prices[EUR]']")).sendKeys("65");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        //Verify product was added to the Catalog

        int finalNumberOfProducts = driver.findElements(By.xpath("//*[@id='main']/form/table/tbody/tr")).size();
        Assert.assertTrue("One more product in the Catalog", finalNumberOfProducts==initialNumberOfProducts + 1);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}