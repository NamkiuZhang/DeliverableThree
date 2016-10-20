/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddItemToCart;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.google.common.base.Predicate;
import static junit.framework.Assert.assertEquals;
import org.junit.Assert;
import org.openqa.selenium.By;
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
    
    private final String APPLETV_PAGE="http://store.demoqa.com/products-page/product-category/accessories/apple-tv/";
    
    /*
    * Scenario 1: add one Apple TV to cart
    */   
    
    @Given("I am browsing Apple TV product page")
    public void openAppleTVPage() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(APPLETV_PAGE);
        wait = new WebDriverWait(driver, 30);
        assertEquals("Apple TV | ONLINE STORE",driver.getTitle());
    }
    
    @When("I try to add one Apple TV to cart and go to checkout")
    public void addAppleTV(){
        WebElement addToCart=driver.findElement(By.className("wpsc_buy_button"));
        addToCart.submit();
        WebElement goToCheckout = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCheckout.click();
    }
    
    
    @Then("I should have one Apple TV in my cart")
    public void productInCart(){
        WebElement itemInCart = (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_page_container")));
        Assert.assertTrue(itemInCart.getText().contains("Apple TV"));
        Assert.assertTrue(itemInCart.getText().contains("$80.00"));
    }
    
    /*
    * Scenario 2: add one Apple TV to cart, then change the quantity
    */
    @Given("There is already one Apple TV in cart")
    public void oneItemInCart() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(APPLETV_PAGE);
        wait = new WebDriverWait(driver, 30);
        WebElement addToCart=driver.findElement(By.className("wpsc_buy_button"));
        addToCart.submit();
        wait=new WebDriverWait(driver,30);
        WebElement goToCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCheckout.click();
    }
    @When("I changed the quantity of Apple TV to 5")
    public void changeItemQuantity(){
        WebElement quantity = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'quantity')]")));
        quantity.clear();
        quantity.sendKeys("5");
        WebElement updateQuantity = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value, 'Update')]")));
        updateQuantity.click();
    }
    @Then("I should have 5 Apple TV in cart")
    public void quantityAfterUpdate(){
        WebElement quantity = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'quantity')]")));
        assertEquals("5", quantity.getAttribute("value"));
    }
    
    /*
    * Scenario 3: add one Apple TV to cart, then remove it
    */
    @Given("There is already and only one Apple TV in cart")
    public void onlyOneItemInCart(){
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        driver.get(APPLETV_PAGE);
        wait = new WebDriverWait(driver, 30);
        WebElement addToCart=driver.findElement(By.className("wpsc_buy_button"));
        addToCart.submit();
        WebElement goToCheckout = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCheckout.click();
    }
    @When("I remove the item from the cart")
    public void removeItem(){
        wait=new WebDriverWait(driver, 30);   
        WebElement removeItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value, 'Remove')]")));
        removeItem.click();
    }
    @Then("I can not see the item removed in cart")
    public void afterRemove(){
        wait.until((Predicate<WebDriver>) w -> w.findElement(By.className("entry-content")).getText().equals("Oops, there is nothing in your cart."));
    }
    
    @After
    public void cleanUp() {
        driver.quit();
    }
}
