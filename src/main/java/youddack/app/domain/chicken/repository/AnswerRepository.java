package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youddack.app.domain.chicken.domain.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long answerId);
}
