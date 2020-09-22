package engine.repositories;

import engine.model.QuizCompleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletedRepository extends JpaRepository<QuizCompleted, Integer> {

    @Query("SELECT c FROM QuizCompleted c WHERE c.author.id = ?1 ORDER BY c.completedAt DESC")
    Page<QuizCompleted> findAllCompletedQuizzesWithPagination(int id, Pageable pageable);

}