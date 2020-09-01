package engine.exceptions;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuizExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Quiz Not Found");
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class RequiredException extends RuntimeException {
    public RequiredException(String message) {
        super(message);
    }
}