Feature: Search products through search box
    As a user
    I want to search the web site
    So that I can find products I want that the online have

	Scenario: search items the store have
		Given I open the home page of the online store
                When I try to search iPad
                Then I should see the product iPad

       
       Scenario: search items the store does not have
		Given I already open the home page of the online store
                When I try to search iMac which the store doesn't sell
                Then I should see an message noticing me no matched products
         
         Scenario: do search directly without input anything in search box
		Given I open online store's homepage
                When I try to do search directly without input anything
                Then I should see numerous products