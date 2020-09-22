package engine.service;

import engine.model.Answer;
import engine.model.FeedBack;
import engine.model.Quiz;
import engine.repositories.QuizRepository;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz getById(String id) {
        Quiz quiz = quizRepository.findById(Integer.parseInt(id)).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return quiz;
    }

    public FeedBack solve(Answer answer, String id) {
        Quiz quiz = getById(id);
        if (answer.getAnswer().equals(quiz.getAnswer())) {
            return new FeedBack(true);
        }

        return new FeedBack(false);
    }


}
