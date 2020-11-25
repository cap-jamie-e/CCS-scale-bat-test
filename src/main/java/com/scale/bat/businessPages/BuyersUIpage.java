package com.scale.bat.businessPages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.Log;
import cucumber.api.Scenario;

public class BuyersUIpage extends Actions {

	private String signIn = "Sign in";
	private String logInButton = "Log in";
	private String mobileDevices = "Mobile Devices";
	private String PLPScreenText = "products";
	private String basketText = "Basket";
	private String basketLink = "Basket";

	private String continueShoppingLinkOnBasketPage = "Continue Shopping";
	private String addToBasket = "  Add ";

	@FindBy(xpath = "//*[@class='bat-basket-item-added__buttons']/a")
	private WebElement continueShopping;

	@FindBy(xpath = "//*[@id='dropdown-navigation']/li[1]/a")
	private WebElement computing;

	@FindBy(xpath = "//*[@id='username']")
	private WebElement userName;

	@FindBy(xpath = "//*[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//*[@class='bat-basket-item-added__buttons']/a[2]")
	private WebElement proceedToBasket;

	@FindBy(xpath = "//*[@id='main-content']/div[2]/div/div/div[1]/ul/li/div/form/div/div[5]/button[2]")
	private WebElement deleteButton;

	@FindBy(xpath = "//*[@id='search']")
	private WebElement search;
	
	@FindBy(xpath = "/html/body/header/div/div[2]/div/div/form/div/button")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='main-content']/div[2]/div[2]/ul/li/div/a/img")
	private WebElement selectProduct;
	
	
	@FindBy(xpath = "//*[@class='bat-header__menu-button bat-js-header-toggle']")
	private WebElement mobileMenue;
	
	private String mobileMenueStr = "//*[@class='bat-header__menu-button bat-js-header-toggle']";
	
	
	//*[@id="main-content"]/div/div[3]/div/h2

	private Logger log = Log.getLogger(BuyersUIpage.class);

	public BuyersUIpage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
	}
	
	public void checkMenuButtonOnMobile() {
		
		/*if(isElementPresentByXpath(mobileMenueStr)) {
			clickElementXpath(mobileMenue);
			log.info("User clicked on Menue button");
		}*/
		
		
	}
	

	public void loginByuerUi(String UserName, String Password) {
		clickByLinkText(signIn);
		enterText(userName, UserName);
		enterText(password, Password);
		waitForSeconds(1);
		clickButton(logInButton);
		log.info("Logged in to buyers Ui");
	}
	
	public void searchProduct(String product) {
		waitForSeconds(1);
		enterText(search, product);
		waitForSeconds(1);
		clickElement(searchButton);
	}

	public void navigateToPDPPage() {
		clickElement(selectProduct);
	}
	
	public void navigateToPLPPage() {
		moveToelement(computing);
		clickByLinkText(mobileDevices);
	}
	
	public void addElementToBasket() {
		clickElement(addToBasket);
		isElementPresent("Item added to your basket");
	}

	public void addProductToBasket(int numberOfProducts) {
		navigateToPLPPage();
		int i = 1;
		do {
			if (isElementPresentByXpath("//*[@class='bat-basket-item-added__buttons']/a")) {
				clickElement(continueShopping);
			}
			String element = "//*[@id='main-content']/div[2]/div[2]/ul/li[" + i + "]/div/div/form";
			clickElement(super.driver.findElement(By.xpath(element)));
			i++;

		} while (i <= numberOfProducts);
	}

	public void compareProducts(int numberOfProducts) {
		navigateToPLPPage();
		List<WebElement> listOfProducts = super.driver.findElements(
				By.xpath("//*[@class='govuk-grid-column-two-thirds-from-desktop bat-products']/ul/li/div/div/a"));
		// Need to write the code. Right now 404 error message is shown on screen
		for (int i = 0; i < numberOfProducts; i++) {
			WebElement element = listOfProducts.get(i);
			try {
				System.out.println(element);
			} catch (StaleElementReferenceException st) {
				st.getMessage();
			}
		}
	}

	public void isBuyerNavigatedToGivenPage(String pageName) {
		switch (pageName) {
		case "PLP":
			assertTrue(isElementPresent(PLPScreenText));
			log.info("Navigated to " + pageName);
			break;

		case "Basket":
			assertTrue(isElementPresent(basketText));
			log.info("Navigated to " + pageName);
			break;

		case "Confirmation screen":
			waitForSeconds(2);
			assertTrue((isElementPresentByXpath("//*[@class='bat-basket-item-added__buttons']/a")));
			log.info("Navigated to " + pageName);
			break;
		case "PDP":
			waitForSeconds(2);
			assertTrue(isElementPresent("Marketing description"));
			log.info("Navigated to " + pageName);
			break;

		default:
			break;
		}
	}

	public WebElement getContinueShoppingElement() {
		return continueShopping;
	}

	public WebElement deleteButtonOnBasketElement() {
		return continueShopping;
	}

	public WebElement getProceedToBasketElement() {
		return proceedToBasket;
	}

	public String getContinueShoppingLinkOnBasketPage() {
		return continueShoppingLinkOnBasketPage;
	}

	public String getAddToBasket() {
		return addToBasket;
	}

	public String getBasketLink() {
		return basketLink;
	}

}
