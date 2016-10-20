Feature: User Login
    As a user
    I want to login
    So that I can know my account informations like purchase history
        Scenario: log in without inputing username and password
		Given no username and password input
                When I try to login without inputting username and password
                Then I should see an message to remind me to enter username and password

	Scenario: log in with correct username and password
		Given a correct username and password
                When I try to log in with those credentials
                Then I should login successfully with my username showed

        Scenario: log in with correct username and incorrect password
		Given a correct username and an incorrect password
                When I try to log in with correct username and incorrect password
                Then I should see an error message
        
        