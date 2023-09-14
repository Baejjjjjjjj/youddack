package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Pageable;
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

    @Query("select a from ChickenCategory c LEFT OUTER JOIN Category a ON c.category.id = a.id where c.chicken.name = :chicken_name")
    Chicken findByChickenName(@Param("chicken_name") String chicken_name);

    @Query("select a from ChickenFlavor c LEFT OUTER JOIN Flavor a ON c.flavor.id = a.id where c.chicken.id = :chicken_id")
    List<Flavor> findByChickenIdJoinFlavor(@Param("chicken_id")Long chickenId);

    @Query("select c from Chicken c where c.id>:chicken_id order by c.id")
    List<Chicken> findChickenListLimit(@Param("chicken_id") Long chicken_id, Pageable pageable);

    @Query("select c from Chicken c left JOIN Brand b On c.brand.id = b.id where c.id>:chicken_id order by c.id")
    List<Chicken> findChickenListWithBrandLimit(@Param("chicken_id") Long chicken_id, Pageable pageable);

}
