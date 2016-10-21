# DeliverableThree
Runtime Environment
----------------------
I use Mac to do the projects. So I downloaded the gecko driver for mac, put it in my project and changed the corresponding code in java class.

Problems I met
--------------------
At first, I wrote my first user story and Scenario based on the example codes given. However when I tried to test stepDefinitions.java, it would report errors no matter how I change the codes. Then I got that I need to run testRunner.java and built successful. But the output is 0 Scenarios, 0 Steps, 0m0.000s. It skipped my scenario and steps. Then I rechecked my code and compared with the example. I find that when I wrote .feature file, I add a ‘1’ after Scenario (Scenario 1: xxxxx). What I thought is just to mark the scenario since I would have several scenarios for one user story.  I realized that it might be a key words and the what I wrote in .feature start with Given/When/Then must be same with those in stepDefinitions.java class. 

Another problem I met is that when I use assertTrue() method, the compiler will notice me to import the lib, the first recommendation  is org.junit.Assert.assertTrue. However it will cause the test fail, but when I annotated it and import unit.framework.Assert.assertTrue; it worked. It’s confused.
Xpath is not easy to write. So I use more By.id() and By.className() method. However sometimes using By.id() method didn’t work. Then I need to using xpath. There is a small tip using Chrome browser’s inspect to select the element you want and can do copy xpath.

User stories and scenarios
--------------------
User Story 1: As a user, I want to login, So that I can know my account informations like purchase history
   ----Scenario : log in without inputing username and password.
	     Given no username and password input,
             When I try to login without inputting username and password,
             Then I should see an message to remind me to enter username and password.	     
   ----Scenario : log in with correct username and password.
	     Given a correct username and password,
             When I try to log in with those credentials,
             Then I should login successfully with my username showed.	     
   ----Scenario 3: log in with correct username and incorrect password.
	     Given a correct username and an incorrect password,
            When I try to log in with correct username and incorrect password,
            Then I should see an error message.
	    
User Story 2: As a user, I want to search the web site, So that I can find products I want that the online have.
   ----Scenario 1: search items the store have
		        Given I open the home page of the online store
            When I try to search iPad
            Then I should see the product iPad     
   ----Scenario 2: search items the store does not have
		        Given I already open the home page of the online store
            When I try to search iMac which the store doesn't sell
            Then I should see an message noticing me no matched products 	    
   ----Scenario 3: do search directly without input anything in search box
	    Given I open online store's homepage
            When I try to do search directly without input anything
            Then I should see numerous products
	    
User Story 3:As a user, I want to add items to cart and remove items from cart,So that I can buy products I want
   ----Scenario (1): add one Apple TV to cart
	    Given I am browsing Apple TV product page
            When I try to add one Apple TV to cart and go to checkout
            Then I should have one Apple TV in my cart	    
   ----Scenario (2): add one Apple TV to cart, then change the quantity
	    Given There is already one Apple TV in cart
            When I changed the quantity of Apple TV to 5
            Then I should have 5 Apple TV in cart	    
  ----Scenario (3): add one Apple TV to cart, then remove it
	    Given There is already and only one Apple TV in cart
            When I remove the item from the cart
            Then I can not see the item removed in cart
