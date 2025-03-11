package com.example.upload.domain.post.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 450080477L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.example.upload.global.entity.QBaseTime _super = new com.example.upload.global.entity.QBaseTime(this);

    public final com.example.upload.domain.member.member.entity.QMember author;

    public final ListPath<com.example.upload.domain.post.comment.entity.Comment, com.example.upload.domain.post.comment.entity.QComment> comments = this.<com.example.upload.domain.post.comment.entity.Comment, com.example.upload.domain.post.comment.entity.QComment>createList("comments", com.example.upload.domain.post.comment.entity.Comment.class, com.example.upload.domain.post.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<com.example.upload.domain.post.genFile.entity.PostGenFile, com.example.upload.domain.post.genFile.entity.QPostGenFile> genFiles = this.<com.example.upload.domain.post.genFile.entity.PostGenFile, com.example.upload.domain.post.genFile.entity.QPostGenFile>createList("genFiles", com.example.upload.domain.post.genFile.entity.PostGenFile.class, com.example.upload.domain.post.genFile.entity.QPostGenFile.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath listed = createBoolean("listed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final BooleanPath published = createBoolean("published");

    public final StringPath title = createString("title");

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.example.upload.domain.member.member.entity.QMember(forProperty("author")) : null;
    }

}

