package youddack.app.domain.chicken.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import youddack.app.domain.chicken.domain.*;

import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Repository
public class CustomRepository  {

    private final JPAQueryFactory queryFactory;

    public List<Chicken> selectChickenList(Long chicken_id,String category_name, String part_name, Integer start_price, Integer end_price, Integer sort_id){

        QChicken chicken = QChicken.chicken;

        QChickenCategory chickenCategory = QChickenCategory.chickenCategory;

        QCategory category = QCategory.category;

        BooleanBuilder builder = new BooleanBuilder();

        BooleanBuilder sortBuilder = new BooleanBuilder();

        if(category_name!=""){
            System.out.println("category_name"+category_name);
            builder.and(category.name.eq(category_name));
        }

        if(part_name!=""){
            System.out.println("part_name"+part_name);
            builder.and(chicken.name.eq(part_name));
        }

        if(start_price!=-1&&end_price!=0){
            System.out.println("start_price"+start_price+"end_price"+end_price);
            builder.and(chicken.price.goe(start_price));
            builder.and(chicken.price.loe(end_price));
        }

        if(sort_id==0){

            List<Chicken> chickenList = queryFactory.select(chicken)
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            chicken.id.gt(chicken_id),
                            builder)
                    .orderBy(NumberExpression.random().asc())
                    .limit(10)
                    .fetch();

            return chickenList;

        }
        if(sort_id==1){
            List<Chicken> chickenList = queryFactory.select(chicken)
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            chicken.id.gt(chicken_id),
                            builder)
                    .orderBy(chicken.price.asc())
                    .limit(10)
                    .fetch();

            return chickenList;
        }
        if(sort_id==2){

            List<Chicken> chickenList = queryFactory.select(chicken)
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            chicken.id.gt(chicken_id),
                            builder)
                    .orderBy(chicken.capacity.asc())
                    .limit(10)
                    .fetch();

            return chickenList;
        }

        if(sort_id==3) {
            List<Chicken> chickenList = queryFactory.select(chicken)
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            chicken.id.gt(chicken_id),
                            builder)
                    .orderBy(chicken.price.desc())
                    .limit(10)
                    .fetch();

            return chickenList;
        }

        return null;
    }

    public List<Flavor> SelectFlavorList (Long chicken_id, List<String> flavorList){

        QFlavor flavor = QFlavor.flavor;
        QChickenFlavor chickenFlavor = QChickenFlavor.chickenFlavor;

        BooleanBuilder builder = new BooleanBuilder();

        for (int i = 0; i < flavorList.size();i++){

            System.out.println(flavorList.get(i));
            if(i ==0){

                builder.or(flavor.name.eq(flavorList.get(i)));
            }
            else if(i==1){
                builder.or(flavor.name.eq(flavorList.get(i)));
            } else if (i==2) {builder.or(flavor.name.eq(flavorList.get(i)));;

            }

        }

        List<Flavor> flavorLists = queryFactory.select(flavor)
                .from(chickenFlavor)
                .join(chickenFlavor.flavor, flavor)
                .where((chickenFlavor.flavor.id.eq( flavor.id)),
                        (chickenFlavor.chicken.id.eq(chicken_id))
                                .and(builder)
                )
                .fetch();

        return flavorLists;

    }


}
