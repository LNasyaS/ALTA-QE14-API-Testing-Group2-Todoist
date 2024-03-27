Feature: Projects
  all projects feature from todoist website

# Get all project
  Scenario: Get all projects
    Given Get all projects
    When Send request get all projects
    Then Status code should be 200
    And Response body should be id "2330759743" and name "Inbox"
    And Validate json schema "GetAllProjectsJsonSchema.json"

# Create project
# Positive
  Scenario Outline: Create a new project with valid json
    Given Post create new project with valid "<json>"
    When Send request create project
    Then Status code should be 200
    And Response body name should be "<name>"
    And Validate json schema "CreateProjectValidJsonSchema.json"
    Examples:
      | json                | name          |
      | CreateProject.json  | Shopping List |
      | CreateProject2.json | Bootcamp      |

# Negative
  Scenario: Create a new project with invalid json
    Given Post create new project with invalid "CreateProjectInvalid.json"
    When Send request create project
    Then Status code should be 400
    And Response body error should be "Name must be provided for the project creation"

# Get project
# Positive
  Scenario: Get a project
    Given Get project with valid id 2330859184
    When Send request get project
    Then Status code should be 200
    And Response body project should be id "2330859184" and name "Bootcamp"
    And Validate json schema "GetProjectJsonSchema.json"

# Negative
  Scenario: Get a project with id that does not exist
    Given Get project with invalid id "2203306141"
    When Send request get project
    Then Status code should be 404
    And Response body error should be "Project not found"

  Scenario Outline: Get a project with invalid
    Given Get project with invalid id "<id>"
    When Send request get project
    Then Status code should be 400
    And Response body error should be "Invalid argument value"
    Examples:
      | id           |
      | qwert1234    |
      | !@#$%%^&*()- |

# Update a project
  Scenario: Update a project
    Given Post update with valid "UpdateProject.json" and valid id 2330859182
    When Send request update project
    Then Status code should be 200
    And Response body project should be id "2330859182" and name "Things to buy"
    And Validate json schema "UpdateProjectJsonSchema.json"

# Delete project
  Scenario: Delete a project
    Given Delete user with valid id 2330859125
    When Send request delete user
    Then Status code should be 204


# Get all collaborators
  Scenario: Get all collaborators
    Given Get project collaborators with id 2330859184
    When Send request get collaborators
    Then Status code should be 200
    And Response body id should be "48713640" and name "LNasya Syafitrie"
    And Validate json schema "GetCollaboratorsJsonSchema.json"