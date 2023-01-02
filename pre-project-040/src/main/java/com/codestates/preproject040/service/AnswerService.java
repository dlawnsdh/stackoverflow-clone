package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.answer.AnswerDto;
import com.codestates.preproject040.dto.answer.AnswerPatch;
import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserAccountRepository userAccountRepository;

    public AnswerDto createAnswer(AnswerDto dto) {
        Question question = questionRepository.getReferenceById(dto.questionId());
        UserAccount user = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

        return AnswerDto.from(answerRepository.save(dto.toEntity(question, user)));
    }

    public AnswerDto updateAnswer(Long answerId, AnswerDto dto) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        findAnswer.setContent(dto.content());
        return AnswerDto.from(answerRepository.save(findAnswer));
    }

    public void deleteAnswer(Long questionId, String userId) {
        answerRepository.deleteByIdAndUserAccount_UserId(questionId, userId);
    }

    private Answer findVerifiedAnswer(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

 }
