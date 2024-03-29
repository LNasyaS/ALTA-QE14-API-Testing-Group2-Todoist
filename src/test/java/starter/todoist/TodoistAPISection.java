package starter.todoist;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.Constants;

import java.io.File;

public class TodoistAPISection {
    public static String CREATE_SECTION = Constants.BASE_URL + "/sections";
    public static String GET_SECTION = Constants.BASE_URL + "/sections/{id}";
    public static String GET_ALL_SECTION = Constants.BASE_URL +"/sections?project_id={id}";
    public static String UPDATE_SECTION = Constants.BASE_URL +"/sections/{id}";
    public static String DELETE_SECTION = Constants.BASE_URL +"/sections/{id}";


    @Step("Create Section")
    public void createSection(File json) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get section")
    public void getSection(long id) {
        SerenityRest.given()
                .header("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get single section with invalid id")
    public void getSectionInvalid(String id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get all section")
    public void getAllSection(long id) {
        SerenityRest.given()
                .header("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id);
    }

    @Step("Get all section with invalid project id")
    public void getAllSectionInvalid(String id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id);
    }

    @Step("Update section")
    public void updateSection(File json, long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Delete section")
    public void deleteSection(long id) {
        SerenityRest.given()
                .headers("Authorization", TodoistResponsesSection.SECTION_TOKEN)
                .pathParam("id", id);
    }

}
