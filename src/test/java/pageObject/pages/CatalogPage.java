package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page {
    //@FindBy (xpath="//*[@id='main']/form/table/tbody/tr")


    public CatalogPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public CatalogPage open(String baseURL) {//?????
        driver.findElement(By.xpath("//span[contains(text(), 'Catalog')]")).click();
        return this;
    }
    public Integer getNumberOfProducts()  {
        return driver.findElements(By.xpath("//*[@id='main']/form/table/tbody/tr")).size();
    }

    public void clickAddNewProduct()  {
        //driver.get(baseURL + "/admin/?app=catalog&doc=catalog&category_id=0");
        driver.findElement(By.xpath("//*[@id='main']/ul/li[3]/a")).click();//!!!

    }
    //driver.findElement(By.xpath("//*[@id='main']/ul/li[3]/a")).click();
}
