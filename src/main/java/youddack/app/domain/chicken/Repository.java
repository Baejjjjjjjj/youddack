package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;

import java.util.List;

public interface Repository extends JpaRepository<Chicken, Long> {

    @Query("select a from ChickenCategory c LEFT OUTER JOIN Category a ON c.category.id = a.id where c.chicken.id = :chicken_id")
    List<Category> findByChickenIdJoin(@Param("chicken_id") Long chickenId);

    @Query("select a from ChickenFlavor c LEFT OUTER JOIN Flavor a ON c.flavor.id = a.id where c.chicken.id = :chicken_id")
    List<Flavor> findByChickenIdJoinFlavor(@Param("chicken_id")Long chickenId);
}
