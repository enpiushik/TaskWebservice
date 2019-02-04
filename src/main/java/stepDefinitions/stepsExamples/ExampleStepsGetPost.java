package stepDefinitions.stepsExamples;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.hasSize;

public class ExampleStepsGetPost {

    //test_NumberOfCircuitsFor2017Season_ShouldBe20()
//https://techbeacon.com/app-dev-testing/how-perform-api-testing-rest-assured
    @Given("^GET response from google$")
    public void getResponseFromGoogle() {
        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
    }

    @When("^Trying to get OK status from swapi.co$")
    public void tryingToGetOKStatusFromSwapiCo() {
        given().
                when().
                get("https://swapi.co/api/people/1/").
                then().
                assertThat().
                statusCode(200);
    }

    @Then("^Trying to get NOT FOUND status from swapi.co$")
    public void tryingToGetNOTFOUNDStatusFromSwapiCo() {
        given().
                when().
                get("https://swapi.co/api/people/111/").
                then().
                assertThat().
                statusCode(404);
    }

    @And("^Trying to get OK status from reqres.in POST$")
    public void tryingToGetOKStatusFromReqresInPOST() {
//        post("https://reqres.in/api/users").then().assertThat().statusCode(415);
        given().
                param("id", "437").
                param("createdAt", "2019-01-31T10:53:49.115Z").
                when().
                post("https://reqres.in/api/users").
                then();
//                assertThat().
//                statusCode(201);
    }
}
