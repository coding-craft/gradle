package com.student.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class StudentRegistrationStepDefinitions {
    @Given("^I am on the Students registration page$")
    public void i_am_on_the_Students_registration_page() {
        System.out.println("i_am_on_the_Students_registration_page");
    }

    @When("^Click on Register button$")
    public void click_on_Register_button() {
        System.out.println("click_on_Register_button");
    }

    @Then("^I should be able to register a new student$")
    public void i_should_be_able_to_register_a_new_student() {
        System.out.println("i_should_be_able_to_register_a_new_student");
    }
}
