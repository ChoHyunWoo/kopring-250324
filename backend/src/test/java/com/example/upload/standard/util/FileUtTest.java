package com.example.upload.standard.util;

import com.example.upload.global.app.AppConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtTest {

    @Test
    public void t1() {
        String newFilePath = Ut.file.downloadByHttp("https://picsum.photos/id/237/200/300", AppConfig.getTempDirPath());

        // newFilePath 의 확장자가 jpg 인지 확인
        assertThat(newFilePath).endsWith(".jpg");

        Ut.file.delete(newFilePath);
    }
}