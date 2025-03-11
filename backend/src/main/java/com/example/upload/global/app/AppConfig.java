package com.example.upload.global.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Getter
    public static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        AppConfig.objectMapper = objectMapper;
    }

    public static String getSiteFrontUrl() {
        return "http://localhost:3000";
    }

    public static boolean isNotProd() {
        return true;
    }

    public static String getTempDirPath() {
        return System.getProperty("java.io.tmpdir");
    }

    @Getter
    private static Tika tika;

    @Autowired
    public void setTika(Tika tika) {
        AppConfig.tika = tika;
    }

}
