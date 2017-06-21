import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Task7 {
    WebDriver driver;

    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void addNewProduct() throws InterruptedException {
        driver.get("http://localhost/litecart");
        String quantity = driver.findElement(By.className("quantity")).getText();
        WebElement firstProduct = driver.findElement(By.className("name"));
        firstProduct.click();
        if (firstProduct.getText().equals("Yellow Duck")) {
            Select sel = new Select(driver.findElement(By.name("options[Size]")));
            sel.selectByValue("Medium");
        }
        driver.findElement(By.xpath("//button[contains(text(), 'Add To Cart')]")).click();
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.textToBe(By.className("quantity"), String.valueOf(quantity.getText() + 1)));
        System.out.println(String.valueOf(quantity + 1));


        Thread.sleep(3000);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}