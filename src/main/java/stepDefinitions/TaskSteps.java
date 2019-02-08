package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.awaitility.Awaitility;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    //    String uniqueIdComment = UUID.randomUUID().toString();
    String uniqueIdProfile = "1";

    String URL = "http://localhost:3000";

    @Given("^I am opening localhost posts and get OK status$")
    public void iAmOpeningLocalhostPostsAndGetOKStatus() throws Throwable {
        given().
                when().
                get(URL + "/posts").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost posts with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostPostsWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get(URL + "/posts" + data).
                then().
                assertThat().
                statusCode(404);
    }

    @Then("^I am trying to test POST method in posts and get CREATED status$")
    public void iAmTryingToTestPOSTMethodInPostsAndGetCREATEDStatus() throws Throwable {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        //json
        JSONObject json = new JSONObject();
//        json.put(id, uniqueIdPosts);
        json.put(title, titleBody);
        json.put(author, authorBody);
        request.body(json.toJSONString());

        //rest assured
        Response response = request.post(URL + "/posts");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        //compare data
        List<Map<String, Object>> posts = get(URL + "/posts").as(new TypeRef<List<Map<String, Object>>>() {
        });

//        Assert.assertThat(posts.get(posts.size() - 1).get(id), equalTo(uniqueIdPosts));
        Assert.assertThat(posts.get(posts.size() - 1).get(title), equalTo(titleBody));
        Assert.assertThat(posts.get(posts.size() - 1).get(author), equalTo(authorBody));
    }

    @Given("^I am opening localhost comments and get OK status$")
    public void iAmOpeningLocalhostCommentsAndGetOKStatus() throws Throwable {
        given().
                when().
                get(URL + "/comments").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost comments with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostCommentsWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get(URL + "/comments/" + data).
                then().
                assertThat().
                statusCode(404);
    }

    @And("^I am trying to test POST method in comments and get CREATED status$")
    public void iAmTryingToTestPOSTMethodInCommentsAndGetCREATEDStatus() throws Throwable {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
//        json.put(id, uniqueIdComment);
        json.put(body, commentBody);
        json.put(postId, uniqueIdPosts);
        request.body(json.toJSONString());

        Response response = request.post(URL + "/comments");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        //compare data
        List<Map<String, Object>> comments = get(URL + "/comments").as(new TypeRef<List<Map<String, Object>>>() {
        });

//        Assert.assertThat(comments.get(comments.size() - 1).get(id), equalTo(uniqueIdComment));
        Assert.assertThat(comments.get(comments.size() - 1).get(body), equalTo(commentBody));
        Assert.assertThat(comments.get(comments.size() - 1).get(postId), equalTo(uniqueIdPosts));
    }

    @Given("^I am opening localhost profile and get OK status$")
    public void iAmOpeningLocalhostProfileAndGetOKStatus() throws Throwable {
        given().
                when().
                get(URL + "/profile").
                then().
                assertThat().
                statusCode(200);
    }

    @When("^I am opening localhost profile with not correct: \"([^\"]*)\" and get NOT FOUND status$")
    public void iAmOpeningLocalhostProfileWithNotCorrectAndGetNOTFOUNDStatus(String data) throws Throwable {
        given().
                when().
                get(URL + "/profile/" + data).
                then().
                assertThat().
                statusCode(404);
    }

    @Then("^I am updating data and get CREATED status$")
    public void iAmUpdatingDataAndGetCREATEDStatus() throws Throwable {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put(id, uniqueIdProfile);
        json.put(name, authorBody);
        request.body(json.toJSONString());

        Response response = request.post(URL + "/profile");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

        Map<String, Object> profile = get(URL + "/profile").as(new TypeRef<Map<String, Object>>() {
        });

        Assert.assertThat(profile.get(id), equalTo(uniqueIdProfile));
        Assert.assertThat(profile.get(name), equalTo(authorBody));
    }

    @When("^I am going to post with id one$")
    public void iAmGoingToPostWithIdOne() throws Throwable {
        given().
                when().
                get(URL + "/posts/1").
                then().
                assertThat().
                statusCode(200);
    }

    @Then("^I am creating additional post with \"([^\"]*)\", \"([^\"]*)\" and get CREATED status$")
    public void iAmCreatingAdditionalPostWithAndGetCREATEDStatus(String title1, String author1) throws Throwable {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
//        json.put(id, uniqueIdPosts);
        json.put(title, title1);
        json.put(author, author1);
        request.body(json.toJSONString());

        Response response = request.post(URL + "/posts");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

    @And("^I am going to all posts$")
    public void iAmGoingToAllPosts() throws Throwable {
        given().
                when().
                get(URL + "/posts").
                then().
                assertThat().
                statusCode(200);
    }

    @And("^I am going on home page$")
    public void iAmGoingOnHomePage() throws Throwable {
        given().
                when().
                get(URL).
                then().
                assertThat().
                statusCode(200);
    }

    @Given("^I am creating user with \"([^\"]*)\", \"([^\"]*)\" and get CREATED status$")
    public void iAmCreatingUserWithAndGetCREATEDStatus(String age1, String name1) throws Throwable {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        String age = "age";

        JSONObject json = new JSONObject();
        json.put(age, age1);
        json.put(name, name1);
        request.body(json.toJSONString());

        Response response = request.post(URL + "/users");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

    @When("^I am getting user with age that equalTo$")
    public void iAmGettingUserWithAgeThatEqualTo() throws Throwable {
        given().
                when().
                get(URL + "/users/2/").
                then().
                statusCode(200).
                assertThat().
                body("age", equalTo("25"));
    }

    @Then("^I am getting user with name that equalTo$")
    public void iAmGettingUserWithNameThatEqualTo() throws Throwable {
        given().
                when().
                get(URL + "/users/2/").
                then().
                statusCode(200).
                body("name", equalTo("Anna"));
    }

    @And("^I am going to all users$")
    public void iAmGoingToAllUsers() throws Throwable {
        get(URL + "/users").
                then().
                assertThat().
                statusCode(200);
    }

    @Given("^I am getting all headers and check status code$")
    public void iAmGettingAllHeadersAndCheckStatusCode() throws Throwable {
        Response response = get(URL);
        Headers allHeaders = response.getHeaders();
        System.out.println("HEADERS:" + "\n" + allHeaders);

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @And("^I am getting single header Content-Type, checking status code, validation response$")
    public void iAmGettingSingleHeaderContentTypeCheckingStatusCodeValidationResponse() throws Throwable {
        Response response = get(URL + "/posts");
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        System.out.println("\n" + "Content-Type: " + contentType);
        System.out.println("\n" + response.getStatusLine());

        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Given("^I am creating post with \"([^\"]*)\" and \"([^\"]*)\" for delete$")
    public void iAmCreatingPostWithAndForDelete(String title1, String author1) throws Throwable {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put(id, "123");
        json.put(title, title1);
        json.put(author, author1);
        request.body(json.toJSONString());

        Response response = request.post(URL + "/posts");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);
    }

    @When("^I delete created post$")
    public void iDeleteCreatedPost() throws Throwable {
        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> this.deleteStatus("/posts/123") == 200);
    }

    public int deleteStatus(String parametr) {
        return given().
                when().
                delete(URL + parametr).
                then().
                extract().
                statusCode();
    }

    @Then("^I am checking that correct post was deleted$")
    public void iAmCheckingThatCorrectPostWasDeleted() throws Throwable {
        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> this.getStatus("/posts/123") == 404);
    }

    public int getStatus(String parametr) {
        return given().
                when().
                get(URL + parametr).
                then().
                extract().
                statusCode();
    }

    @And("^I delete all created posts and comments$")
    public void iDeleteAllCreatedPostsAndComments() throws Throwable {
        for (int i = 2; i < 4; i++) {
            String str_i = Integer.toString(i);
            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.deleteStatus("/posts/" + str_i + "/") == 200);
            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.getStatus("/posts/" + str_i + "/") == 404);
        }

//        for (int i = 2; i < 3; i++) {
//            String str_i = Integer.toString(i);
//            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.deleteStatus("/comments/" + str_i + "/") == 200);
//            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.getStatus("/comments/" + str_i + "/") == 404);
//        }
//
//
//        for (int i = 2; i < 3; i++) {
//            String str_i = Integer.toString(i);
//            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.deleteStatus("/users/" + str_i + "/") == 200);
//            Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> this.getStatus("/users/" + str_i + "/") == 404);
//        }

        given().
                when().
                delete(URL + "/comments/2");

        given().
                when().
                delete(URL + "/users/2");


    }
}
