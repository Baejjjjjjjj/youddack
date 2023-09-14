package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youddack.app.domain.chicken.domain.RecommendationDescription;

import java.util.List;

public interface RecommendationDescriptionsRepository extends JpaRepository<RecommendationDescription, Long> {

    List<RecommendationDescription> findByRecommendationId(Long id);
}
