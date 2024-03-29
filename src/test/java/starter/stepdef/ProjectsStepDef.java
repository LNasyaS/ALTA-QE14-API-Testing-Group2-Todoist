package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.todoist.TodoistAPI;
import starter.todoist.TodoistResponses;
import starter.utils.Constants;
import io.restassured.response.Response;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProjectsStepDef {

    @Steps
    TodoistAPI todoistAPI;

    String textResponse = "";

//  Get all project
    @Given("Get all projects")
    public void getAllProjects() {
        todoistAPI.getAllProjects();
    }

    @When("Send request get all projects")
    public void sendRequestGetAllProjects() {
        Response response = SerenityRest.when()
                .get(TodoistAPI.GET_ALL_PROJECTS);
        textResponse = response.asString();
    }

    @And("Response body should be id {string} and name {string}")
    public void responseBodyShouldBeAndName(String id, String name) {
        SerenityRest.and()
                .body(TodoistResponses.ALL_PROJECTS_ID, equalTo(id))
                .body(TodoistResponses.ALL_PROJECTS_NAME, equalTo(name));
    }


//  Create project positive
    @Given("Post create new project with valid {string}")
    public void postCreateNewProjectWithValid(String json) {

        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPI.createProject(jsonFile);
    }

    @When("Send request create project")
    public void sendRequestCreateProject() {
        Response response = SerenityRest.when()
                .post(TodoistAPI.CREATE_PROJECT);
        textResponse = response.asString();
    }


    @And("Response body name should be {string}")
    public void responseBodyNameShouldBeAnd(String name) {
        SerenityRest.and()
                .body(TodoistResponses.PROJECTS_NAME, equalTo(name));
    }


//  Create project negative
    @Given("Post create new project with invalid {string}")
    public void postCreateNewProjectWithInvalid(String json) {

        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPI.createProject(jsonFile);
    }

    @And("Response body error should be {string}")
    public void responseBodyErrorShouldBe(String text) {
        assertThat(textResponse, equalTo(text));
    }

//  Get project positive
    @Given("Get project with valid id {long}")
    public void getProjectWithValidId(long id) {
        todoistAPI.getProject(id);
    }

    @When("Send request get project")
    public void sendRequestGetProject() {
        Response response = SerenityRest.when()
                .get(TodoistAPI.GET_PROJECT);
        textResponse = response.asString();
    }

    @And("Response body project should be id {string} and name {string}")
    public void responseBodyProjectShouldBeIdAndName(String id, String name) {
        SerenityRest.and()
                .body(TodoistResponses.PROJECTS_ID, equalTo(id))
                .body(TodoistResponses.PROJECTS_NAME, equalTo(name));
    }

//  Get project negative
    @Given("Get project with invalid id {string}")
    public void getProjectWithInvalidId(String id) {
        todoistAPI.getProjectInvalidId(id);
    }

//  Update project positive
    @Given("Post update with valid {string} and valid id {long}")
    public void postUpdateWithValidAndValidId(String json, long id) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPI.updateProject(jsonFile, id);
    }

    @When("Send request update project")
    public void sendRequestUpdateProject() {
        SerenityRest.when()
                .post(TodoistAPI.UPDATE_PROJECT);
    }

//  Delete project
    @Given("Delete user with valid id {long}")
    public void deleteUserWithValidId(long id) {
        todoistAPI.deleteProject(id);
    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when()
                .delete(TodoistAPI.DELETE_PROJECT);
    }

//  Get collaborators

    @Given("Get project collaborators with id {long}")
    public void getProjectCollaboratorsWithId(long id) {
        todoistAPI.getCollaborators(id);
    }

    @When("Send request get collaborators")
    public void sendRequestGetCollaborators() {
        SerenityRest.when()
                .get(TodoistAPI.GET_COLLABORATORS);
    }

    @And("Response body id should be {string} and name {string}")
    public void responseBodyIdShouldBeAndName(String id, String name) {
        SerenityRest.and()
                .body(TodoistResponses.COLLABORATORS_ID, equalTo(id))
                .body(TodoistResponses.COLLABORATORS_NAME, equalTo(name));
    }



}
