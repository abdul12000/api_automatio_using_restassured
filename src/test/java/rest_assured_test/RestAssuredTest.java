package rest_assured_test;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;


public class RestAssuredTest {

    public void runGetParticularPost() {

        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/posts?id=1")
                .then().statusCode(200)
                .body("title", hasItem("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("body", hasItem("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"))
                .log().all();
    }

    @Test
    public void runAPostRequest() {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("userId", "3001");
        postContent.put("title", "My new Holiday experience");
        postContent.put("body", "I went to LOndon on holiday and i had a really good time");

        given().contentType(ContentType.JSON).log().all()
                .with().body(postContent)
                .when().post("https://jsonplaceholder.typicode.com/posts")
                .then().statusCode(201)
                .body("title", is("My new Holiday experience"))
                .body("body", is("I went to LOndon on holiday and i had a really good time"))
                .log().all();


    }
}
