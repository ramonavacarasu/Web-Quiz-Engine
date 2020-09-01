package engine.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Answer {

    private List<Integer> answer;

    public Answer() {
        answer = new ArrayList<>();
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    @Override
    public int hashCode() {
        return answer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Answer && this.equals(obj);
    }

}