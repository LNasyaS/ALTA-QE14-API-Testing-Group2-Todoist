package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.todoist.TodoistAPIComment;
import starter.todoist.TodoistResponsesComment;
import starter.utils.Constants;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class CommentsStepDef {

    @Steps
    TodoistAPIComment todoistAPIComment;
    String textResponse = "";

    @Given("Get all comments with task id {long}")
    public void getAllCommentsWithTaskId(long task_id) {
        todoistAPIComment.getComments(task_id);
    }


    @When("Send request get all comments")
    public void sendRequestGetAllComments() {
        Response response = SerenityRest.when().get(todoistAPIComment.GET_COMMENTS);
        textResponse = response.asString();
    }


    @And("Response body should be show task id {string}")
    public void responseBodyShouldBeShowTaskId(String id) {
        SerenityRest.and().body(TodoistResponsesComment.ALL_COMMENT_ID, equalTo(id));
    }


    @Given("Create a new comments with valid json {string} and valid id {long}")
    public void createANewCommentsWithValidJsonAndValidId(String json, long task_id) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPIComment.CreateComment(jsonFile, task_id);
    }

    @When("Send request post comment")
    public void sendRequestPostComment() {
        Response response = SerenityRest.when().post(todoistAPIComment.POST_COMMENT);
        textResponse = response.asString();
    }


    @Given("Get a comment with id {long}")
    public void getACommentWithId(long id) {
        todoistAPIComment.getId(id);
    }

    @When("Send request get a comments")
    public void sendRequestGetAComments() {
        Response response = SerenityRest.when().get(todoistAPIComment.GET_ID);
        textResponse = response.asString();

    }

    @And("Response body should be show id {string}")
    public void responseBodyShouldBeShowId(String id) {
        SerenityRest.and().body(TodoistResponsesComment.A_COMMENT_ID, equalTo(id));
    }


    @When("Send request update a comment")
    public void sendRequestUpdateAComment() {
        Response response = SerenityRest.when().post(todoistAPIComment.UPDATE);
        textResponse = response.asString();

    }

    @Given("Update a comment with valid json {string} and valid id {long}")
    public void updateACommentWithValidJsonAndValidId(String json, long id) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPIComment.UpdateComment(jsonFile, id);

    }

    @Given("Delete a comment with valid id {long}")
    public void deleteACommentWithValidId(long id) {
        todoistAPIComment.deleteUser(id);
    }

    @When("Send request delete a comment")
    public void sendRequestDeleteAComment() {
        SerenityRest.when().delete(todoistAPIComment.DELETE);
    }

    @Given("Get a comment with invalid id {long}")
    public void getACommentWithInvalidId(long id) {
        todoistAPIComment.getId(id);
    }

    @And("Response body error should be {string}")
    public void responseBodyErrorShouldBe(String text) {
        assertThat(textResponse,equalTo(text));
    }

    @Given("Get all comment with invalid task id {string}")
    public void getAllCommentWithInvalidTaskId(String task_id) {
            todoistAPIComment.getTaskId(task_id);
    }



    @Given("Create new comment with valid json {string} and invalid task id {string}")
    public void createNewCommentWithValidJsonAndInvalidTaskId(String json, String taskId) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPIComment.CreateCommentInvalid(jsonFile, taskId);
    }


    @Given("Update a comment with valid json {string} and invalid id {string}")
    public void updateACommentWithValidJsonAndInvalidId(String json, String id) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPIComment.UpdateInvalidComment(jsonFile, id);
    }


}

