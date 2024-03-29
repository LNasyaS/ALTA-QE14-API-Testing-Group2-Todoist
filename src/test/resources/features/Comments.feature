Feature: Comments
  Scenario: Create New Comments
    Given Create a new comments with valid json "AddComments.json" and valid id 7833957182
    When Send request post comment
    Then Status code should be 200
    And Validate json schema "PostCommentJsonSchema.json"

  Scenario: Get Valid Comment
    Given Get all comments with task id 7832077679
    When Send request get all comments
    Then Status code should be 200
    And Validate json schema "GetAllCommentJsonSchema.json"
    And Response body should be show task id "7832077679"

  Scenario: Get a Comment with id
    Given Get a comment with id 3496281613
    When Send request get a comments
    Then Status code should be 200
    And Validate json schema "GetACommentWithIDJsonSchema.json"
    And Response body should be show id "3496281613"

  Scenario: Update a comment
    Given Update a comment with valid json "UpdateComment.json" and valid id 3496281613
    When Send request update a comment
    Then Status code should be 200
    And Validate json schema "UpdateComment.json"
    And Response body should be show id "3496281613"

  Scenario: Delete a comment
    Given Delete a comment with valid id 3496281366
    When Send request delete a comment
    Then Status code should be 204

  Scenario: Get a comment with invalid id
    Given Get a comment with invalid id 2992679862
    When Send request get a comments
    Then Status code should be 404
    And Response body error should be "Comment not found"


  Scenario: Get All comment with invalid task id
    Given Get all comment with invalid task id "srretetet"
    When Send request get all comments
    Then Status code should be 400
    And Response body error should be "task_id is invalid"

  Scenario: Create new comment with invalid id
    Given Create new comment with valid json "AddInvalidComment.json" and invalid task id "srretetet"
    When Send request post comment
    Then Status code should be 400
    And Response body error should be "task_id is invalid"


  Scenario: Update Comment with invalid id
    Given Update a comment with valid json "UpdateInvalid.json" and invalid id "srretetet"
    When Send request update a comment
    Then Status code should be 400
    And Response body error should be "comment_id is invalid"
