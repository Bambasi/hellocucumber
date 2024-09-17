Feature: what day it is?
  everyone wants to know what day it is

  Scenario Outline: find the exact day
    Given today is "<day>"
    When I ask what day it is
    Then I should be told "<answer>"

    Examples:
      | day            | answer |
      | Monday         | Yes    |
      | Tuesday        | Yes1   |
      | Wednesday      | Yes2   |
      | Thursday       | Yes3   |
      | Friday         | Yes4   |
      | anything else! | Nope   |



