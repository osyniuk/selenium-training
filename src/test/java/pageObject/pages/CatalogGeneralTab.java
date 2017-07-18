package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class CatalogGeneralTab extends Page {
    public CatalogGeneralTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String baseURL) {
        driver.get(baseURL + "/admin/?category_id=0&app=catalog&doc=edit_product");
    }

    @FindBy(xpath = "//label[contains(text(), ' Enabled')]")
    public WebElement enableProduct;

    @FindBy(xpath = "//input[@value='1-3']")
    public WebElement productGroupsInput;

    @FindBy(name = "date_valid_from")
    public WebElement dateValidFromInput;

    @FindBy(name = "date_valid_to")
    public WebElement dateValidToInput;

    @FindBy(name = "code")
    public WebElement codeInput;

    @FindBy(name = "name[en]")
    public WebElement nameInput;

    @FindBy(name = "sku")
    public WebElement skuInput;

    @FindBy(name = "gtin")
    public WebElement gtinInput;

    @FindBy(name = "taric")
    public WebElement taricInput;

    @FindBy(name = "quantity")
    public WebElement quantityInput;

    @FindBy(name = "weight")
    public WebElement weightInput;

    @FindBy(name = "dim_x")
    public WebElement dimXInput;

    @FindBy(name = "dim_y")
    public WebElement dimYInput;

    @FindBy(name = "dim_z")
    public WebElement dimZInput;

    public void selectSoldOutStatus(int statusId) {
        Select sel = new Select(driver.findElement(By.name("sold_out_status_id")));
        sel.selectByIndex(statusId);
    }

    public void uploadFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
    }
}
