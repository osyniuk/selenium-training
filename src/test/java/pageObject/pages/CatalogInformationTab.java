package pageObject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CatalogInformationTab extends Page {
    public CatalogInformationTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String baseURL) {
        driver.findElement(By.xpath("//a[contains(text(), 'Information')]")).click();//!!!
    }

    public void selectManufacturer (int manId) {
        Select sel1 = new Select(driver.findElement(By.name("manufacturer_id")));
        sel1.selectByIndex(manId);

    }
    @FindBy(xpath = "//input[@name='keywords']")
    public WebElement keywordsInput;

    @FindBy(xpath = "//input[@name='short_description[en]']")
    public WebElement shortDescriptionInput;

    @FindBy(className = "trumbowyg-editor")
    public WebElement descriptionInput;

    @FindBy(xpath = "//textarea[@name='attributes[en]']")
    public WebElement attributesInput;

    @FindBy(xpath = "//input[@name='head_title[en]']")
    public WebElement headTitleInput;

    @FindBy(xpath = "//input[@name='meta_description[en]']")
    public WebElement metaDescriptionInput;
}
