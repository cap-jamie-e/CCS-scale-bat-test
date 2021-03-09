package com.scale.bat.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.scale.bat.businessPages.BuyersUIpage;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuyerUiStepDefs {
	private Logger log = Log.getLogger(BuyersUIpage.class);

	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public BuyerUiStepDefs(TestContext testContextObj, ScenarioContext scenarioContext) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = scenarioContext;
	}

	@When("User add {int} product in basket")
	public void user_add_product_in_basket(int numberOfProducts) {
		objectManager.getBuyersUIpage().addProductToBasket(numberOfProducts);
	}
	
	@And("User removes all the products from the basket")
	public void user_removes_all_the_products_from_the_basket() {
	    
		objectManager.getBuyersUIpage().removeProductsFromTheBasket();
	}

	@When("User add {int} products to compare")
	public void user_add_product_to_compare(int numberOfProducts) {
		objectManager.getBuyersUIpage().compareProducts(numberOfProducts);
	}

	@Then("{string} is shown to the buyer")
	public void is_shown_to_the_buyer(String pageName) {
		objectManager.getBuyersUIpage().isBuyerNavigatedToGivenPage(pageName);
	}

	@And("User clicks on {string} in buyers UI")
	public void user_clicks_on_button_on_buyers_UI(String button) {
		switch (button) {
		case "Continue Shopping":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getContinueShoppingElement());
			break;
		case "Proceed to basket":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getProceedToBasketElement());
			break;
		case "browser back button":
			objectManager.getBuyersUIpage().navigateBackfromBrowser();
			break;
		case "continue shopping link":
			objectManager.getBuyersUIpage()
					.clickByLinkText(objectManager.getBuyersUIpage().getContinueShoppingLinkOnBasketPage());
			break;
		case "Delete button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().deleteButtonOnBasketElement());
			break;
		case "PLP image":
			objectManager.getBuyersUIpage().navigateToPDPPage();
			break;
		case "Basket Link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getBasketLink());
			break;
		case "Add to basket":
			objectManager.getBuyersUIpage().addElementToBasket();
		case "My Account link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getMyAccountLink());
			break;
		case "Clear basket":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getclearBasketLink());
			break;
		
		

		}
	}

	@And("User navigates to PLP of buyers UI")
	public void user_navigates_to_PLP_of_buyers_UI() {
		objectManager.getBuyersUIpage().navigateToPLPPage();
	}

	@Then("{string} button should not be visible")
	public void button_should_not_be_visible(String buttonOrLinkText) {
		switch (buttonOrLinkText) {
		case "Add to Basket":
			assertFalse(
					objectManager.getBuyersUIpage().isElementPresent(objectManager.getBuyersUIpage().getAddToBasket()));
			break;
		case "Basket":
			assertFalse(
					objectManager.getBuyersUIpage().isElementPresent(objectManager.getBuyersUIpage().getBasketLink()));
			break;
		}
	}

	@When("User search a product with SKU number")
	public void user_search_a_product_with_SKU_number() {
		log.info(scenarioContext.productDetails);
		objectManager.getBuyersUIpage().searchProduct(scenarioContext.productDetails.get("SKU").toString());
	}
	
	@And("User clears the basket")
	public void user_clears_the_basket() {
	    
		objectManager.getBuyersUIpage().clearBasket();
			
	}
	
	@And("User clicks on My List Visit button")
	public void user_clicks_on_My_List_Visit_button() {
	    
		objectManager.getBuyersUIpage().clickOnMyListVisitButton();
	}
	

	@And("User clears the Mylist")
	public void user_clears_the_Mylist() {
	    
		objectManager.getBuyersUIpage().clearMyList();
	}
	
	@And("User validates the products detail in My List page")
	public void user_validates_the_products_detail_in_My_List_page() {
	    
		//objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.productDetails,scenarioContext.SCA161OneProductFilePathNew);
		objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.SCA161OneProductFilePathNew);
	}
	
	
	@And("User Validates the more than one products details in My List page")
	public void user_Validates_the_more_than_one_products_details_in_My_List_page() {
		
		objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.SCA161TwoProductsFilePathNew);
	    
	}

	@And("User Clicks on AddToBasket button of the selected product on My List page")
	public void user_Clicks_on_AddToBasket_button_of_the_selected_product_on_My_List_page() {
	    
		objectManager.getBuyersUIMyListpage().clickOnAddtoBasketButton();
		
	}
	
	@And("User Clicks on Clear my basket and add these items button")
	public void user_Clicks_on_Clear_my_basket_and_add_these_items_button() {
	    
		objectManager.getBuyersUIMyListpage().clickOnClearMyBasketAndAddTheseItemsbutton();
		
	}
	
	@And("User Clicks on Add these items to current basket button")
	public void user_Clicks_on_Add_these_items_to_current_basket_button() {
	    
		objectManager.getBuyersUIMyListpage().clickOnAddTheseItemsToCurrentBasketbutton();
	}


	@And("User Validated the product details on basket page")
	public void user_Validated_the_product_details_on_basket_page() {
		
		
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage(scenarioContext.SCA161OneProductFilePathNew);
	    
	}
	
	
	
	@And("User Validated a product details on basket page")
	public void user_Validated_a_product_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage();
	}
	
	@Given("User validates the message after Clear basket button clicked")
	public void user_validates_the_message_after_Clear_basket_button_clicked() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductMessageOnBasketPage();
	}
	

	@Given("User validates the Clear basket button and {string} message on basket page")
	public void user_validates_the_Clear_basket_button_and_message_on_basket_page(String Msg) {
    
		objectManager.getBuyersUIBasketpage().verifyProductMessageAndClearBasketButttonOnBasketPage(Msg);
	}

	
	@And("User Validated multiple products details on basket page")
	public void user_Validated_multiple_products_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyMultipleProductsDetailsOnBasketPage();
	}
	
	@And("User Validates products details after clicking on Add these items to current basket button")
	public void user_Validates_products_details_after_clicking_on_Add_these_items_to_current_basket_button() {
	    
		objectManager.getBuyersUIBasketpage().verifyMultipleProductsDetailsOnBasketPageAfterAddTheseItemsButton();
	}
	
	
	@And("User Validated the more than one products details on basket page")
	public void user_Validated_the_more_than_one_products_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage();
	}
	
	
	@And("User validates the product message Product added to your basket")
	public void user_validates_the_product_message_Product_added_to_your_basket() {
	 
		objectManager.getBuyersUIBasketpage().verifyMessageProductAddToBasketMsg();
	}
	
	@And("User validates the product message You cannot add the selected product to the basket Its out of stock")
	public void user_validates_the_product_message_You_cannot_add_the_selected_product_to_the_basket_Its_out_of_stock() {
	 
		objectManager.getBuyersUIBasketpage().VerifyMessageItsOutOfStock();
	}
	
	@And("User validates the product message All products were added to your basket")
	public void user_validates_the_product_message_All_products_were_added_to_your_basket() {
	    
		objectManager.getBuyersUIBasketpage().verifyMessageAllProductsWereAddedToYourBasket();
	}



	@Then("verify the product details in buyers UI")
	public void verify_the_product_details_in_buyers_UI() {
		objectManager.getBuyersUIpage().navigateToPDPPage();
		objectManager.getBuyersUIPDPPage().verifyProductDetails(scenarioContext.productDetails);
	}

	@Then("verify the product details should not be available on buyers UI")
	public void verify_the_product_details_unavialable_on_buyers_UI() {
		//objectManager.getBuyersUIpage().isElementPresent("0 results");
		objectManager.getBuyersUIpage().isElementPresent("No results found.");
		
	}

	@Then("User Validate last updated product by supplier {string} is showing in PDP price table on buyers UI")
	public void user_Validate_last_updated_product_by_supplier_is_showing_in_PDP_price_table_on_buyers_UI(
			String supplierName) {

		objectManager.getBuyersUIPDPPage().verifyLastUpdatedProductDetails(scenarioContext.productDetails,
				supplierName);
	}
}
