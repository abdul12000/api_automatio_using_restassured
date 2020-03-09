@testToRun
Feature:
  Imagine you are building a social network. Starting from simple functionality. Users are now able to make posts and comment on them.
  You are working in the backend team that exposes the service: https://jsonplaceholder.typicode.com/ which has the following endpoints:
  1. Make posts: https://jsonplaceholder.typicode.com/posts
  2. Comment on posts: https://jsonplaceholder.typicode.com/comments
  3. List of users: https://jsonplaceholder.typicode.com/users
  Using Rest-Assured, Cucumber, and Java, create a few scenarios to test this functionality

  ##GET requests:
#  @testToRun
  Scenario Outline: 1. Test that existing posts can be retrieved wih a GET request
    Given Service is up and running
    When i search for "<id>" of a post with a GET method
    Then status code of 200 is returned
    And I should get the correct "<id>", "<title>" and "<body>" returned

    Examples:
      | id | title                              | body                                                                                                                                                                                                  |
      | 6  | dolorem eum magni eos aperiam quia | ut aspernatur corporis harum nihil quis provident sequi\nmollitia nobis aliquid molestiae\nperspiciatis et ea nemo ab reprehenderit accusantium quas\nvoluptate dolores velit et doloremque molestiae |
      | 11  | et ea vero quia laudantium autem | delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi |


#  @testToRun
  Scenario Outline: 2. Test that existing comments can be retreived with a GET request
    Given Service is up and running
    When i search for comment with "<id>" with a GET method
    And status code of 200 is returned for comments
    Then the correct "<id>", "<name>", "<email>" and "<body>" are returned
    Examples:
      | id | name                                      | email                  | body                                                                                                                                                                        |
      | 2  | quo vero reiciendis velit similique earum | Jayne_Kuhic@sydney.com | est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et |

#
  #Task
#Create USer story and write the test for get User request (Use particular users with id =6)



  #### POST requests:
#  @testToRun
  Scenario Outline: 3. Test that new posts can be created with the POST request
    Given Service is up and running
    When I create a new post with the following details "<uId>","<title>" and "<body>",
    Then status code of 201 is returned for created post request
    And i should get the correct "<uId>","<title>" and "<body>" returned

    Examples:
      | uId   | title                        | body                        |
      | 10009 | this is my Test title        | this is my test body        |
      | 10027 | this is my second Test title | this is my second test body |
#  @testToRun
  Scenario Outline: 4. Test that new comments can be created with the POST request
    Given Service is up and running
    When I create a new comment with the following details "<postId>","<name>", "<email>" and "<body>"
    Then status code of 201 is returned for create Comment request
    And i should get the correct "<postId>","<name>", "<email>" and "<body>" returned

    Examples:
      | postId | name                 | email        | body                 |
      | 10009  | this is my Test name | tesemail.com | this is my test body |
      | 10229  | this another Test name | anothertest.com | this is another body |

      #Task
#Create USer story and write the test for post new user request (Use particular users with id =3)



