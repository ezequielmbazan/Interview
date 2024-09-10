package com.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UIStepDefinitions {

    private WebDriver driver;
    WebElement googleButton;

    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "/Users/ezequielbazan/IdeaProjects/Interview/geckodriver");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
        driver = new FirefoxDriver(options);


    }

    @Given("I open the browser and navigate to {string}")
    public void navigateTo(String url) {
        setUp();
        driver.get(url);
    }

    @When("Google signup button appears")
    public void buttonAppears(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='Botón Iniciar sesión con Google']")));


        driver.switchTo().frame(iframeElement);


        googleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container\"]/div/div[2]/span[1]")));

        Assert.assertTrue("Sing up button is not displayed",googleButton.isDisplayed());

        googleButton.click();
    }

    @Then("Signup popup is open")
    public void signUpIsOpen() {
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {

                driver.switchTo().window(handle);

                break;
            }

            assertTrue("Pop up is not open", driver.findElement(By.name("identifier")).isDisplayed());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
