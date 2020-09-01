package engine.service;

import engine.model.Answer;
import engine.model.FeedBack;
import engine.model.Quiz;
import engine.model.User;
import engine.repositories.QuizRepository;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz getById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return quiz;
    }


    public void save(Quiz quiz, User user) {
        Long max = maxId();
        quiz.setId(max == null ? 0 : max + 1);
        quiz.setUser(user);
        //user.setQuizzes().add(quiz);
        quizRepository.save(quiz);
    }


    public FeedBack solve(Answer answer, Long id) {
        Quiz quiz = getById(id);
        if (answer.getAnswer().equals(quiz.getAnswer())) {
            return new FeedBack(true);
        }

        return new FeedBack(false);
    }

    public ResponseEntity delete(Long id, User user) {
        Quiz quiz = getById(id);
        User userNameQuiz = quiz.getUser();

        if (!user.getUsername().equals(userNameQuiz.getUsername())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        quizRepository.delete(quiz);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public Long maxId() {
        return quizRepository.maxId();
    }
}
