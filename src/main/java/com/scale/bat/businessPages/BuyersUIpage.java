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
import com.scale.bat.framework.utility.TakeScreenShotAndAddToWordDoc;

import cucumber.api.Scenario;

public class BuyersUIpage extends Actions {

	private String signIn = "Sign in";
	private String logInButton = "Log in";
	private String mobileDevices = "Output Accessories";
	private String PLPScreenText = "products";
	private String basketText = "Basket";
	private String basketLink = "Basket";
	private String myAccountLink="account";
	private String clearBasketLink="Clear basket";
	private String viewListLink="View list";
	

	private String continueShoppingLinkOnBasketPage = "Continue shopping";
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
	
	private String searchButtonStr= "/html/body/header/div/div[2]/div/div/form/div/button";
	
	@FindBy(xpath = "//*[@id='main-content']/div[2]/div[2]/ul/li/div/div[1]/a/img")
	private WebElement selectProduct;
	
	private String selectProductStr="//*[@id='main-content']/div[2]/div[2]/ul/li/div/div[1]/a/img";
	
	@FindBy(xpath = "//*[@class='bat-header__menu-button bat-js-header-toggle']")
	private WebElement mobileMenue;
	
	@FindBy(xpath = "//*[@id='main-content']/div[2]/div[3]/a")
	private WebElement myListVisitButton;
	
	@FindBy(xpath = "//*[@class='govuk-button govuk-button--secondary bat-product__button bat-product__add-to-list__button']")
	private WebElement addToList;
	
	@FindBy(xpath = "//*[@class='govuk-button govuk-button--secondary bat-compare-products-header__add-to-list']")
	private WebElement addToListBtnComparePage;
	
	@FindBy(xpath = "//a[@href='/wishlist']")
	private WebElement myAccountMyListVisitButton;
	
	@FindBy(xpath = "//*[@class='bat-wished-product__delete']/button")
	private WebElement myListDeleteProductButton;
	
	@FindBy(xpath = "//*[@id='main-content']/div[1]/div/table/thead/tr/th[2]/div/div[2]/form[2]/button")
	private WebElement removeLink;
	
	@FindBy(xpath = "//*[@class='govuk-width-container ']/a")
	private WebElement backLink;
	
	@FindBy(xpath = "//*[@class='govuk-button govuk-button--secondary bat-product-preview__compare']")
	private WebElement compareButton;
	
	@FindBy(xpath = "//*[@class='govuk-notification-banner__heading']")
	private WebElement addToListMsgValidation;
	
	@FindBy(xpath = "//*[@class='govuk-notification-banner__heading']")
	private WebElement addedToComparePageMsg;
	
	@FindBy(xpath = "//*[@class='bat-basket__items']/ul/li[1]/div/form/div/div[5]/button[1]")
	private WebElement quantityUpdateButtonBasket;
	
	
	private String mobileMenueStr = "//*[@class='bat-header__menu-button bat-js-header-toggle']";
	private String totalNoOfProductInBasket="//li[@class='bat-basket__item']";
	private String checkBasketIsEmpty="//*[@class='govuk-heading-m']";
	private String getTotalProductsInBasket="//*[@class='bat-basket__items']/ul/li";
	private String checkMyListIsEmpty="//*[@class='govuk-body govuk-!-govuk-!-margin-bottom-6']";
	private String getTotalProductsInMyList="//*[@id='main-content']/div[1]/div/ul/li";
	
	
		

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
		//TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
		clickButton(logInButton);
		log.info("Logged in to buyers Ui");
	}
	
	public void searchProduct(String product) {
		waitForSeconds(1);
		enterText(search, product);
		waitForSeconds(2);
		//clickElement(searchButton);
		clickElementWithJavaScript(searchButtonStr);
		waitForSeconds(2);
	}
	
	
	public void clearBasket() {
		
		if (!isElementPresentByXpath(checkBasketIsEmpty)) {
			
			int totalProducts = getElementsSizeByXpath(getTotalProductsInBasket, driver);
			
			for(int i=totalProducts;i==totalProducts;i--) {
								
				clickElementWithJavaScript("//*[@class='bat-basket__items']/ul/li[" + i +"]/div/form/div/div[5]/button[2]");
			}
	}
		
		
	}
	
	public void clearMyList() {
		
		if (!isElementPresentByXpath(checkMyListIsEmpty)) {
			
			int totalProducts = getElementsSizeByXpath(getTotalProductsInMyList, driver);
			
			for(int i=totalProducts;i==totalProducts;i--) {
								
				clickElementWithJavaScript("//*[@id='main-content']/div[1]/div/ul/li[" + i +"]/div/form[2]/button");
			}
	}
		
		
	}
	
	public void clickOnMyListVisitButton() {
		clickElement(myListVisitButton);
	}

	public void navigateToPDPPage() {
		clickElementWithJavaScript(selectProductStr);
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
	
	
	public void removeProductsFromTheBasket() {
		clickElement(basketLink);
		int getTotalNoOfProductInBasket=getElementsSizeByXpath(totalNoOfProductInBasket,super.driver);
		for(int i=1; i<=getTotalNoOfProductInBasket;i++) {
			if (isElementPresentByXpath("//li[@class='bat-basket__item'][" + i + "]//div[@class='bat-basket-item__actions']/button[2]")) {
				WebElement abc = getWebElementByXpath("//li[@class='bat-basket__item'][" + i + "]//div[@class='bat-basket-item__actions']/button[2]");
				clickElement(abc);
			}
		}
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
	
	public String getMyAccountLink() {
		return myAccountLink;
	}
	
	public String getclearBasketLink() {
		return clearBasketLink;
	}
	
	public WebElement getaddToList() {
		return addToList;
	}
	
	public String getaddToListMsg() {
		return getText(addToListMsgValidation);
	}
	
	public String getviewListLink() {
		return viewListLink;
	}
	
	public WebElement getcompareButton() {
		return compareButton;
	}
	
	public String getproductAddedToComparePageMsg() {
		return getText(addedToComparePageMsg);
	}
	
	public WebElement getaddToListBtnComparePage() {
		return addToListBtnComparePage;
	}
	
	public WebElement getbackLink() {
		return backLink;
	}
	
	
	public WebElement getremoveLink() {
		return removeLink;
	}
	
	public WebElement getMyAccountMyListVisitButton() {
		return myAccountMyListVisitButton;
	}
	
	public WebElement getMyListDeleteProductButton() {
		return myListDeleteProductButton;
	}
	
	public WebElement quantityUpdateButtonBasket() {
		return quantityUpdateButtonBasket;
	}
	
	
	
	

}
