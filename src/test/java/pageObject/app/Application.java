package pageObject.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.model.Product;
import pageObject.pages.*;

public class Application {
    private WebDriver driver;
    private LoginPage loginPage;
    private CatalogPage catalogPage;
    private CatalogGeneralTab catalogGeneralTab;
    private CatalogInformationTab catalogInformationTab;
    private CatalogPricesTab catalogPricesTab;

    private String baseURL = "http://localhost/litecart";

    public Application (){
        driver = new ChromeDriver();
        loginPage = new LoginPage (driver);
        catalogPage = new CatalogPage (driver);
        catalogGeneralTab = new CatalogGeneralTab(driver);
        catalogInformationTab = new CatalogInformationTab(driver);
        catalogPricesTab = new CatalogPricesTab (driver);

    }
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void addProductToCatalog (Product product) {
catalogPage.open(baseURL);
catalogGeneralTab.enableProduct.click();
catalogGeneralTab.productGroupsInput.click();
catalogGeneralTab.dateValidFromInput.sendKeys(product.getDateValidFrom());
catalogGeneralTab.dateValidToInput.sendKeys(product.getDateValidTo());
catalogGeneralTab.codeInput.sendKeys(product.getCode());
catalogGeneralTab.nameInput.sendKeys(product.getName());
catalogGeneralTab.skuInput.sendKeys(product.getSku());
catalogGeneralTab.gtinInput.sendKeys(product.getGtin());
catalogGeneralTab.taricInput.sendKeys(product.getTaric());
catalogGeneralTab.quantityInput.sendKeys(product.getQuantity());
catalogGeneralTab.weightInput.sendKeys(product.getWeight());
catalogGeneralTab.dimXInput.sendKeys(product.getDimX());
catalogGeneralTab.dimYInput.sendKeys(product.getDimY());
catalogGeneralTab.dimZInput.sendKeys(product.getDimZ());
catalogGeneralTab.selectSoldOutStatus(product.getStatusId());
catalogGeneralTab.uploadFile(product.getFileName());

catalogInformationTab.open(baseURL);
catalogInformationTab.selectManufacturer(product.getManId());
catalogInformationTab.keywordsInput.sendKeys(product.getKeywords());
catalogInformationTab.shortDescriptionInput.sendKeys(product.getShortDescription());
catalogInformationTab.descriptionInput.sendKeys(product.getDescription());
catalogInformationTab.attributesInput.sendKeys(product.getAttributes());
catalogInformationTab.headTitleInput.sendKeys(product.getHeadTitle());
catalogInformationTab.metaDescriptionInput.sendKeys(product.getMetaDescription());

catalogPricesTab.open(baseURL);
catalogPricesTab.purchasePriceInput.sendKeys(product.getPurchasePrice());
catalogPricesTab.selectCurrencyCode(product.getCurrencyCode());
catalogPricesTab.USDPriceInput.sendKeys(product.getUSDPrice());
catalogPricesTab.EURPriceInput.sendKeys(product.getEURPrice());
catalogPricesTab.save();

    }

    public Integer getNumberOfProducts() {
        if(loginPage.open(baseURL).isOnLoginPage()) {
            loginPage.enterUsername("admin").enterPassword("admin").submitLogin();
        }
        return catalogPage.open(baseURL).getNumberOfProducts();
    }
}
