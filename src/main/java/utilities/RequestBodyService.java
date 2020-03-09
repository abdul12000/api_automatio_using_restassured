package utilities;

import Step_definitions.BaseStep;
import com.jayway.jsonpath.DocumentContext;

public class RequestBodyService extends BaseStep {
    public void setRequestForBodyForNewPost(DocumentContext requestBody, String uid, String title, String body){
        requestBody.set("userId", uid);
        requestBody.set("title", title);
        requestBody.set("body", body);
    }

    public void setRequestForBodyForNewComment(DocumentContext requestBody, String postId, String name, String email, String body){
        requestBody.set("postId", postId);
        requestBody.set("name", name);
        requestBody.set("email", email);
        requestBody.set("body", body);
    }
}
