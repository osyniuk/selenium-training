import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task7 {
    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void start() {
        //FirefoxDriverManager.getInstance().setup();
        //driver = new FirefoxDriver();
        //InternetExplorerDriverManager.getInstance().setup();
        //driver = new InternetExplorerDriver();
        ChromeDriverManager.getInstance().setup();
        // ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--kiosk");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,20);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    //get int quantity from Shopping Cart xpath

    private Integer getQuantity() {
        Integer quantity = 0;
        try {
            quantity = Integer.parseInt(driver.findElement(By.className("quantity")).getText());
        } catch (NumberFormatException e) {
            System.out.println("Caught Exception:" + e.getMessage());
        }
        return quantity;
    }

    @Test
    public void addRemoveProduct() {
        driver.get("http://localhost/litecart");
        Integer quantity = getQuantity();

        //switch to Popular Products tab to see different ducks
        driver.findElement(By.xpath("//a[contains(text(), 'Popular Products')]")).click();
        List<WebElement> listOfPopularProducts;

        // Add ducks one by one
        for (int i = 0; i < 3; i++) {
            listOfPopularProducts = driver.findElements(By.xpath("//*[@id='box-popular-products']/div/div"));
            listOfPopularProducts.get(i).click();

            //Select for the Yellow Duck
            if (driver.findElement(By.xpath("//*[@id='box-product']/div[1]/div[2]/h1")).getText().equals("Yellow Duck")) {
                Select sel = new Select(driver.findElement(By.name("options[Size]")));
                sel.selectByValue("Medium");

            }
            driver.findElement(By.xpath("//button[contains(text(), 'Add To Cart')]")).click();

            //close the duck window
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();

            //wait Shopping Cart to contain all products
            //WebDriverWait wait = new WebDriverWait(driver, 20);
            Integer futureQuantity = quantity + 1;
            wait.until(ExpectedConditions.textToBe(By.className("quantity"), futureQuantity.toString()));
            quantity = getQuantity();

            // if (!isElementPresent(By.xpath ("//*[@id='main']/h1"))) {
            //throw new InterruptedException("No element");
        }

        //driver.findElement(By.xpath("a[href='http://localhost/litecart/en/checkout']")).click();
        //driver.manage().window().fullscreen();

        //open the cart
        driver.findElement(By.cssSelector("div#cart")).click();


        // get the collection of ducks in the table
        List<WebElement> listOfProductsInCart = driver.findElements(By.xpath("//*[@id='box-checkout-cart']/div/table/tbody/tr"));
        int numberOfProductsInCart = listOfProductsInCart.size();


        //remove ducks one by one
        for (int j = numberOfProductsInCart - 1; j > (-1); j--) {
            driver.findElement(By.name("remove_cart_item")).click();
            //WebDriverWait wait1 = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//*[@id='box-checkout-cart']/div/table/tbody/tr"), numberOfProductsInCart));
            listOfProductsInCart = driver.findElements(By.xpath("//*[@id='box-checkout-cart']/div/table/tbody/tr"));
            numberOfProductsInCart = listOfProductsInCart.size();
        }


        // verify there are no products in the cart
        driver.findElement(By.xpath("//a[contains(text(), '<< Back')]")).click();
        Assert.assertTrue("0 items in the cart", 0 == getQuantity());

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}