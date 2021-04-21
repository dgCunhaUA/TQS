package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorSteps {

    private Calculator calc;

    @Given("a calculator I just turned on")
    public void aCalculatorIJustTurnedOn() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void iSubstractTo(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("-");
    }

    @Then("the result is {double}")
    public void theResultIs(int arg0) {
        assertEquals(arg0, calc.value());
    }

    @When("I add <a> and <b>")
    public void iAddAAndB() {
    }

    @Then("the result is <c>")
    public void theResultIsC() {
    }

    @Then("the result is {double}")
    public void theResultIs(int arg0, int arg1) {
    }
}
