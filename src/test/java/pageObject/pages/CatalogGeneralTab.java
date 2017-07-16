package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class CatalogGeneralTab extends Page{
    public CatalogGeneralTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }



    @FindBy (xpath = "//label[contains(text(), ' Enabled')]")
    public WebElement enableProduct;

    @FindBy (xpath = "//input[@name='product_groups[]']")
    public WebElement productGroupsInput;

    @FindBy (xpath = "//input[@name='date_valid_from']")
    public WebElement dateValidFromInput;

    @FindBy (xpath = "//input[@name='date_valid_to']")
    public WebElement dateValidToInput;

    @FindBy (xpath = "//input[@name='code']")
    public WebElement codeInput;

    @FindBy (xpath = "//input[@name='name[en]']")
    public WebElement nameInput;

    @FindBy (xpath = "//input[@name='sku']")
    public WebElement skuInput;

    @FindBy (xpath = "//input[@name='gtin']")
    public WebElement gtinInput;

    @FindBy (xpath = "//input[@name='taric']")
    public WebElement taricInput;

    @FindBy (xpath = "//input[@name='quantity']")
    public WebElement quantityInput;

    @FindBy (xpath = "//input[@name='weight']")
    public WebElement weightInput;

    @FindBy (xpath = "//input[@name='dim_x']")
    public WebElement dimXInput;

    @FindBy (xpath = "//input[@name='dim_y']")
    public WebElement dimYInput;

    @FindBy (xpath = "//input[@name='dim_z']")
    public WebElement dimZInput;

    public void selectSoldOutStatus(int statusId) {
        Select sel = new Select(driver.findElement(By.name("sold_out_status_id")));
        sel.selectByIndex(statusId);
    }

    public void uploadFile (String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(file.getAbsolutePath());
    }
}
