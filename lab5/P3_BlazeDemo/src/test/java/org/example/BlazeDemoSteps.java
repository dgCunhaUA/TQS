package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BlazeDemoSteps {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on {string} page")
    public void iNavigateTo(String arg0) {
        driver.get(arg0);
    }

    @And("I choose {string} for departure city")
    public void iChooseForDepartureCity(String arg0) {
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.xpath("//option[@value=\'"+arg0+"\']")).click();
    }

    @And("I choose {string} for destination city")
    public void iChooseForDestinationCity(String arg0) {
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.xpath("//option[@value=\'"+arg0+"\']")).click();
    }

    @And("I click in {string}")
    public void iClick(String arg0) {
        driver.findElement(By.xpath("//input[@value=\'"+arg0+"\']")).click();
    }

    @Then("{string} should appear as a h3 title")
    public void shouldAppearAsH3Title(String arg0) {
        driver.findElement(By.xpath("//h3[contains(.,\'"+arg0+":\')]")).click();
    }

    @Then("{string} should appear as a h2 title")
    public void shouldAppearAsH2Title(String arg0) {
        driver.findElement(By.xpath("//h2[contains(.,\'"+arg0+"\')]")).click();
    }

    @And("Fill some info by writing {string} in your credit card name")
    public void fillSomeInfoByWritingInYourCreditCardName(String arg0) {
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(arg0);
    }

    @Then("{string} should appear as a h1 title")
    public void shouldAppearAsH1Title(String arg0) {
        driver.findElement(By.xpath("//h1[contains(.,"+arg0 +")]"));
    }

}
