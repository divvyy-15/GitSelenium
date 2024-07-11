package practice;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductCatalog;
import pageObjects.cartPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

		@Test(groups= {"ErrorValidations"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation() throws IOException
		{
		
		lp.loginApplication("divsel@practice.com", "Getinman25");
		//Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", lp.getErrorMessage()); //just to test screenshot code addition in listeners is working or not

	}
		@Test
		public void ProductErrorValidation() throws IOException
		{
		
		String productName = "ZARA COAT 3";
		ProductCatalog pc = lp.loginApplication("divsel@practice.com", "Getinman15");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		cartPage cp = pc.goToCartPage();
		
		Boolean match = cp.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
}
		
}