package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TaskSteps {
    //global variables
    String id = "id";
    String title = "title";
    String author = "author";
    String body = "body";
    String postId = "postId";
    String name = "name";

    String titleBody = "json-server";
    String authorBody = "typicode";
    String commentBody = "some comment";


    String uniqueIdPosts = UUID.randomUUID().toString();
    String uniqueIdComment = UUID.randomUUID().toString();
    String uniqueIdProfile = "1";


    @Given("^I am opening localhost posts and get OK status$")
    public void iAmOpeningLocalhostPostsAndGetOKStatus() {
        given().
                when().
                get("http://localhost:3000/posts").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost posts with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostPostsWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get("http://localhost:3000/posts/" + data).
                then().
                assertThat().
                statusCode(404);
    }

    //@Test
    @Then("^I am trying to test POST method in posts and get CREATED status$")
    public void iAmTryingToTestPOSTMethodInPostsAndGetCREATEDStatus() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        //json
        JSONObject json = new JSONObject();
        json.put(id, uniqueIdPosts);
        json.put(title, titleBody);
        json.put(author, authorBody);
        request.body(json.toJSONString());

        //rest assured
        Response response = request.post("http://localhost:3000/posts");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        //compare data
        List<Map<String, Object>> posts = get("http://localhost:3000/posts").as(new TypeRef<List<Map<String, Object>>>() {
        });

        Assert.assertThat(posts.get(posts.size() - 1).get(id), equalTo(uniqueIdPosts));
        Assert.assertThat(posts.get(posts.size() - 1).get(title), equalTo(titleBody));
        Assert.assertThat(posts.get(posts.size() - 1).get(author), equalTo(authorBody));
    }

    @Given("^I am opening localhost comments and get OK status$")
    public void iAmOpeningLocalhostCommentsAndGetOKStatus() {
        given().
                when().
                get("http://localhost:3000/comments").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost comments with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostCommentsWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get("http://localhost:3000/comments/" + data).
                then().
                assertThat().
                statusCode(404);
    }

    @And("^I am trying to test POST method in comments and get CREATED status$")
    public void iAmTryingToTestPOSTMethodInCommentsAndGetCREATEDStatus() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put(id, uniqueIdComment);
        json.put(body, commentBody);
        json.put(postId, uniqueIdPosts);
        request.body(json.toJSONString());

        Response response = request.post("http://localhost:3000/comments");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        //compare data
        List<Map<String, Object>> comments = get("http://localhost:3000/comments").as(new TypeRef<List<Map<String, Object>>>() {
        });

        Assert.assertThat(comments.get(comments.size() - 1).get(id), equalTo(uniqueIdComment));
        Assert.assertThat(comments.get(comments.size() - 1).get(body), equalTo(commentBody));
        Assert.assertThat(comments.get(comments.size() - 1).get(postId), equalTo(uniqueIdPosts));
    }

    @Given("^I am opening localhost profile and get OK status$")
    public void iAmOpeningLocalhostProfileAndGetOKStatus() {
        given().
                when().
                get("http://localhost:3000/profile").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost profile with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostProfileWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get("http://localhost:3000/profile/" + data).
                then().
                assertThat().
                statusCode(404);
    }

    @Then("^I am trying to create one more variable and get CREATED status$")
    public void iAmTryingToCreateOneMoreVariableAndGetCREATEDStatus() {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put(id, uniqueIdProfile);
        json.put(name, authorBody);
        request.body(json.toJSONString());

        Response response = request.post("http://localhost:3000/profile");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        Map<String, Object> profile = get("http://localhost:3000/profile").as(new TypeRef<Map<String, Object>>() {
        });

        Assert.assertThat(profile.get(id), equalTo(uniqueIdProfile));
        Assert.assertThat(profile.get(name), equalTo(authorBody));
    }
}
