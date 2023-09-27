package youddack.app.domain.chicken.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;

import java.util.List;

public interface Repository extends JpaRepository<Chicken, Long> {

    Chicken findChickenById(Long chicken_id);

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

    @Query("select c from Chicken c where c.brand.id = :id  and c.id>:chicken_id order by c.id")
    List<Chicken> findChickenListLimitAndBrand(@Param("chicken_id") Long chicken_id, @Param("id")Long id,  Pageable pageable);


    @Query("select c from Chicken c where c.id>:id and c.name like %:name%")
    List<Chicken>findAllByIdAndByChickenName(@Param("id")Long chicken_id,@Param("name") String chicken_name,Pageable pageable);

    @Query(value = "select *, ranks from (select row_number() over (order by price asc) as ranks from chicken)\n" +
            "    as derived_table having ranks > :rank_id order by ranks limit 10",nativeQuery = true)
    List<Long> findRankOrderByPriceAsc(@Param(("rank_id")) Long rank_id);


    @Query(value = "select ranks from (select row_number() over (order by price desc) as ranks from chicken)\n" +
            "    as derived_table having ranks > :rank_id order by ranks limit 10",nativeQuery = true)
    List<Long> findRankOrderByPriceDesc(@Param(("rank_id")) Long rank_id);

    @Query(value = "select ranks from (select row_number() over (order by capacity desc) as ranks from chicken)\n" +
            "    as derived_table having ranks > :rank_id order by ranks limit 10",nativeQuery = true)
    List<Long> findRankOrderByCapacityAsc(@Param(("rank_id")) Long rank_id);


    @Query(value = "select ranks from (select *, row_number() over (order by price asc) as ranks from chicken)" +
            "  as derived_table where derived_table.id = :chicken_id order by ranks limit 10",nativeQuery = true)
    Long findRankByChickenIdAsc(@Param("chicken_id") Long chicken_id);





}
