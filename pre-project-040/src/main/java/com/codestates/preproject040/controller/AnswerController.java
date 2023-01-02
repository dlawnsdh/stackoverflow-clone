package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.answer.AnswerDto;
import com.codestates.preproject040.dto.answer.AnswerPatch;
import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.dto.answer.AnswerResponse;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.repository.UserAccountRepository;
import com.codestates.preproject040.service.AnswerService;
import com.codestates.preproject040.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@RestController
@Slf4j
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{questionId}/answers")   // 답변 등록
    public ResponseEntity postAnswer(@PathVariable("questionId") @Positive Long questionId,
                                     @RequestBody AnswerPost requestBody,
                                     @AuthenticationPrincipal UserAccountPrincipal principal) {

        AnswerDto answer = answerService.createAnswer(requestBody.toDto(principal.toDto()));

        // 서비스 계층으로 Dto를 주고, 서비스 안에서 Dto를 요리조리 볶는다.
        return new ResponseEntity<>(AnswerResponse.from(answer), HttpStatus.CREATED);
    }


    @PatchMapping("/{questionId}/answers/{answerId}")   // 답변 수정
    public ResponseEntity patchAnswer(@PathVariable("questionId") @Positive Long questionId,
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerPatch requestBody,
                                      @AuthenticationPrincipal UserAccountPrincipal principal) {
        System.out.println("answerId = " + answerId);
        System.out.println("questionId = " + questionId);
        System.out.println("requestBody.content() = " + requestBody.content());

        AnswerDto answerDto = answerService.updateAnswer(answerId, requestBody.toDto(principal.toDto()));

        return new ResponseEntity(AnswerResponse.from(answerDto), HttpStatus.OK);
    }


    @DeleteMapping("/{questionId}/answers/{answerId}")   // 답변 삭제
    public ResponseEntity deleteAnswer(@PathVariable("questionId") @Positive Long questionId,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @AuthenticationPrincipal UserAccountPrincipal principal) {

        answerService.deleteAnswer(questionId, principal.getUsername());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
