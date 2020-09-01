Web Quiz Engine
It is an engine for creating/solving/deleting quizzes by multiple authorized (HTTP Basic) users. This project built on REST principles and uses Spring Boot framework and its embedded H2 database for storing quizzes and users. JSON is used for transmitting objects between a client and a server.

API:

/api
    /quizzes:
    POST: Create a quiz.
    GET: Get all quizzes.
      /{id}:
      GET: Get quiz by id.
      DELETE: Delete quiz by user's id.
        /solve:
        POST: Solve quiz with recieved quiz's id.
      /completed:
      GET: Get all quizzes, solved by currently authorized user.
    /register:
    POST: Register a user.
