package starter.todoist;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.Constants;

import java.io.File;

public class TodoistAPIComment {


    public static String GET_COMMENTS = Constants.BASE_URL + "/comments?task_id={task_id}";

    public static String POST_COMMENT = Constants.BASE_URL + "/comments?task_id={task_id}";

    public static String GET_ID = Constants.BASE_URL + "/comments/{id}";

    public static String UPDATE = Constants.BASE_URL + "/comments/{id}";

    public static String DELETE = Constants.BASE_URL + "/comments/{id}";


    @Step("Get Comments")
    public void getComments(long task_id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .pathParam("task_id", task_id);
    }


    @Step("Create New Comment With Valid Json")
    public void CreateComment(File json, long task_id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("task_id", task_id);

    }

    @Step("Get Id")
    public void getId(long id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .pathParam("id", id);
    }

    @Step("Update New Comment With Valid Json")
    public void UpdateComment(File json, long id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", id);
    }

    @Step("Delete user with valid user id")
    public void deleteUser(long id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get Task Id")
    public void getTaskId(String taskId) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .pathParam("task_id", taskId);
    }

    @Step("Create New Comment With Valid Json")
    public void CreateCommentInvalid(File json, String taskId) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("task_id", taskId);



    }

    @Step("Update New Comment With Valid Json")
    public void UpdateInvalidComment(File json, String id) {
        SerenityRest.given().headers("Authorization", TodoistResponsesComment.COMMENTS_TOKEN)
                .contentType(ContentType.JSON)
                .body(json)
                .pathParam("id", id);
    }

}
