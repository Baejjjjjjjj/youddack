package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChickenCategory is a Querydsl query type for ChickenCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChickenCategory extends EntityPathBase<ChickenCategory> {

    private static final long serialVersionUID = -137802853L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChickenCategory chickenCategory = new QChickenCategory("chickenCategory");

    public final QCategory category;

    public final QChicken chicken;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QChickenCategory(String variable) {
        this(ChickenCategory.class, forVariable(variable), INITS);
    }

    public QChickenCategory(Path<? extends ChickenCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChickenCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChickenCategory(PathMetadata metadata, PathInits inits) {
        this(ChickenCategory.class, metadata, inits);
    }

    public QChickenCategory(Class<? extends ChickenCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.chicken = inits.isInitialized("chicken") ? new QChicken(forProperty("chicken"), inits.get("chicken")) : null;
    }

}

