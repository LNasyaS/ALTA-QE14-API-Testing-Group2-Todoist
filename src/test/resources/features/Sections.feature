@Section
Feature: Section
  all section feature from todoist website

  @CreateSection
    #Positive
  Scenario: Create section with valid json
    Given Post Create section with valid json "CreateSection.json"
    When Send request post create section
    Then Status code should be 200
    And Validate json schema "CreateSectionJsonSchema.json"

  @CreateSection
    #Negative
  Scenario Outline: Create section with invalid json
    Given Post Create section with invalid json "<json>"
    When Send request post create section
    Then Status code should be 400
    And Response body error should be contain "<error>"
    Examples:
      | json                  | error                 |
      | InvalidProjectId.json | project_id is invalid |
      | ProjectIdNull.json    | project_id is invalid |
      | SectionNameNull.json  | Name is required      |

  @GetSingleSection
    Scenario: Get single section with valid section id
      Given Get single section with valid section id 151571501
      When Send request get single section
      Then Status code should be 200
      And Response body section should be id "151571501" and name "Snacks"
      And Validate json schema "SingleSectionJsonSchema.json"

  @GetSingleSection
    Scenario: Get single section with invalid section id
      Given Get single section with invalid section id "sectionid"
      When Send request get single section
      Then Status code should be 404
      And Response body error should be contain "Section not found"

  @GetAllSection
    Scenario: Get all section with valid project id
    Given Get all section with valid project id 2330869235
    When Send request get all section
    Then Status code should be 200
    And Validate json schema "AllSectionJsonSchema.json"

  @GetAllSection
    Scenario: Get all section with invalid project id
    Given Get all section with invalid project id "projectid"
    When Send request get all section
    Then Status code should be 400
    And Response body error should be contain "project_id is invalid"

  @UpdateSection
  Scenario: Update a section
    Given Post update section with valid json "UpdateSection.json" and valid section id 151571452
    When Send request post update section
    Then Status code should be 200
    And Validate json schema "UpdateSectionJsonSchema.json"

  @DeleteSection
  Scenario: Delete a section
    Given Delete section with valid section id 151571518
    When Send request delete section
    Then Status code should be 204





