package engine.controllers;

import engine.exceptions.ResourceNotFoundException;
import engine.model.Answer;
import engine.model.FeedBack;
import engine.model.Quiz;
import engine.model.User;
import engine.repositories.QuizRepository;
import engine.service.QuizService;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;


    @Autowired
    private QuizService quizService;


    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes(@AuthenticationPrincipal User user) {
        return quizService.getAll();
    }


    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable(value = "id") Long quizId, @AuthenticationPrincipal User user) {
        return quizService.getById(quizId);
    }


    @PostMapping(value = "/api/quizzes")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        quizService.save(quiz, user);
        return quiz;
    }


    @PostMapping(value = "/api/quizzes/{id}/solve")
    public FeedBack answerQuiz(@PathVariable Long id, @RequestBody Answer answer) throws ResourceNotFoundException {
        return quizService.solve(answer, id);
    }


    @DeleteMapping(path = "/api/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable Long id,  @AuthenticationPrincipal User user) {
        return quizService.delete(id, user);
    }


}