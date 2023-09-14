package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youddack.app.domain.chicken.domain.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
