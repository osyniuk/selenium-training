package pageObject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CatalogPricesTab extends Page{
  public CatalogPricesTab(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
  public void open() {
    driver.findElement(By.xpath("//a[contains(text(), 'Prices')]")).click();
  }

  @FindBy(name = "purchase_price")
  public WebElement purchasePriceInput;

  public void selectCurrencyCode (String value) {
    Select sel1 = new Select(driver.findElement(By.name("purchase_price_currency_code")));
    sel1.selectByValue(value);
  }
  @FindBy(name = "prices[USD]")
  public WebElement USDPriceInput;

  @FindBy(name = "prices[EUR]")
  public WebElement EURPriceInput;

  public void save () {
    driver.findElement(By.name("save")).click();
  }

}
