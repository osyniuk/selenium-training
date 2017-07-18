package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page {

  public CatalogPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public CatalogPage open(String baseURL) {
    driver.get(baseURL + "/admin/?app=catalog&doc=catalog");
    return this;
  }

  public Integer getNumberOfProducts() {
    return driver.findElements(By.xpath("//*[@id='main']/form/table/tbody/tr")).size();
  }

}
