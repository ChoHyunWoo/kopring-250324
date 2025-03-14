package com.example.upload.domain.post.genFile.controller;


import com.example.upload.domain.member.member.service.MemberService;
import com.example.upload.domain.post.genFile.entity.PostGenFile;
import com.example.upload.domain.post.post.entity.Post;
import com.example.upload.domain.post.post.service.PostService;
import com.example.upload.global.app.AppConfig;
import com.example.upload.standard.util.Ut;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class ApiV1PostGenFileControllerTest {
    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("다건 조회")
    void t1() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        get("/api/v1/posts/1/genFiles")
                )
                .andDo(print());

        resultActions
                .andExpect(handler().handlerType(ApiV1PostGenFileController.class))
                .andExpect(handler().methodName("items"))
                .andExpect(status().isOk());

        List<PostGenFile> postGenFiles = postService
                .getItem(1).get().getGenFiles();

        for (int i = 0; i < postGenFiles.size(); i++) {
            PostGenFile postGenFile = postGenFiles.get(i);
            resultActions
                    .andExpect(jsonPath("$[%d].id".formatted(i)).value(postGenFile.getId()))
                    .andExpect(jsonPath("$[%d].createDate".formatted(i)).value(Matchers.startsWith(postGenFile.getCreatedDate().toString().substring(0, 20))))
                    .andExpect(jsonPath("$[%d].modifyDate".formatted(i)).value(Matchers.startsWith(postGenFile.getModifiedDate().toString().substring(0, 20))))
                    .andExpect(jsonPath("$[%d].postId".formatted(i)).value(postGenFile.getPost().getId()))
                    .andExpect(jsonPath("$[%d].typeCode".formatted(i)).value(postGenFile.getTypeCode()))
                    .andExpect(jsonPath("$[%d].fileExtTypeCode".formatted(i)).value(postGenFile.getFileExtTypeCode()))
                    .andExpect(jsonPath("$[%d].fileExtType2Code".formatted(i)).value(postGenFile.getFileExtType2Code()))
                    .andExpect(jsonPath("$[%d].fileSize".formatted(i)).value(postGenFile.getFileSize()))
                    .andExpect(jsonPath("$[%d].fileNo".formatted(i)).value(postGenFile.getFileNo()))
                    .andExpect(jsonPath("$[%d].fileExt".formatted(i)).value(postGenFile.getFileExt()))
                    .andExpect(jsonPath("$[%d].fileDateDir".formatted(i)).value(postGenFile.getFileDateDir()))
                    .andExpect(jsonPath("$[%d].originalFileName".formatted(i)).value(postGenFile.getOriginalFileName()))
                    .andExpect(jsonPath("$[%d].downloadUrl".formatted(i)).value(postGenFile.getDownloadUrl()))
                    .andExpect(jsonPath("$[%d].publicUrl".formatted(i)).value(postGenFile.getPublicUrl()))
                    .andExpect(jsonPath("$[%d].fileName".formatted(i)).value(postGenFile.getFileName()));
        }
    }

    @Test
    @DisplayName("새 파일 등록")
    @WithUserDetails("user2")
    void t2() throws Exception {
        String newFilePath = Ut.file.downloadByHttp("https://picsum.photos/id/237/200/300", AppConfig.getTempDirPath());

        ResultActions resultActions = mvc
                .perform(
                        multipart("/api/v1/posts/9/genFiles/" + PostGenFile.TypeCode.attachment)
                                .file(new MockMultipartFile("file", "300.jpg", "image/jpeg", new FileInputStream(newFilePath)))
                )
                .andDo(print());

        Post post = postService.getItem(9).get();
        System.out.println(post.getGenFiles().size());
        List<PostGenFile> genFiles = post.getGenFiles();

        resultActions
                .andExpect(handler().handlerType(ApiV1PostGenFileController.class))
                .andExpect(handler().methodName("makeNewFile"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("201-1"))
                .andExpect(jsonPath("$.msg").value(Matchers.containsString("번 파일이 생성되었습니다.")))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.createDate").isString())
                .andExpect(jsonPath("$.data.modifyDate").isString())
                .andExpect(jsonPath("$.data.postId").value(9))
                .andExpect(jsonPath("$.data.typeCode").value(PostGenFile.TypeCode.attachment.name()))
                .andExpect(jsonPath("$.data.fileExtTypeCode").value("img"))
                .andExpect(jsonPath("$.data.fileExtType2Code").value("jpg"))
                .andExpect(jsonPath("$.data.fileSize").isNumber())
                .andExpect(jsonPath("$.data.fileNo").value(1))
                .andExpect(jsonPath("$.data.fileExt").value("jpg"))
                .andExpect(jsonPath("$.data.fileDateDir").isString())
                .andExpect(jsonPath("$.data.originalFileName").value("300.jpg"))
                .andExpect(jsonPath("$.data.downloadUrl").isString())
                .andExpect(jsonPath("$.data.publicUrl").isString())
                .andExpect(jsonPath("$.data.fileName").isString());

        Ut.file.rm(newFilePath);
    }
}