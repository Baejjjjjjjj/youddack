package youddack.app.domain.chicken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlavor is a Querydsl query type for Flavor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlavor extends EntityPathBase<Flavor> {

    private static final long serialVersionUID = -1653082154L;

    public static final QFlavor flavor = new QFlavor("flavor");

    public final youddack.app.domain.chicken.domain.common.QBaseEntity _super = new youddack.app.domain.chicken.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QFlavor(String variable) {
        super(Flavor.class, forVariable(variable));
    }

    public QFlavor(Path<? extends Flavor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlavor(PathMetadata metadata) {
        super(Flavor.class, metadata);
    }

}

