Feature: song service possibilities

  Scenario: save song's data
    When song's data saved
      | name     | artist            | album  | length | year | resourceId |
      | Levitate | Twenty One Pilots | Trench | 02:25  | 2018 | 1          |
    Then POST response code is 201

  Scenario: get song's data by identifier
    When GET request sent songs/1
    Then GET response code is 200
    And song's data returned with resourceId 1
