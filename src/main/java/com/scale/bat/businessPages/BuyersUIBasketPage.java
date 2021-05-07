package com.scale.bat.businessPages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.TakeScreenShotAndAddToWordDoc;
import com.scale.bat.framework.utility.API.APIBase;
import com.scale.bat.businessPages.WishListServiceStepDefs;

import cucumber.api.Scenario;
import io.restassured.response.Response;

public class BuyersUIBasketPage  extends Actions{
	
	private Logger log = Log.getLogger(BuyersUIPDPPage.class);
	private ConfigurationReader configReaderObj;
	APIBase apiBase=new APIBase();
	//WishListServiceStepDefs wishListServiceStepDefs=new WishListServiceStepDefs();

	public BuyersUIBasketPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	
	
	@FindBy(xpath ="//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/div/table/tbody/tr/td[5]")
	private WebElement priceOnBasket1;
	
	@FindBy(xpath = "")
	private WebElement manufaturerBasket;
	
	@FindBy(xpath ="//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/form/div/div[2]/a")
	private WebElement productNameBasket;
	
	@FindBy(xpath ="//*[@class='bat-basket-item__title__sku']")
	private WebElement skuBasket;
	
	@FindBy(xpath ="//*[@class='bat-basket-item__title__mpn']")
	private WebElement mpnBasket;
	
	@FindBy(xpath ="//*[@class='bat-basket__footer__right']/ul/li[1]/span[2]")
	private WebElement productsTotal;
	
	@FindBy(xpath ="//*[@class='bat-basket__footer__right']/ul/li[2]/span[2]")
	private WebElement deliveryTotal;
				
	@FindBy(xpath ="//*[@class='govuk-notification-banner__heading']")
	private WebElement productAddToBasketMsg;
	
	@FindBy(xpath ="//input[@name='quantity']")
	private WebElement qtyBasket;
	
	@FindBy(xpath ="//input[@name='quantity']")
	private WebElement productQuantity;
	
	@FindBy(xpath ="//*[@class='govuk-notification-banner__content']/p")
	private WebElement allProductsWereAddedToYourBasket;
	
	@FindBy(xpath ="//*[@class='govuk-warning-text__text']")
	private WebElement YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg;
	
	
	//My Basket Page Locators
	//PRODUCT 1
	
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/ul/li/div/form/div/div[2]/a")
	private WebElement productName;
	
	@FindBy(xpath ="//*[@class='bat-basket-item__title']/a/following-sibling::span[1]")
	private WebElement mpn;
	
	@FindBy(xpath ="//*[@class='bat-basket-item__unit-price']")
	private WebElement productUnitPrice;
	
	@FindBy(xpath ="//*[@class='bat-basket-item__total']")
	private WebElement productTotalPrice;
	
	@FindBy(xpath ="//div[@class='bat-basket__footer__right']/ul/li[2]/span[2]")
	private WebElement getDeviveryCharge;
	
	//PRODUCT 2
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/a")
	private WebElement productName2;
	
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/span[1]")
	private WebElement mpn2;
	
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/following-sibling::div[1]")
	private WebElement productPrice2;
	
	@FindBy(xpath ="//button[@class='govuk-button govuk-button--secondary govuk-!-width-one-quarter']")
	private WebElement basketUpdateButton;
	
	
	
	//PRODUCT 3 
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[3]/div/ul/li/div/form/div/div[2]/a")
	private WebElement productName3;
		
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[3]/div/ul/li/div/form/div/div[2]/span[1]")
	private WebElement mpn3;
		
	@FindBy(xpath ="//ul[@class='govuk-list bat-basket-items-by-supplier']/li[3]/div/ul/li/div/form/div/div[2]/following-sibling::div[1]")
	private WebElement productPrice3;

	//Basket page
	@FindBy(xpath ="//*[@class='govuk-notification-banner__heading']")
	private WebElement basketPageMsgAfterClearBasket;
	
	String checkProductPresentOrNot ="//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/form/div/div[2]/a";
	
	@FindBy(xpath ="//*[@class='govuk-heading-m']")
	private WebElement basketPageYourbasketIsEmptyMsg;
	
