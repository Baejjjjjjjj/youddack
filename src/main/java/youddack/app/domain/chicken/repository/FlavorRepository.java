package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import youddack.app.domain.chicken.domain.Flavor;

import java.util.List;

public interface FlavorRepository extends JpaRepository<Flavor, Long> {
    @Query("select f from Flavor f LEFT JOIN ChickenFlavor cf On f.id = cf.flavor.id where cf.chicken.id = 3")
    List<Flavor> findFlavorWithChickenFlavorById(Long id);
}
