package nettee.blolet.article.rdb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDraftEntity is a Querydsl query type for DraftEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDraftEntity extends EntityPathBase<DraftEntity> {

    private static final long serialVersionUID = 1053218269L;

    public static final QDraftEntity draftEntity = new QDraftEntity("draftEntity");

    public final nettee.jpa.support.QSnowflakeBaseTimeEntity _super = new nettee.jpa.support.QSnowflakeBaseTimeEntity(this);

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final NumberPath<Long> blogId = createNumber("blogId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    public final NumberPath<Long> entryBlockId = createNumber("entryBlockId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath path = createString("path");

    public final EnumPath<nettee.blolet.article.rdb.entity.type.DraftEntityStatus> status = createEnum("status", nettee.blolet.article.rdb.entity.type.DraftEntityStatus.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    public QDraftEntity(String variable) {
        super(DraftEntity.class, forVariable(variable));
    }

    public QDraftEntity(Path<? extends DraftEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDraftEntity(PathMetadata metadata) {
        super(DraftEntity.class, metadata);
    }

}

