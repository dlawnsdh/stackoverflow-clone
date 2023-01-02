package com.codestates.preproject040.exception;

import lombok.Getter;

public enum ExceptionCode {
    QUESTION_NOT_FOUND(404, "Question not found"),
    USER_NOT_FOUND(404, "User not found"),
    ANSWER_NOT_FOUND(404,"Answer not found"),
    USER_ALREADY_EXIST(404, "user already exist");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
