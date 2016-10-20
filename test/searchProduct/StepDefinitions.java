/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchProduct;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Mengjiao
 */
public class StepDefinitions {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private final String HOME_PAGE = "http://store.demoqa.com/";
    /**
     * Scenario 1: search items the store have
     **/
    @Given("I open the home page of the online store")
    public void HomePage() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(HOME_PAGE);
        wait = new WebDriverWait(driver, 30);
    }
    @When("I try to search iPad")
    public void searchIPad() {
        WebElement search = driver.findElement(By.className("search"));       
        search.clear();
        search.sendKeys("iPad");
        search.sendKeys(Keys.RETURN);
    }
    @Then("I should see the product iPad")
    public void resultOfSearchIPad() {
        wait=new WebDriverWait(driver, 30);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(@class, 'prodtitle')]")));
        assertTrue(result.getText().contains("iPad"));
    }
    
     /**
     * Scenario 2: search items the store does not have
     **/
    @Given("I already open the home page of the online store")
    public void openHomePage() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(HOME_PAGE);
        wait = new WebDriverWait(driver, 30);
    }
    @When("I try to search iMac which the store doesn't sell")
    public void searchIMac() {
        WebElement search = driver.findElement(By.className("search"));       
        search.clear();
        search.sendKeys("iMac");
        search.sendKeys(Keys.RETURN);
    }
    @Then("I should see an message noticing me no matched products")
    public void resultOfSearchIMac() {       
        wait = new WebDriverWait(driver, 30);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/p")));
        assertEquals("Sorry, but nothing matched your search criteria. Please try again with some different keywords.",result.getText());      
    }
    
    /**
     * Scenario 3: do search directly without input anything in search box 
     **/
    @Given("I open online store's homepage")
    public void openHomePageAgain() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(HOME_PAGE);
        wait = new WebDriverWait(driver, 30);
    }
    @When("I try to do search directly without input anything")
    public void search() {
        WebElement search = driver.findElement(By.className("search"));
        search.clear();
        search.sendKeys(Keys.RETURN);
    }
    @Then("I should see numerous products")
    public void resultOfSearchNothing() {
        wait=new WebDriverWait(driver, 30);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grid_view_products_page_container")));
        assertTrue(result.getText().contains("iPhone"));
        assertTrue(result.getText().contains("iPad"));
        assertTrue(result.getText().contains("iPod"));       
    }
    
     @After
    public void cleanUp() {
        driver.quit();
    }
}
