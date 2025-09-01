package nettee.blolet.article.rdb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDraftImageEntity is a Querydsl query type for DraftImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDraftImageEntity extends EntityPathBase<DraftImageEntity> {

    private static final long serialVersionUID = 550699876L;

    public static final QDraftImageEntity draftImageEntity = new QDraftImageEntity("draftImageEntity");

    public final nettee.jpa.support.QSnowflakeBaseEntity _super = new nettee.jpa.support.QSnowflakeBaseEntity(this);

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imageUrl = createString("imageUrl");

    public QDraftImageEntity(String variable) {
        super(DraftImageEntity.class, forVariable(variable));
    }

    public QDraftImageEntity(Path<? extends DraftImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDraftImageEntity(PathMetadata metadata) {
        super(DraftImageEntity.class, metadata);
    }

}

