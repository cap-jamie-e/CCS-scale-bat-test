#User Story-63
Feature: As a Supplier,
  I MUST be able to set the future date that I want each product record to become published on, 
  So that if I have a new product line only available from a certain date I can add that certain date

  @test
  Scenario: SCA-535_Verify Unpublished on field is empty once the product is published
    Given User logged in as "supplier" in admin panel
    And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And User clicks on edit button to view product details
    When User clicks on "Publish" button on product details page
    Then "Discontinue On" field is empty once the product is published

  #@test Need to check the test case
  Scenario: SCA-535_Verify Unpublished on field is empty once the product is published
    Given User logged in as "supplier" in admin panel
    And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And User clicks on edit button to view product details
    #Need to provide test data
    When User "updates" the "Published from date" of a product
    And User clicks on "Update" button on product details page
    Then Product should be in "published" state

  @test
  Scenario: SCA-534_Verify Unpublished on field is updated with today's date once the product is unpublished
    Given User logged in as "supplier" in admin panel
    And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And User clicks on edit button to view product details
    When User clicks on "Unpublish" button on product details page
    Then "Discontinue On" field is updated with today's date

  @test
  Scenario: SCA-531_ Verify that Published from date field is empty when unpublish button is clicked
    Given User logged in as "supplier" in admin panel
    And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User clicks on edit button to view product details
    Then "Published from date" field is empty once the product is published
