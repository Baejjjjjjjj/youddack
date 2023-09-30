package youddack.app.domain.chicken.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import youddack.app.domain.chicken.domain.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Repository
public class CustomRepository  {

    private final JPAQueryFactory queryFactory;

    final youddack.app.domain.chicken.repository.Repository repository;

    public List<Chicken> selectChickenList(Long chicken_id,String category_name, String part_name, Integer start_price, Integer end_price, Integer sort_id, List<String> flavorList, Long rank_id){

        System.out.println(sort_id);

        QChicken chicken = QChicken.chicken;

        QChickenCategory chickenCategory = QChickenCategory.chickenCategory;

        QCategory category = QCategory.category;

        QChickenFlavor chickenFlavor = QChickenFlavor.chickenFlavor;

        QFlavor flavor = QFlavor.flavor;

        BooleanBuilder builder = new BooleanBuilder();

        BooleanBuilder sortBuilder = new BooleanBuilder();


        if(category_name!=""){
            System.out.println("category_name"+category_name);
            builder.and(category.name.eq(category_name));
        }

        if(flavorList!=null){
            System.out.println("맛 필터링 들어간다 확마");
            for (int i = 0; i < flavorList.size();i++){

                System.out.println("맛 필터링:"+ flavorList.get(i));
                if(i ==0){
                    builder.and(flavor.name.eq(flavorList.get(i)));
                }
                else if(i==1){
                    builder.or(flavor.name.eq(flavorList.get(i)));
                }else if (i==2) {builder.or(flavor.name.eq(flavorList.get(i)));;

                }

            }

        }

        if(part_name!=""){
            System.out.println("part_name"+part_name);
            builder.and(chicken.part.eq(part_name));
        }

        if(start_price!=-1&&end_price!=0){
            System.out.println("start_price"+start_price+"end_price"+end_price);
            builder.and(chicken.price.goe(start_price));
            builder.and(chicken.price.loe(end_price));
        }


        if(sort_id==0){
            //querydsl 은 예전버전에서는 연관관계없인 조인을 허락하지 않았다.


            List<Chicken> chickenList = queryFactory.select(chickenCategory.chicken).distinct()
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .innerJoin(chickenFlavor).on(chickenCategory.chicken.id.eq(chickenFlavor.chicken.id))
                    .innerJoin(chickenFlavor.flavor).on(chickenFlavor.flavor.id.eq(flavor.id))
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            builder,
                            chicken.id.gt(chicken_id)
                            )
                    .groupBy(chicken.name)
                    .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                     .limit(10).fetch().stream().toList();


            return chickenList;

        }


        if(sort_id==1){
            System.out.println("가격 낮은 순입니다");

            NumberExpression<Integer> rankSubquery = Expressions.numberPath(Integer.class,
                    "(SELECT COUNT(*) FROM Chicken as c2 WHERE c2.price < " + chicken.price + ")");

           // Integer rank = repository.findRankOrderByPriceAsc()
            List<Chicken> chickenList = queryFactory
                    .select(chickenCategory.chicken
                    ).distinct()
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id), // chicken_id 사용
                            rankSubquery.gt(rank_id)
                            ,builder
                    )
                    .groupBy(chicken.name)
                    .orderBy(chicken.price.asc())
                    .limit(10)
                    .fetch();
            System.out.println("rankSubquery:"+rankSubquery);

            return chickenList;
        }
        if(sort_id==2){


            List<Chicken> chickenList = queryFactory.select(chickenCategory.chicken).distinct()
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            builder)
                    .groupBy(chicken.name)
                    .orderBy(chicken.capacity.desc(), chicken.id.asc())
                    .offset(rank_id)
                    .limit(10).stream().toList();


            return chickenList;
        }

        if(sort_id==3) {

            NumberExpression<Integer> rankSubquery = Expressions.numberPath(Integer.class,
                    "(SELECT COUNT(*) FROM Chicken as c2 WHERE c2.price > " + chicken.price + ")");

            List<Chicken> chickenList = queryFactory.select(chickenCategory.chicken).distinct()
                    .from(chickenCategory)
                    .join(chickenCategory.chicken, chicken).limit(10)
                    .join(chickenCategory.category, category)
                    .where(chickenCategory.chicken.id.eq(chicken.id),
                            chickenCategory.category.id.eq(category.id),
                            rankSubquery.gt(rank_id),
                            builder)
                    .groupBy(chicken.name)
                    .orderBy(chicken.price.desc())
                    .stream().toList();

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
                .stream().toList();

        return flavorLists;

    }


}
