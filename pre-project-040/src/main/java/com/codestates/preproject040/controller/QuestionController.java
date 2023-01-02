package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.question.*;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.dto.user.UserAccountDto;
import com.codestates.preproject040.dto.question.QuestionResponseDto;
import com.codestates.preproject040.dto.question.QuestionWithAnswersResponseDto;
import com.codestates.preproject040.repository.UserAccountRepository;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final UserAccountRepository userAccountRepository;

    public QuestionController(QuestionService questionService,
                              UserAccountRepository userAccountRepository) {
        this.questionService = questionService;
        this.userAccountRepository = userAccountRepository;
    }

    // 검색 ex. questions/search?searchKeyword=내용&page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/questions/search")
    public ResponseEntity getQuestions(String searchKeyword, @PageableDefault(size = 2) Pageable pageable) {
        Page<QuestionResponseDto> searchPage = questionService.searchQuestions(searchKeyword, pageable);

        return new ResponseEntity<>(searchPage, HttpStatus.OK);
    }

    // 작성
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@RequestBody QuestionPost requestBody,
                                       @AuthenticationPrincipal UserAccountPrincipal principal){

        QuestionDto question = requestBody.toDto(principal.toDto());
        QuestionResponseDto createdQuestion = questionService.createQuestion(question, requestBody.hashtags());

        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    /*
    @GetMapping("/questions/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive Long id){
        QuestionWithAnswersResponseDto question = questionService.findQuestion(id);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }
     */
    // 1개 보기
    @GetMapping("/questions/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive Long id){
        if(questionService.answerIsEmpty(id)) {
            QuestionResponseDto question = questionService.findQuestion(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        else {
            QuestionWithAnswersResponseDto question = questionService.findQuestionWithAnswers(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
    }

    // (Home 화면) 목록 보기
    // 실제 size=96, 일단 size=2로 작성
    @GetMapping("/")
    public ResponseEntity getQuestionsHome(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        List<QuestionResponseDto> questionList = questionService.findQuestions(pageable);

        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    // (Question 화면) 목록 보기 ex. /questions?page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/questions")
    public ResponseEntity getQuestions(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        List<QuestionResponseDto> questionList = questionService.findQuestions(pageable);

        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    // 수정
    //PatchDto로 body 받아오고, responseDto형식으로 보내기
    @PatchMapping("/questions/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive Long id,
                                        @RequestBody QuestionPatch requestBody,
                                        @AuthenticationPrincipal UserAccountPrincipal principal){

        QuestionDto question = requestBody.toDto(principal.toDto());
        QuestionResponseDto updatedQuestion = questionService.updateQuestion(id, question);

        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") Long id){
        questionService.deleteQuestion(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}