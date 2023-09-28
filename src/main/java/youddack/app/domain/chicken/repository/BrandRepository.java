package youddack.app.domain.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youddack.app.domain.chicken.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findAllById(Long id);
}
