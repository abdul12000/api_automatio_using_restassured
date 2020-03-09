package Step_definitions;

import com.jayway.jsonpath.DocumentContext;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SocialNetworkStepDef extends BaseStep implements En {
    Response responseForGetServiceUrl;
    Response responseForGetPostRequest, responseForGetComment, responseForPostRequest, responseForPostCommentRequest;
    DocumentContext requestBody;
    RequestBodyService requestBodyService;

    public SocialNetworkStepDef() {
        Given("^Service is up and running$", () -> {
            setHeadersWithContentType();
            setEndPointPath(ServiceURL);
            try {
                getCall();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            responseForGetServiceUrl = getResponse();
            assertThat(responseForGetServiceUrl.statusCode(), is(200));
        });
        When("^i search for \"([^\"]*)\" of a post with a GET method$", (String id) -> {
            setHeadersWithContentType();
            setEndPointPath(MakeAPostEndpoint + id);
            try {
                getCall();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            responseForGetPostRequest = getResponse();


        });
        Then("^I should get the correct \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" returned$", (String id, String title, String body) -> {
            assertThat("Validate id", responseForGetPostRequest.body().jsonPath().get("id").toString().contains(id), is(true));
            assertThat("Validate title", responseForGetPostRequest.body().jsonPath().get("title"), is(title));
            assertThat("Validate Body", responseForGetPostRequest.body().jsonPath().get("body"), is(body));

        });
        And("^status code of (\\d+) is returned$", (Integer arg0) -> {
            assertThat(responseForGetPostRequest.statusCode(), is(200));
        });
        When("^i search for comment with \"([^\"]*)\" with a GET method$", (String id) -> {
            setHeadersWithContentType();
            setEndPointPath(MakeCommentEndpoint + id);
            try {
                getCall();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            responseForGetComment = getResponse();


        });
        Then("^the correct \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" are returned$",
                (String id, String name, String email, String body) -> {
                    assertThat("Validate id", responseForGetComment.body().jsonPath().get("id").toString().contains(id), is(true));
                    assertThat("Validate name", responseForGetComment.body().jsonPath().get("name"), is(name));
                    assertThat("Validate email", responseForGetComment.body().jsonPath().get("email"), is(email));
                    assertThat("Validate body", responseForGetComment.body().jsonPath().get("body"), is(body));

                });
        And("^status code of (\\d+) is returned for comments$", (Integer sCode) -> {
            assertThat(responseForGetComment.statusCode(), is(sCode));
        });

        When("^I create a new post with the following details \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\",$", (String uid, String title, String body) -> {
            requestBodyService = new RequestBodyService();

//            setHeadersWithContentType();
            requestBody = loadJsonTemplate(MakeAPostPayload);
            requestBodyService.setRequestForBodyForNewPost(requestBody, uid, title, body);
            setEndPointPath(MakeAPostEndpoint);
            try {
                getPostCall();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            responseForPostRequest = getResponse();


        });
        Then("^i should get the correct \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" returned$", (String uid, String title, String body) -> {
            assertThat("Validate uid", responseForPostRequest.body().jsonPath().get("userId"), is(uid));
            assertThat("Validate title", responseForPostRequest.body().jsonPath().get("title"), is(title));
            assertThat("Validate body", responseForPostRequest.body().jsonPath().get("body"), is(body));

        });

        And("^status code of (\\d+) is returned for created post request$", (Integer sCode) -> {
            assertThat(responseForPostRequest.statusCode(), is(sCode));
        });
        When("^I create a new comment with the following details \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$",
                (String postId, String name, String email, String body) -> {
                    requestBodyService = new RequestBodyService();

//            setHeadersWithContentType();
                    requestBody = loadJsonTemplate(CreateCommentPayload);
                    requestBodyService.setRequestForBodyForNewComment(requestBody, postId, name, email, body);
                    setEndPointPath(MakeCommentEndpoint);
                    try {
                        getPostCall();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    responseForPostCommentRequest = getResponse();


                });
        Then("^status code of (\\d+) is returned for create Comment request$", (Integer sCode) -> {
            assertThat(responseForPostCommentRequest.statusCode(), is(sCode));
        });
        And("^i should get the correct \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" returned$",
                (String postId, String name, String email, String body) -> {
                    assertThat("validate PostId", responseForPostCommentRequest.body().jsonPath().get("postId"), is(postId));
                    assertThat("validate name", responseForPostCommentRequest.body().jsonPath().get("name"), is(name));
                    assertThat("validate email", responseForPostCommentRequest.body().jsonPath().get("email"), is(email));
                    assertThat("validate body", responseForPostCommentRequest.body().jsonPath().get("body"), is(body));
                });
    }
}
