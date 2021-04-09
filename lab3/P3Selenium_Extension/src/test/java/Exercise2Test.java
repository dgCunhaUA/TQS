import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class Exercise2Test {


    @Test
    void testExercise2WithFirefox(FirefoxDriver driver1) {

        driver1.get("https://blazedemo.com/");
        driver1.manage().window().setSize(new Dimension(1853, 1053));
        driver1.findElement(By.name("fromPort")).click();
        driver1.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(1)")).click();
        driver1.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver1.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        driver1.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        driver1.findElement(By.cssSelector(".btn-primary")).click();
        driver1.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        driver1.findElement(By.id("inputName")).click();
        driver1.findElement(By.id("inputName")).sendKeys("Diogo");
        driver1.findElement(By.id("creditCardNumber")).click();
        driver1.findElement(By.id("creditCardNumber")).sendKeys("123456789");
        driver1.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver1.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
    }

}