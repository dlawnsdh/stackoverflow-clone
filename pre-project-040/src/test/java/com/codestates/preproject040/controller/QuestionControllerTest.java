package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.dto.question.QuestionDto;
import com.codestates.preproject040.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired private QuestionService questionService;
    @Autowired private ObjectMapper objectMapper;

    @DisplayName("글 4개 작성")
    @BeforeAll
    void postQuestion() throws Exception {
        QuestionDto postA = QuestionDto.of("AtestTitle", "AtestContent1", "AtestContent2");
        QuestionDto postB = QuestionDto.of("BtestTitle-z", "BtestContent1", "BtestContent2");
        QuestionDto postC = QuestionDto.of("CtestTitle", "CtestContent1-z", "CtestContent2");
        QuestionDto postD = QuestionDto.of("DtestTitle", "DtestContent1", "DtestContent2-z");

        String jsonA = objectMapper.writeValueAsString(postA);
        String jsonB = objectMapper.writeValueAsString(postB);
        String jsonC = objectMapper.writeValueAsString(postC);
        String jsonD = objectMapper.writeValueAsString(postD);

        // postA
        ResultActions actionsA =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonA)
                );

        actionsA.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("AtestTitle"))
                .andExpect(jsonPath("$.content1").value("AtestContent1"))
                .andExpect(jsonPath("$.content2").value("AtestContent2"));

        // postB
        ResultActions actionsB =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonB)
                );

        actionsB.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("BtestTitle-z"))
                .andExpect(jsonPath("$.content1").value("BtestContent1"))
                .andExpect(jsonPath("$.content2").value("BtestContent2"));

        // postC
        ResultActions actionsC =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonC)
                );

        actionsC.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("CtestTitle"))
                .andExpect(jsonPath("$.content1").value("CtestContent1-z"))
                .andExpect(jsonPath("$.content2").value("CtestContent2"));

        // postD
        ResultActions actionsD =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonD)
                );

        actionsD.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("DtestTitle"))
                .andExpect(jsonPath("$.content1").value("DtestContent1"))
                .andExpect(jsonPath("$.content2").value("DtestContent2-z"));
    }

    @Test
    @DisplayName("1개 보기")
    @Order(1)
    void getQuestion() throws Exception {
        long questionId = 1L;

        ResultActions actions =
                mvc.perform(
                        get("/questions/" + questionId)
                );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("AtestTitle"))
                .andExpect(jsonPath("$.content1").value("AtestContent1"))
                .andExpect(jsonPath("$.content2").value("AtestContent2"));
    }

    @Test
    @DisplayName("홈 화면 전체 목록")
    @Order(2)
    void getQuestionsHome() throws Exception {
        mvc.perform(
                        get("/")
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                // 페이지에 questionId 4, 3 나오는지 확인
                .andExpect(jsonPath("$[0].questionId").value(4))
                .andExpect(jsonPath("$[1].questionId").value(3));
    }

    @Test
    @DisplayName("퀘스천 화면 전체 목록")
    @Order(3)
    void testGetQuestions() throws Exception {
        int page = 1;

        mvc.perform(
                        get("/questions")
                                .queryParam("page", String.valueOf(page))
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                // 1 페이지에 quesitonId 2, 1인지 확인 (0 페이지에 4, 3 / 1 페이지에 2, 1)
                .andExpect(jsonPath("$[0].questionId").value(2))
                .andExpect(jsonPath("$[1].questionId").value(1));
    }


    @Test
    @DisplayName("검색 기능")
    @Order(4)
    void getQuestions() throws Exception {
        // question 1번에 "답변-z" 라고 answer 달아주는 과정
        AnswerPost answerPost = AnswerPost.of(1L, "답변-z");

        String answer = objectMapper.writeValueAsString(answerPost);

        mvc.perform(
                post("/questions/1/answers")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(answer)
        )
                .andExpect(status().isCreated());

        // 위에서 글 4개, 답변 작성하고 검색 키워드로 z 을 넣었습니다!
        // questionId 차례로 [답변, title1, content1, content2] 에 넣어서 id [4>3>2>1] 순으로 나오는지 확인
        String searchKeyword = "z";

        // page = 0
        mvc.perform(
                        get("/questions/search")
                                .queryParam("searchKeyword", String.valueOf(searchKeyword))
                                .queryParam("page", String.valueOf(0))
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                // 0번째 페이지에 questionId 4, 3 나오는지 확인
                .andExpect(jsonPath("$.content[0].questionId").value(4))
                .andExpect(jsonPath("$.content[1].questionId").value(3));

        // page = 1
        mvc.perform(
                        get("/questions/search")
                                .queryParam("searchKeyword", String.valueOf(searchKeyword))
                                .queryParam("page", String.valueOf(1))
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                // 1번째 페이지에 questionId 2, 1 나오는지 확인 (questionId 1번은 답변 내용으로 검색되는 것)
                .andExpect(jsonPath("$.content[0].questionId").value(2))
                .andExpect(jsonPath("$.content[1].questionId").value(1));
    }

    @Test
    @DisplayName("글 수정")
    @Order(5)
    void patchQuestion() throws Exception {
        QuestionDto post = QuestionDto.of("BtestNewTitle", "BtestNewContent1", "BtestNewContent2");

        String json = objectMapper.writeValueAsString(post);

        long questionId = 2L;

        mvc.perform(
                        patch("/questions/" + questionId)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("BtestNewTitle"))
                .andExpect(jsonPath("$.content1").value("BtestNewContent1"))
                .andExpect(jsonPath("$.content2").value("BtestNewContent2"));
    }

    @Test
    @DisplayName("글 삭제")
    @Order(6)
    void deleteQuestion() throws Exception {
        long questionId = 3L;

        mvc.perform(
                        delete("/questions/" + questionId)
                )

                .andExpect(status().isNoContent());
    }

}