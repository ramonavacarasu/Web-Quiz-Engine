package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "quizzes_completed")
@AllArgsConstructor
@NoArgsConstructor
public class QuizCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @JsonIgnore
    @ManyToOne
    private Quiz quiz;

    @Column
    private LocalDateTime completedAt;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    private User author;
}
