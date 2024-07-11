package practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;
import pageObjects.cartPage;
import pageObjects.ordersPage;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String,String> input) throws IOException {

		ProductCatalog pc = lp.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));
		cartPage cp = pc.goToCartPage();

		Boolean match = cp.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = cp.goToCheckout();

		CheckoutPage.sendValueToDropdwn("ind");
		CheckoutPage.selectCountryByName("india");
		ConfirmationPage confPage = CheckoutPage.submitOrder();
		String confirmMsg = confPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
/*
	// To verify ZARA COAT 3 is displayed on the orders page - below is creation of
	// a dependent method

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalog pc = lp.loginApplication("divsel@practice.com", "Getinman15");
		ordersPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrdersDisplay(productName));

	}
//Method-1 to send data
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"divsel@practice.com","Getinman15","ZARA COAT 3"},{"divpract@sel.com","Getinman6","ADIDAS ORIGINAL"}};
	}
	
	
//Method-2 to send data
	@DataProvider
	public Object[][] getData() {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("email", "divsel@practice.com");
		map.put("password", "Getinman15");
		map.put("product", "ZARA COAT 3");
		
		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		map1.put("email", "divpract@sel.com");
		map1.put("password", "Getinman6");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},
				{map1} };
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}