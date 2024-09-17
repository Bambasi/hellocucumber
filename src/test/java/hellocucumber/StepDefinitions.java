package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private String today;
    private String response;


    private String checkDay(String day) {
        // Vérifie quel jour il est et renvoie la réponse correspondante
        switch (day) {
            case "Friday":
                return "Yes4";
            case "Monday":
                return "Yes";
            case "Tuesday":
                return "Yes1";
            case "Wednesday":
                return "Yes2";
            case "Thursday":
                return "Yes3";
            default:
                return "Nope";
        }
    }


    @Given("today is {string}")
    public void today_is(String today) {
        this.today = today ;
    }

    @When("I ask what day it is")
    public void i_ask_what_day_it_is() {
        response = checkDay(today);
    }


    @Then("I should be told {string}")
    public void i_should_be_told(String expectedResponse) {
        assertEquals(expectedResponse, response);
    }
}