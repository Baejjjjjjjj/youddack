package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendation is a Querydsl query type for Recommendation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendation extends EntityPathBase<Recommendation> {

    private static final long serialVersionUID = -708483855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendation recommendation = new QRecommendation("recommendation");

    public final youddack.app.domain.chicken.domain.common.QBaseEntity _super = new youddack.app.domain.chicken.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image_url = createString("image_url");

    public final StringPath name = createString("name");

    public final QBrand recommend_brand;

    public final StringPath recommend_chicken1_image_url = createString("recommend_chicken1_image_url");

    public final StringPath recommend_chicken1_name = createString("recommend_chicken1_name");

    public final StringPath recommend_chicken2_image_url = createString("recommend_chicken2_image_url");

    public final StringPath recommend_chicken2_name = createString("recommend_chicken2_name");

    public final StringPath recommend_chicken3_image_url = createString("recommend_chicken3_image_url");

    public final StringPath recommend_chicken3_name = createString("recommend_chicken3_name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QRecommendation(String variable) {
        this(Recommendation.class, forVariable(variable), INITS);
    }

    public QRecommendation(Path<? extends Recommendation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendation(PathMetadata metadata, PathInits inits) {
        this(Recommendation.class, metadata, inits);
    }

    public QRecommendation(Class<? extends Recommendation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recommend_brand = inits.isInitialized("recommend_brand") ? new QBrand(forProperty("recommend_brand")) : null;
    }

}

