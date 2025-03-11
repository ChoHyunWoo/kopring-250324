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

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostGenFile extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

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
}
