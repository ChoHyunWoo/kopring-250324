package com.example.upload.domain.post.genFile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostGenFile is a Querydsl query type for PostGenFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostGenFile extends EntityPathBase<PostGenFile> {

    private static final long serialVersionUID = -973297149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostGenFile postGenFile = new QPostGenFile("postGenFile");

    public final com.example.upload.global.entity.QBaseTime _super = new com.example.upload.global.entity.QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath fileExt = createString("fileExt");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath originalFileName = createString("originalFileName");

    public final com.example.upload.domain.post.post.entity.QPost post;

    public final StringPath typeCode = createString("typeCode");

    public QPostGenFile(String variable) {
        this(PostGenFile.class, forVariable(variable), INITS);
    }

    public QPostGenFile(Path<? extends PostGenFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostGenFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostGenFile(PathMetadata metadata, PathInits inits) {
        this(PostGenFile.class, metadata, inits);
    }

    public QPostGenFile(Class<? extends PostGenFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new com.example.upload.domain.post.post.entity.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

