package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.abstractComponent;

public class CheckoutPage extends abstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement dropdown;
	
	@FindBy(xpath="//a[contains(@class, 'action__submit')]")
	WebElement submit;
	
	@FindBy(xpath="//section[@class ='ta-results list-group ng-star-inserted']/button/span")
	List<WebElement> countries;
	
	By countriesBy = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	
	
	public void sendValueToDropdwn(String val)
	{
		dropdown.sendKeys(val);
	}
	
	public List<WebElement> getCountriesList()
	{
		waitForElementToAppear(countriesBy);
		return countries;
	}
	
	public void selectCountryByName(String countryName)
	{
		WebElement country =getCountriesList().stream().filter(c->c.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		country.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

	
	

}
