package Step_definitions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseStep {

    String MakeAPostEndpoint, MakeCommentEndpoint, CreateUsersEndpoint;
    public String MakeAPostPayload, CreateCommentPayload;

    String ServiceURL;
    public Headers headers;
    private Response response;
    private String endpointPath;
    public DocumentContext requestBodyJson;

    public BaseStep(){

        //endpoints
        ServiceURL = "https://jsonplaceholder.typicode.com";
        MakeAPostEndpoint = ServiceURL + "/posts/";
        MakeCommentEndpoint = ServiceURL + "/comments/";
        CreateUsersEndpoint = ServiceURL + "/users/";

//Paths
        MakeAPostPayload = "/templates/MakeAPostTemplate.json";
        CreateCommentPayload= "/templates/CreateCommentTemplate.json";

        //Needed to change the format of the template

    };

    public void setHeaders(Headers value){
        resetHeaders();
           headers = value;
    }
    public void resetHeaders(){
        headers = null;
    }
    public void setHeadersWithContentType(){
        headers = new Headers(
            new Header("Content-Type", "application/json"));
            setHeaders(headers);
        }
        public Response getCall() throws MalformedURLException {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .get(getUrl())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
        }

    public Response getPostCall() throws MalformedURLException {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBodyJson.jsonString())
                .when()
                .post(getUrl())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }
    private URL getUrl() throws MalformedURLException {

            return new URL(endpointPath);

    }

    public void setEndPointPath(String endPointPath){
            this.endpointPath =endPointPath;
    }

    public Response getResponse(){
        return  response;
    }

    public DocumentContext loadJsonTemplate(String path){
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson;
    }

}




