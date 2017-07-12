package pageObject.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
//import com.tngtech.java.junit.dataprovider.DataProviders;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pageObject.model.Product;

@RunWith(DataProviderRunner.class)


public class AddProductTest extends TestBase{
Integer initialNumberOfProducts, finalNumberOfProducts;
    @Test
    @UseDataProvider(value="validProducts", location = DataProviders.class)
    public void canAddNewProduct (Product product) {
        initialNumberOfProducts = app.getNumberOfProducts();
        app.addProductToCatalog(product);
        finalNumberOfProducts = app.getNumberOfProducts();
        Assert.assertTrue(initialNumberOfProducts==finalNumberOfProducts+1);


    }

}