	String checkClearBasketBtn ="//*[@name='_csrf']/following-sibling::button";

	String deliveryMethodDropDown ="//select[@id='shipping_method_id']";
	
	@FindBy(xpath ="//select[@id='shipping_method_id']")
	private WebElement webElementDeliveryMethodDropDown;
	
	@FindBy(xpath ="//div[@id='shipping_method_id-hint']")
	private WebElement basketWarningMsgOfNextDayDelivery;
	
	private String basketWarningMsgOfNextDayDeliveryString="//div[@id='shipping_method_id-hint']";
	
	@FindBy(xpath ="//a[@href='/basket']")
	private WebElement basketLink;
	
	
	@FindBy(xpath ="//h2[@class='govuk-notification-banner__title']")
	private WebElement notificationBlueBanner;
	
	@FindBy(xpath ="//span[@class='govuk-details__summary-text']")
	private WebElement notificationBlueBannerSummaryText;
	
	@FindBy(xpath ="//div[@class='govuk-details__text']/ul/li")
	private WebElement notificationBlueBannerProductDetail;
	
	@FindBy(xpath ="//input[@name='quantity']")
	private WebElement productQuantityOnBasket;
	
	
	
	
	//Declare Variables
	String productsTotalSplit[];
	String productsTotalSplitActual;
	String deliveryTotalSplit[];
	String deliveryTotalSplitActual;
	
//	public void verifyProductDetailsOnBasketPage(Map<String, Object> pDetails) {
	public void verifyProductDetailsOnBasketPage(String path) {
		waitForSeconds(2);
		
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(path));
		//Retrieving the array
        JSONArray jsonArray = (JSONArray) jObj.getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++) {
          		
        	JSONObject obj=jsonArray.getJSONObject(i);
        	JSONObject attributes=obj.getJSONObject("attributes");
        	int j=i+1;
        	//Product Price UI
        	assertTrue(getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li["+j+"]/div/div/table/tbody/tr/td[5]").replaceAll("[^a-zA-Z0-9]","").contains(attributes.getString("display_price").replaceAll("[^a-zA-Z0-9]","")));
        	
        	//Product Name Validation
        	assertTrue(getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li["+j+"]/div/form/div/div[2]/a").contains(attributes.getString("name")));
        	        	        	
        	//SKU Validation
        	assertTrue(getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li["+j+"]/div/div/table/tbody/tr/td[2]").contains(attributes.getString("SKU")));
        	
        	//MPN
        	assertTrue(getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li["+j+"]/div/form/div/div[2]/span").contains(attributes.getString("mpn_number")));
        		
    		log.info("Validation completed on Basket Page UI");
        	
        	
		
	}

	}
	
	
	public void verifyMultipleProductsDetailsOnBasketPage() {
		waitForSeconds(2);
				
			int productNamelength = getText(productName).length();
			String productName1 = getText(productName).substring(17,productNamelength);
			//System.out.println("product1NameUI = " + productName + " jsonproductName = " + apiBase.getvaluefromresponse("data[1].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse));
			int mpnlength=getText(mpn).length();
			String mpn1 = getText(mpn).substring(5,mpnlength);
			
			//Product1 Price UI
        	//assertTrue(getText(productPrice).equals(apiBase.getvaluefromresponse("data[1].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//Product1 Name Validation
        	assertTrue(productName1.equals(apiBase.getvaluefromresponse("data[1].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//Product1 MPN
        	assertTrue(mpn1.equals(apiBase.getvaluefromresponse("data[1].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
                	
        	// Second Product Validation
        	int product2Namelength = getText(productName2).length();
			String product2Name = getText(productName2).substring(17,product2Namelength);
			    	
			int product2mpnlength=getText(mpn2).length();
			String product2mpn = getText(mpn2).substring(5,product2mpnlength);
			
			String[] productPrice2Split = getText(productPrice2).split(" ");	
			String productPrice2UIActual = productPrice2Split[0];
        	//Product2 Price UI
        	assertTrue(productPrice2UIActual.equals(apiBase.getvaluefromresponse("data[2].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//Product2 Name Validation
        	assertTrue(product2Name.equals(apiBase.getvaluefromresponse("data[2].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//Product2 MPN
        	assertTrue(product2mpn.equals(apiBase.getvaluefromresponse("data[2].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	log.info("Validation completed on Basket Page UI");
            
	}
	
	
	public void verifyMultipleProductsDetailsOnBasketPageAfterAddTheseItemsButton() {
		waitForSeconds(2);
		
		int productNamelength = getText(productName).length();
		String productName1 = getText(productName).substring(17,productNamelength);
		//System.out.println("productNameUI = " + productName + " jsonproductName = " + apiBase.getvaluefromresponse("data[0].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse));
		
		int mpnlength=getText(mpn).length();
		String mpn1 = getText(mpn).substring(5,mpnlength);
		
		String[] productPriceSplit = getText(productUnitPrice).split(" ");	
		String productPriceUIActual = productPriceSplit[0];
    	
    	//Product1 Price UI
    	assertTrue(productPriceUIActual.equals(apiBase.getvaluefromresponse("data[0].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
    	
    	//Product1 Name Validation
    	assertTrue(productName1.equals(apiBase.getvaluefromresponse("data[0].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
    	
    	//MPN1
    	assertTrue(mpn1.equals(apiBase.getvaluefromresponse("data[0].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
    
		
		//PRODUCT2 Validation
		int product1Namelength = getText(productName2).length();
		String product1Name = getText(productName2).substring(17,product1Namelength);
			
		int product1mpnlength=getText(mpn2).length();
		String product1mpn = getText(mpn2).substring(5,product1mpnlength);
			
		String[] productPrice2Split = getText(productPrice2).split(" ");	
		String productPriceUIActual2 = productPrice2Split[0];
			
        //Product2 Price UI
      	assertTrue(productPriceUIActual2.equals(apiBase.getvaluefromresponse("data[1].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        //Product2 Name Validation
        assertTrue(product1Name.equals(apiBase.getvaluefromresponse("data[1].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        //Product2 MPN
        assertTrue(product1mpn.equals(apiBase.getvaluefromresponse("data[1].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	
        //PRODUCT3  Validation
       	int product2Namelength = getText(productName3).length();
		String product2Name = getText(productName3).substring(17,product2Namelength);
				
		int product2mpnlength=getText(mpn3).length();
		String product2mpn = getText(mpn3).substring(5,product2mpnlength);
		
		String[] productPrice3Split = getText(productPrice3).split(" ");	
		String productPriceUIActual3 = productPrice3Split[0];
		 	
        //Product3 Price UI
       	assertTrue(productPriceUIActual3.equals(apiBase.getvaluefromresponse("data[2].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
       	//Product3 Name Validation
       	assertTrue(product2Name.equals(apiBase.getvaluefromresponse("data[2].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        //Product3 MPN
        assertTrue(product2mpn.equals(apiBase.getvaluefromresponse("data[2].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
       	log.info("Validation completed on Basket Page UI");
            
	}
	
	public void verifyProductDetailsOnBasketPage() {
		waitForSeconds(2);
		
			int productNamelength = getText(productName).length();
			String productNames = getText(productName).substring(17,productNamelength);
			//System.out.println("productNameUI = " + productName + " jsonproductName = " + apiBase.getvaluefromresponse("data[0].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse));
			String[] productPrice = getText(productUnitPrice).split("each");
			String productPriceSplit = productPrice[0].replaceAll("\\s", "");
			int mpnlength=getText(mpn).length();
			String mpns = getText(mpn).substring(5,mpnlength);
			
			System.out.println("JSON Body Validation: " + WishListServiceStepDefs.jsonAllProductsResponse.getBody().asString());
		 	//Product Price UI
        	assertTrue(productPriceSplit.equals(apiBase.getvaluefromresponse("data[0].attributes.display_price", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//Product Name Validation
        	assertTrue(productNames.equals(apiBase.getvaluefromresponse("data[0].attributes.name", WishListServiceStepDefs.jsonAllProductsResponse)));
        	
        	//MPN
        	assertTrue(mpns.equals(apiBase.getvaluefromresponse("data[0].attributes.mpn_number", WishListServiceStepDefs.jsonAllProductsResponse)));
        
        	log.info("Validation completed on Basket Page UI");
        	
        	//TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
            
	}
	
	
	public void verifyProductMessageOnBasketPage() throws IOException {
		waitForSeconds(2);
			//Product Price UI
        	assertTrue(getText(basketPageMsgAfterClearBasket).equals("All products were removed from the basket."));
        	 boolean checkProductNotPresent = isElementPresentByXpath(checkProductPresentOrNot);
        	 
        	 if(checkProductNotPresent==false) {
        		 assertTrue("All Products are cleared from basket", true);
        	 }else {
        		 
        		 assertTrue("All Products are Not cleared from basket", false);
        	 }
        	 
        	 //TakeScreenShotAndAddToWordDoc.captureScreenShotNew();   
        	 //TakeScreenShotAndAddToWordDoc.writeScreenShot();
        	 //TakeScreenShotAndAddToWordDoc.writeDoc();
        	 waitForSeconds(2);
	}
	
	
	
	public void verifyBlueBanneredBox(Map<String, Object> pDetails) throws IOException {
		waitForSeconds(2);
		assertTrue(getText(notificationBlueBanner).equals("Important"));
        assertTrue(getText(notificationBlueBannerSummaryText).equals(configReaderObj.get("notificationBlueBannerSummaryTextMsg")));
        clickElementXpath(notificationBlueBannerSummaryText);
        assertTrue(getText(notificationBlueBannerProductDetail).equals(pDetails.get("ProductName").toString() + " from sftpsupplier2021 had it's stock reduced from 5 to the maximum stock available from suppliers. The suppliers providing the product were updated."));
    }
	
	public void verifyProductReducedQuantityOnBasketPage() throws IOException {
		waitForSeconds(2);
		assertTrue(getAttributeValue(productQuantityOnBasket).equals("4"));
        
	}
	
	
	public void verifyBasketGenericMessageOnBasketPage(String genericMsg) throws IOException {
		waitForSeconds(2);
		assertTrue(getText(basketPageMsgAfterClearBasket).equals(genericMsg));
        waitForSeconds(2);
	}
	
	
	
	public void verifyBasketLinkCount(String basketCount) throws IOException {
			waitForSeconds(2);
			//Verify Basket Link qty
        	assertTrue(getText(basketLink).equals("Basket: "+basketCount));
        	log.info("Validated basket link product quantity "+getText(basketLink)+ " successfully");
	}
	
	public void validateWarningMessageIsHiddenOnBasketPage() {
		
		boolean mgsNotVisible = isElementPresentByXpath(basketWarningMsgOfNextDayDeliveryString);
		if(mgsNotVisible==false) {
			assertFalse("Warning message is hidden on basket when 'Next Day Delivery' option is available",mgsNotVisible);
			log.info("Warning message is hidden on basket when 'Next Day Delivery' option is available");
		}else {
			assertFalse(getText(basketWarningMsgOfNextDayDelivery)+" is visible", mgsNotVisible);
		}
	}
	
	public void validateNextDayDeliveryIsGreyedOutOnBasketPage() {
		
		boolean nexBusinessDayIsGreyedOut  = isElementPresentByXpath(basketWarningMsgOfNextDayDeliveryString);
		assertTrue("'Next Business Day (Orders after Midday)' option is greyed out ",nexBusinessDayIsGreyedOut);
		log.info("'Next Business Day (Orders after Midday)' option is greyed out in Delivery method dropdown");
		
	}
	
	
	
	public void validateWarningMessageIsVisiblenOnBasketPage() {
		
		assertTrue(getText(basketWarningMsgOfNextDayDelivery).equals(configReaderObj.get("warningMessageNextDay")));
		log.info("Warning message: " +getText(basketWarningMsgOfNextDayDelivery)+ " is validated");
	}
	
	public void verifyProductMessageAndClearBasketButttonOnBasketPage(String msg) {
		waitForSeconds(2);
			
        	assertTrue(getText(basketPageYourbasketIsEmptyMsg).equals(msg));
        	 boolean checkClearBasketBtnNotPresent = isElementPresentByXpath(checkClearBasketBtn);
        	 
        	 if(checkClearBasketBtnNotPresent==false) {
        		 assertTrue("Clear Basket button is not visible on basket page", true);
        	 }else {
        		 
        		 assertTrue("Clear Basket button is visible on basket page", false);
        	 }       
	}
	
	
	public void verifyTheDefaultDeliveryOption(String defaultDeliveryOption) {
		assertEquals(getDropDownDefaultSelectedOption(deliveryMethodDropDown), defaultDeliveryOption);
		log.info("Validated default value selected in Delivery Method drop down is " + defaultDeliveryOption + " on Basket Page UI");
	}
	
	public void verifyAllTheOptionsInDeliveryOptionDropDown() {
		
		List<WebElement> allValues = getAllValuesOfDropDown(deliveryMethodDropDown);
		//Get the length
	    System.out.println(allValues.size());
	    //Creating arraylist    
	    ArrayList<String> listDeliveryOptions=new ArrayList<String>();
	    //Adding object in arraylist 
	    listDeliveryOptions.add("Standard UK Mainland (3-5 days)");
	    listDeliveryOptions.add("Standard UK Non Mainland (3-5 days)");
	    listDeliveryOptions.add("Next Business Day (Orders after Midday)");    

	    // Loop to print one by one
	    for (int j = 0; j < allValues.size(); j++) {
	    	System.out.println(allValues.get(j).getText().equals(listDeliveryOptions.get(j)));
	    	assertEquals(allValues.get(j).getText(), listDeliveryOptions.get(j));
	
	    }
	
	    log.info("Validated all Delivery Method on Basket Page UI");
	}
	
	public void verifyMessageProductAddToBasketMsg() {
		
		assertEquals(getText(productAddToBasketMsg), "Product added to your basket");
	}
	
	public void verifyProductUpdatedQuantityOnBasket() {
		
		assertEquals(getAttributeValue(productQuantity), "2");
	}
	
	public void VerifyMessageItsOutOfStock() {
		String contents = getText(YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg);
		String[] lines = contents.split("\\r?\\n");
		System.out.println("OUT Of Stock SPLIT 0 " + lines[0]);
		System.out.println("OUT Of Stock SPLIT 1 " + lines[1]);
		System.out.println("OUT Of Stock MGS " + getText(YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg));
		
		assertEquals(lines[1], "You cannot add the selected product to the basket. It's out of stock.");
	}
	
	public void verifyProductPriceDetails(Map<String, Object> pDetails) {
		waitForSeconds(2);
		
		System.out.println("JSON PRODUCT PRICE : "+pDetails.get("Price").toString());
		//System.out.println("UI PRODUCT PRICE : "+getText(productPrice).replaceAll("[^a-zA-Z0-9]",""));
		System.out.println("UI PRODUCT PRICE : "+getText(productUnitPrice).replaceAll("�", ""));
		assertTrue(getText(productUnitPrice).replaceAll("�", "").contains(pDetails.get("Price").toString()));
		/*assertEquals(getText(mpnNumber), pDetails.get("MPN").toString());
		assertEquals(getText(manufaturer), pDetails.get("ManufacturerName").toString());*/
		log.info("Validation completed for Price on buyer UI");
	}
	
	public void updateTheProductQuantity(String quantity) {
		waitForSeconds(2);
		enterText(productQuantity, quantity);
		log.info("User enters the product quantity");
	}
	
	public void selectTheDeliveryMethod(String deliveryMethod) {
		waitForSeconds(1);
		selectItemFromDropDown(webElementDeliveryMethodDropDown, deliveryMethod);
		clickElement(basketUpdateButton);
		waitForSeconds(1);
	}
	
	
	public void verifyProductDeliveryCostDetails(Map<String, Object> pDetails) {
		waitForSeconds(1);
		
		String[] productTotalPriceUISplit;
		String productTotalPriceUIActual;
		String[] productUnitPriceUISplit;
		String productUnitPriceUIActual;
		String[] getDeviveryChargeSplit;
		String deliveryTotalActualValue;
		
		// 1) Check the Delivery charge for Delivery method "Standard UK Non Mainland (3-5 days)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Standard UK Non Mainland (3-5 days)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");
		
		//System.out.println("JSON PRODUCT Standard UK Non Mainland (3-5 days) : "+ pDetails.get("StandardChargeProductUKNonMainland").toString());
		//System.out.println("UI PRODUCT Standard UK Non Mainland (3-5 days) : "+ deliveryTotalActualValue);
		
		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue.equals(pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("\\s", "")));
		//Remove the unnecessary text "MPN: " from the string
		String[] splitMPN=getText(mpnBasket).split(" ");
		String actMPN=splitMPN[1];
		//Remove the unnecessary text "SKU: " from the string
		String[] splitSKU=getText(skuBasket).split(" ");
		String actSKU=splitSKU[1];
		
		assertTrue(actMPN.equals(pDetails.get("MPN").toString()));
		assertTrue(actSKU.equals(pDetails.get("SKU").toString()));
		assertTrue(getAttributeValue(qtyBasket).equals("2"));
		
		//Double the price as per quantity 2 for validation
		String productUnitPriceStr=pDetails.get("Price").toString();
		double productUnitPriceDouble = Double.parseDouble(productUnitPriceStr);
		double productTotalPriceDouble = productUnitPriceDouble*2;
		String productTotalPriceString=String.valueOf(productTotalPriceDouble); 
		//System.out.println("JSON productTotalPriceString : "+ productTotalPriceString);
		
		//Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		//Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit=getText(deliveryTotal).split("e");
		deliveryTotalSplitActual=deliveryTotalSplit[0];
		
		//Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");
		
		//Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");
		
		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").contains(pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]","")));
		
		
		
		// 2) Check the Delivery charge for Delivery method "Next Business Day (Oders after Midday)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Next Business Day (Orders after Midday)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");
		
		//System.out.println("JSON PRODUCT Next Business Day (Orders after Midday) : "+ pDetails.get("nextDayChargeProduct").toString());
		//System.out.println("UI PRODUCT Next Business Day (Orders after Midday) : "+ deliveryTotalActualValue);
		
		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue.equals(pDetails.get("nextDayChargeProduct").toString()));
		
		//Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		//Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit =getText(deliveryTotal).split("e");
		deliveryTotalSplitActual=deliveryTotalSplit[0];
		
		//Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");
				
		//Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");
		
		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]","")));
		
		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").contains(pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]","")));
		
		
		
		// 3) Check the Delivery charge for Delivery method "Standard UK Mainland (3-5 days)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Standard UK Mainland (3-5 days)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");
		
		//System.out.println("JSON PRODUCT StandardChargeProductUKMainland : "+ pDetails.get("StandardChargeProductUKMainland").toString());
		//System.out.println("UI PRODUCT StandardChargeProductUKMainland : "+getText(getDeviveryCharge).replace("�", ""));
		
		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue.equals(pDetails.get("StandardChargeProductUKMainland").toString()));
		
		//Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		//Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit =getText(deliveryTotal).split("e");
		deliveryTotalSplitActual=deliveryTotalSplit[0];
		
		//Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");
						
		//Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");
				
		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]","").equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]","")));
				
		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]","")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]","").contains(pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]","")));
	
		log.info("Validation completed for All Delivery Method Delivery charges on buyer UI");
	}
	
	
	public void verifyProductDetailsOmMyListPage(Map<String, Object> pDetails) {
		waitForSeconds(2);
		
		System.out.println("JSON PRODUCT PRICE : "+pDetails.get("Price").toString());
		System.out.println("UI PRODUCT PRICE : "+getText(productUnitPrice).replaceAll("[^a-zA-Z0-9]",""));
		assertTrue(getText(productUnitPrice).replaceAll("[^a-zA-Z0-9]","").contains(pDetails.get("Price").toString().replaceAll("[^a-zA-Z0-9]","")));
		/*assertEquals(getText(mpnNumber), pDetails.get("MPN").toString());
		assertEquals(getText(manufaturer), pDetails.get("ManufacturerName").toString());*/
		log.info("Validation completed for Price on buyer UI");
	}
	
	
	public void verifyMessageAllProductsWereAddedToYourBasket() {
		
		assertEquals(getText(allProductsWereAddedToYourBasket), "All products were added to your basket");
	}
	
}
