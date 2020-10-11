package com.one;

import com.one.framework.Browser;
import com.one.framework.WebDriverConfig;
import com.one.ui.domains.Product;
import com.one.ui.pages.LoginForm;
import com.one.ui.pages.ProductsContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Reporter;

import javax.inject.Inject;

@ContextConfiguration(classes = {LoginForm.class, WebDriverConfig.class, Browser.class})
public class MainPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginForm loginForm;

    @Inject
    private Browser browser;

    private ProductsContent productsContent;
    private Product product;


    @DataProvider(name = "test1")
    public Object[] createDataTest1() {
        return new Product[]{ new Product("Sauce Labs Backpack",
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "$29.99",
                "ADD TO CARD") };
    }

    @DataProvider(name = "test2")
    public Object[] createDataTest2() {
        return new Product[]{ new Product("Sauce Labs Bike Light",
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                "$9.99",
                "ADD TO CARD") };
    }


    @DataProvider(name = "test3")
    public Object[] createDataTest3() {
        return new Product[]{ new Product("Sauce Labs Bolt T-Shirt",
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                "$15.99",
                "ADD TO CARD") };
    }


    @DataProvider(name = "test4")
    public Object[] createDataTest4() {
        return new Product[]{ new Product("Sauce Labs Fleece Jacket",
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                "$49.99",
                "ADD TO CARD") };
    }

    @DataProvider(name = "test5")
    public Object[] createDataTest5() {
        return new Product[]{ new Product("Sauce Labs Onesie",
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                "$7.99",
                "ADD TO CARD") };
    }

    @Test(dataProvider = "test1")
    public void verifyProductOnFirstPosition(Product p) {
        Reporter.log("Verify that the first product in the list - " + p.getName() +" - is displayed correctly");
        product = productsContent.getProductFromPosition(0);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(product.getName().matches(p.getName()), "The name is not the expected one");
        softAssert.assertTrue(product.getDescription().matches(p.getDescription()), "The description is not the expected one");
        softAssert.assertTrue(product.getPrice().matches(p.getPrice()), "The price is not the expected one");
    }

    @Test(dataProvider = "test2")
    public void verifyProductOnSecondPosition(Product p) {
        Reporter.log("Verify that the second product in the list - " + p.getName() + " -  is displayed correctly");
        product = productsContent.getProductFromPosition(1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(product.getName().matches(p.getName()), "The name is not the expected one");
        softAssert.assertTrue(product.getDescription().matches(p.getDescription()), "The description is not the expected one");
        softAssert.assertTrue(product.getPrice().matches(p.getPrice()), "The price is not the expected one");
    }

    @Test(dataProvider = "test5")
    public void verifyProductOnPositionFive(Product p) {
        Reporter.log("Verify that the fifth product in the list - " + p.getName() + " -  is displayed correctly");
        product = productsContent.getProductFromPosition(4);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(product.getName().matches(p.getName()), "The name is not the expected one");
        softAssert.assertTrue(product.getDescription().matches(p.getDescription()), "The description is not the expected one");
        softAssert.assertTrue(product.getPrice().matches(p.getPrice()), "The price is not the expected one");
    }

    //TODO - add method for sorting on Header class
   /* @Test(dataProvider = "test5")
    public void verifyProductOnPositionOneAfterSortByPrice(Product p) {
        product = productsContent.getProductFromPosition(0);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(product.getName().matches(p.getName()), "The name is not the expected one");
        softAssert.assertTrue(product.getDescription().matches(p.getDescription()), "The description is not the expected one");
        softAssert.assertTrue(product.getPrice().matches(p.getPrice()), "The price is not the expected one");
    }
*/

    @BeforeClass(alwaysRun = true)
    @Parameters({"username", "password"})
    public void beforeTestMethod(String username, String password) {
        productsContent = new ProductsContent(browser);
        loginForm.loginAs(username, password);
    }
}
