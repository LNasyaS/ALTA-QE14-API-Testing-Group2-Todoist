package starter.todoist;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.Constants;

import java.io.File;

public class TodoistAPI {

    public static String GET_ALL_PROJECTS = Constants.BASE_URL + "/projects";

    public static String CREATE_PROJECT = Constants.BASE_URL + "/projects";

    public static String GET_PROJECT = Constants.BASE_URL + "/projects/{id}";

    public static String UPDATE_PROJECT = Constants.BASE_URL + "/projects/{id}";

    public static String DELETE_PROJECT = Constants.BASE_URL + "/projects/{id}";

    public static String GET_COLLABORATORS = Constants.BASE_URL + "/projects/{id}/collaborators";

//    feature-projects
    @Step("Get all projects")
    public void getAllProjects() {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN);
    }

    @Step("Create a project")
    public void createProject(File json) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get a project")
    public void getProject(long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get a project with invalid id")
    public void getProjectInvalidId(String id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .pathParam("id", id);
    }

    @Step("Update a project")
    public void updateProject(File json, long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }
    @Step("Delete project")
    public void deleteProject(long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get collaborators")
    public void getCollaborators(long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponses.PROJECTS_TOKEN)
                .pathParam("id", id);
    }
}
