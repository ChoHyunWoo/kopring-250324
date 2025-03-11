package com.example.upload.domain.post.genFile.entity;

import com.example.upload.domain.post.post.entity.Post;
import com.example.upload.global.app.AppConfig;
import com.example.upload.global.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostGenFile extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int fileNo;
    private String typeCode;
    private String filePath;
    private String originalFileName;
    private String metadata;
    private String fileDateDir;
    private String fileExt;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private String fileName;
    private long fileSize;

    public String getFilePath() {
        return AppConfig.getGenFileDirPath() + "/" + getModelName() + "/" + typeCode + "/" + fileDateDir + "/" + fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (id != null) return super.equals(o);

        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostGenFile that = (PostGenFile) o;
        return fileNo == that.fileNo && Objects.equals(typeCode, that.typeCode);
    }

    @Override
    public int hashCode() {
        if (id != null) return super.hashCode();

        return Objects.hash(super.hashCode(), typeCode, fileNo);
    }
}
