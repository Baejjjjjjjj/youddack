package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChickenFlavor is a Querydsl query type for ChickenFlavor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChickenFlavor extends EntityPathBase<ChickenFlavor> {

    private static final long serialVersionUID = -208556933L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChickenFlavor chickenFlavor = new QChickenFlavor("chickenFlavor");

    public final QChicken chicken;

    public final QFlavor flavor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QChickenFlavor(String variable) {
        this(ChickenFlavor.class, forVariable(variable), INITS);
    }

    public QChickenFlavor(Path<? extends ChickenFlavor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChickenFlavor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChickenFlavor(PathMetadata metadata, PathInits inits) {
        this(ChickenFlavor.class, metadata, inits);
    }

    public QChickenFlavor(Class<? extends ChickenFlavor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chicken = inits.isInitialized("chicken") ? new QChicken(forProperty("chicken"), inits.get("chicken")) : null;
        this.flavor = inits.isInitialized("flavor") ? new QFlavor(forProperty("flavor")) : null;
    }

}

