package com.example.upload.domain.post.post.entity;

import com.example.upload.domain.member.member.entity.Member;
import com.example.upload.domain.post.comment.entity.Comment;
import com.example.upload.domain.post.genFile.entity.PostGenFile;
import com.example.upload.global.entity.BaseTime;
import com.example.upload.global.exception.ServiceException;
import com.example.upload.standard.util.Ut;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Post extends BaseTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    private String content;
    private boolean published;
    private boolean listed;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<PostGenFile> genFiles = new ArrayList<>();

    public PostGenFile addGenFile(String typeCode, String filePath) {

        String originalFileName = Ut.file.getOriginalFileName(filePath);
        String fileExt = Ut.file.getFileExt(filePath);
        String fileName = UUID.randomUUID() + "." + fileExt;
        long fileSize = Ut.file.getFileSize(filePath);

        PostGenFile genFile = PostGenFile.builder()
                .post(this)
                .typeCode(typeCode)
                .filePath(filePath)
                .originalFileName(originalFileName)
                .fileExt(fileExt)
                .fileName(fileName)
                .fileSize(fileSize)
                .build();

        genFiles.add(genFile);
        Ut.file.mv(filePath, genFile.getFilePath());

        return genFile;
    }

    public Comment addComment(Member author, String content) {

        Comment comment = Comment
                .builder()
                .post(this)
                .author(author)
                .content(content)
                .build();

        comments.add(comment);

        return comment;
    }

    public Comment getCommentById(long id) {

        return comments.stream()
                .filter(comment -> comment.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> new ServiceException("404-2", "존재하지 않는 댓글입니다.")
                );
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    public void canModify(Member actor) {
        if (actor == null) {
            throw new ServiceException("401-1", "인증 정보가 없습니다.");
        }

        if (actor.isAdmin()) return;

        if (actor.equals(this.author)) return;

        throw new ServiceException("403-1", "자신이 작성한 글만 수정 가능합니다.");
    }

    public void canDelete(Member actor) {
        if (actor == null) {
            throw new ServiceException("401-1", "인증 정보가 없습니다.");
        }

        if (actor.isAdmin()) return;

        if (actor.equals(this.author)) return;

        throw new ServiceException("403-1", "자신이 작성한 글만 삭제 가능합니다.");
    }

    public void canRead(Member actor) {
        if (actor.equals(this.author)) return;
        if (actor.isAdmin()) return;

        throw new ServiceException("403-1", "비공개 설정된 글입니다.");
    }

    public Comment getLatestComment() {
        return comments
                .stream()
                .sorted(Comparator.comparing(Comment::getId).reversed())
                .findFirst()
                .orElseThrow(
                        () -> new ServiceException("404-2", "존재하지 않는 댓글입니다.")
                );
    }

    public boolean getHandleAuthority(Member actor) {
        if (actor == null) return false;
        if (actor.isAdmin()) return true;

        return actor.equals(this.author);
    }
}
