package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendationDescription is a Querydsl query type for RecommendationDescription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendationDescription extends EntityPathBase<RecommendationDescription> {

    private static final long serialVersionUID = -326109621L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendationDescription recommendationDescription = new QRecommendationDescription("recommendationDescription");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final QRecommendation recommendation;

    public QRecommendationDescription(String variable) {
        this(RecommendationDescription.class, forVariable(variable), INITS);
    }

    public QRecommendationDescription(Path<? extends RecommendationDescription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendationDescription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendationDescription(PathMetadata metadata, PathInits inits) {
        this(RecommendationDescription.class, metadata, inits);
    }

    public QRecommendationDescription(Class<? extends RecommendationDescription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recommendation = inits.isInitialized("recommendation") ? new QRecommendation(forProperty("recommendation"), inits.get("recommendation")) : null;
    }

}

