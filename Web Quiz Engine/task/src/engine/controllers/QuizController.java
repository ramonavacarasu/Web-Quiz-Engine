package engine.controllers;

import engine.exceptions.ResourceNotFoundException;
import engine.model.*;
import engine.repositories.QuizCompletedRepository;
import engine.repositories.QuizRepository;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Validated
@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCompletedRepository quizCompletedRepository;


    @Autowired
    private QuizService quizService;

    @GetMapping("/api/quizzes")
    public Page<Quiz> getQuizzes(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 3);

        return quizRepository.findAll(pageable);
    }

    @GetMapping("/api/quizzes/completed")
    public Page<QuizCompleted> getCompletedQuizzes(@RequestParam int page, @AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(page, 10);

        return quizCompletedRepository.findAllCompletedQuizzesWithPagination(user.getId(), pageable);
    }

    private void createQuizCompleted(@AuthenticationPrincipal User user, Quiz quiz) {
        ArrayList<QuizCompleted> completions = new ArrayList<QuizCompleted>();
        completions.addAll(quiz.getQuizCompleted());

        QuizCompleted quizCompleted = new QuizCompleted();
        quizCompleted.setQuiz(quiz);
        quizCompleted.setAuthor(user);
        quizCompleted.setCompletedAt(LocalDateTime.now());
        quizCompleted = quizCompletedRepository.save(quizCompleted);

        completions.add(quizCompleted);
        quiz.setQuizCompleted(completions);
        quizRepository.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable(value = "id") String quizId, @AuthenticationPrincipal User user) {
        return quizService.getById(quizId);
    }


    @PostMapping(value = "/api/quizzes")
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        quiz.setUser(user);
        quiz = quizRepository.save(quiz);
        return ResponseEntity.ok(quiz);
    }


    @PostMapping(value = "/api/quizzes/{id}/solve")
    public FeedBack answerQuiz(@PathVariable String id, @RequestBody Answer answer) throws ResourceNotFoundException {
        return quizService.solve(answer, id);
    }


    @DeleteMapping(path = "/api/quizzes/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable String id,  @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = getQuiz(id);
        if (quiz.isPresent()) {
            if (quiz.get().getUser().equals(user)) {
                quizRepository.delete(quiz.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Optional<Quiz> getQuiz(String id) {
        var idValue = Integer.parseInt(id);
        return quizRepository.findById(idValue);
    }
}