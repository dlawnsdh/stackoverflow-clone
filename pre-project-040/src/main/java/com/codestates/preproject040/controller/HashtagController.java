package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.PostHashtagDto;
import com.codestates.preproject040.service.HashtagService;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
//@RequestMapping("/questions")
public class HashtagController {
    private final HashtagService hashtagService;
    private final QuestionService questionService;

    public HashtagController(HashtagService hashtagService,
                             QuestionService questionService){
        this.hashtagService=hashtagService;
        this.questionService=questionService;
    }

    @PostMapping //해쉬대크 등록
    public ResponseEntity postHashtag(@PathVariable("hashtagId") @Positive Long hashtagID,
                                      @RequestBody PostHashtagDto requestBody){
//        PostHashtagDto postHashtagDto=PostHashtagDto.of(hashtagID,requestBody.content());
        PostHashtagDto postHashtagDto = PostHashtagDto.of(requestBody.content());

        return new ResponseEntity<>(HashtagDto.from(hashtagService.createHashtag(postHashtagDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/questions/{hashtagId}")
    public ResponseEntity patchAnswer(@PathVariable("hashtagId") @Positive Long hashtagId){
        hashtagService.deleteHashtag(hashtagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
