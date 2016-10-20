Feature: Add Item to Cart
    As a user
    I want to add items to cart and remove items from cart
    So that I can buy products I want

	Scenario: add one Apple TV to cart
		Given I am browsing Apple TV product page
                When I try to add one Apple TV to cart and go to checkout
                Then I should have one Apple TV in my cart

        Scenario: add one Apple TV to cart, then change the quantity
		Given There is already one Apple TV in cart
                When I changed the quantity of Apple TV to 5
                Then I should have 5 Apple TV in cart

        Scenario: add one Apple TV to cart, then remove it
		Given There is already and only one Apple TV in cart
                When I remove the item from the cart
                Then I can not see the item removed in cart