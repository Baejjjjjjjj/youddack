package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youddack.app.domain.chicken.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
