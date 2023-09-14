package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChicken is a Querydsl query type for Chicken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChicken extends EntityPathBase<Chicken> {

    private static final long serialVersionUID = 1818818429L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChicken chicken = new QChicken("chicken");

    public final youddack.app.domain.chicken.domain.common.QBaseEntity _super = new youddack.app.domain.chicken.domain.common.QBaseEntity(this);

    public final StringPath allergy = createString("allergy");

    public final QBrand brand;

    public final StringPath capacity = createString("capacity");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image_url = createString("image_url");

    public final StringPath name = createString("name");

    public final StringPath part = createString("part");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QChicken(String variable) {
        this(Chicken.class, forVariable(variable), INITS);
    }

    public QChicken(Path<? extends Chicken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChicken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChicken(PathMetadata metadata, PathInits inits) {
        this(Chicken.class, metadata, inits);
    }

    public QChicken(Class<? extends Chicken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new QBrand(forProperty("brand")) : null;
    }

}

