package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import jnr.constants.Constant;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import starter.todoist.TodoistAPISection;
import starter.todoist.TodoistResponsesSection;
import starter.utils.Constants;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class SectionsStepDef {
    @Steps
    TodoistAPISection todoistAPISection;
    String textResponse = "";

    //Scenario 1 
    @Given("Post Create section with valid json {string}" )
    public void createSectionWithValidJson (String json) {
        File jsonFile = new File(Constants.REQ_BODY+json);
        todoistAPISection.createSection(jsonFile);
    }

    @When("Send request post create section")
    public void sendRequestPostCreateSection() {
        Response response = SerenityRest.when()
                .post(TodoistAPISection.CREATE_SECTION);
        textResponse = response.asString();
    }

    //Scenario 2
    @Given("Post Create section with invalid json {string}")
    public void postCreateSectionWithInvalidJson(String json) {
        File jsonFile = new File(Constants.REQ_BODY+json);
        todoistAPISection.createSection(jsonFile);
    }

    @And("Response body error should be contain {string}")
    public void responseBodyErrorShouldBeContain(String error) {
        assertThat(textResponse, equalTo(error));
    }

   //Scenario 3
    @Given("Get single section with valid section id {long}")
    public void getSingleSectionWithValidSectionId(long id) {
        todoistAPISection.getSection(id);
    }

    @When("Send request get single section")
    public void sendRequestGetSingleSection() {
        Response response = SerenityRest.when()
                .get(TodoistAPISection.GET_SECTION);
        textResponse = response.asString();
    }

    @And("Response body section should be id {string} and name {string}")
    public void responseBodySectionShouldBeIdAndName(String id, String name) {
        SerenityRest.and()
                .body(TodoistResponsesSection.SECTION_ID, equalTo(id))
                .body(TodoistResponsesSection.SECTION_NAME, equalTo(name));
    }

    //Scenario 4
    @Given("Get single section with invalid section id {string}")
    public void getSingleSectionWithInvalidSectionId(String id) {
        todoistAPISection.getSectionInvalid(id);
    }

    //Scenario 5
    @Given("Get all section with valid project id {long}")
    public void getAllSectionWithValidProjectId(long id) {
        todoistAPISection.getAllSection(id);
    }

    @When("Send request get all section")
    public void sendRequestGetAllSection() {
        Response response = SerenityRest.when()
                .get(TodoistAPISection.GET_ALL_SECTION);
        textResponse = response.asString();
    }

    //Scenario 6
    @Given("Get all section with invalid project id {string}")
    public void getAllSectionWithInvalidProjectId(String id) {
        todoistAPISection.getAllSectionInvalid(id);
    }

    //Scenario 7
    @Given("Post update section with valid json {string} and valid section id {long}")
    public void postUpdateSectionWithValidJsonAndValidSectionId(String json, long id) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        todoistAPISection.updateSection(jsonFile, id);
    }

    @When("Send request post update section")
    public void sendRequestPostUpdateSection() {
        SerenityRest.when()
                .post(TodoistAPISection.UPDATE_SECTION);
    }

    //Scenario 8
    @Given("Delete section with valid section id {long}")
    public void deleteSectionWithValidSectionId(long id) {
        todoistAPISection.deleteSection(id);
    }

    @When("Send request delete section")
    public void sendRequestDeleteSection() {
        SerenityRest.when()
                .delete(TodoistAPISection.DELETE_SECTION);
    }
}
