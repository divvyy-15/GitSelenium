package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.abstractComponent;

public class ProductCatalog extends abstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='card-body']/h5") 
	List<WebElement> products;
	
	@FindBy(className="ng-animating")
	WebElement spinner;
	
	By productBy = By.xpath("//div[@class='card-body']/h5");
	By addToCart = By.xpath("//button[@class='btn w-10 rounded']");
	By toastMsg = By.id("toast-container");

	
	//Action method
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()  //If need the webelement then use filter
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);
	}
	
	
}
