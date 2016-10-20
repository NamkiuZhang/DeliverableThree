/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author AsphaltPanthers
 */
public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private final String ACCOUNT_PAGE="http://store.demoqa.com/products-page/your-account/";
    
    /*
    * Scenario 1: log in without inputing username and password
    */
    @Given("no username and password input")
    public void openMyAccountPage3() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(ACCOUNT_PAGE);
        wait = new WebDriverWait(driver, 30);
        assertEquals("Your Account | ONLINE STORE",driver.getTitle());
    }
    @When("I try to login without inputting username and password")
    public void loginWithoutInput(){
        WebElement login = driver.findElement(By.id("login"));
        login.submit(); 
    }
    @Then("I should see an message to remind me to enter username and password")
    public void resultOfLoginWithoutInput(){
        wait=new WebDriverWait(driver, 30);
        WebElement showSuggestion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertTrue(showSuggestion.getText().contains("Please enter your username and password."));
    }
    
    
    /*
    /*
    * Scenario 2: log in with correct username and password
    */   
    
    @Given("a correct username and password")
    public void openMyAccountPage() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(ACCOUNT_PAGE);
        wait = new WebDriverWait(driver, 30);
        assertEquals("Your Account | ONLINE STORE",driver.getTitle());
    }
    
    @When("I try to log in with those credentials")
    public void logWithCorrectCredentials() {
        WebElement username=driver.findElement(By.id("log"));
        username.sendKeys("Namkiu");
        WebElement pwd=driver.findElement(By.id("pwd"));
        pwd.sendKeys("123456");
        WebElement login = driver.findElement(By.id("login"));
        login.submit();    
    }
    
    @Then("I should login successfully with my username showed")
    public void logSuccessed() {
        wait = new WebDriverWait(driver, 30);
        WebElement showInfo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wp-admin-bar-my-account")));;
        assertEquals("Howdy, Namkiu", showInfo.getText());
    }
    
    /*
    * Scenario 3: log in with correct username and incorrect password
    */
    @Given("a correct username and an incorrect password")
    public void openMyAccountPage2() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(ACCOUNT_PAGE);
        wait = new WebDriverWait(driver, 30);
        assertEquals("Your Account | ONLINE STORE",driver.getTitle());
    }
    
    @When("I try to log in with correct username and incorrect password")
    public void logWithCorrectUsernameIncorrectPwd() {
        WebElement username=driver.findElement(By.id("log"));
        username.sendKeys("Namkiu");
        WebElement pwd=driver.findElement(By.id("pwd"));
        pwd.sendKeys("111");
        WebElement login = driver.findElement(By.id("login"));
        login.submit();    
    }
     
    @Then("I should see an error message")
    public void logFailed() {       
        wait = new WebDriverWait(driver, 30);
        WebElement showErrorInfo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]")));;
        assertTrue(showErrorInfo.getText().contains("ERROR"));
    }
    
    
    
    @After
    public void cleanUp() {
        driver.quit();
    }
}
