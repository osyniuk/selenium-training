package pageObject.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.openqa.selenium.Keys;
import pageObject.model.Product;

public class DataProviders {
    @DataProvider
    public static Object[][] validProducts() {
        return new Object[][]{
                {
                        Product.newEntity()
                                .withDateValidFrom("0202" + Keys.ARROW_RIGHT + "2016")
                                .withDateValidTo("2010" + Keys.ARROW_RIGHT + "2018")
                                .withCode("03115")
                                .withName("Test Product")
                                .withSku("Manufacturer")
                                .withGtin("22332211")
                                .withTaric("99887766")
                                .withQuantity(Keys.DELETE + "3")
                                .withWeight(Keys.DELETE + "20")
                                .withDimX(Keys.DELETE + "80")
                                .withDimY(Keys.DELETE + "100")
                                .withDimZ(Keys.DELETE + "50")
                                .withStatusId(2)
                                .withFileName("Att_Sample1.png")
                                .withManId(1)
                                .withKeywords("50, " + "keyword1")
                                .withShortDescription("Short description")
                                .withDescription("Description")
                                .withAttributes("Attributes")
                                .withHeadTitle("Head Title")
                                .withMetaDescription("Meta description")
                                .withPurchasePrice(Keys.DELETE + "99")
                                .withCurrencyCode("EUR")
                                .withUSDPrice("68")
                                .withEURPrice("65").build()
                }
        };
    }
}
