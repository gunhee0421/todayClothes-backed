package org.project.todayclothes.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ReviewErrorCode implements ErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없습니다."),
    INVALID_REVIEW_CONTENT(HttpStatus.BAD_REQUEST, "리뷰 내용이 유효하지 않습니다."),

    S3_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S3에 파일을 업로드하는 중 오류가 발생했습니다."),
    S3_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S3에 파일을 삭제하는 중 오류가 발생했습니다."),
    FILE_CONVERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "파일 변환 오류가 발생했습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 리뷰가 존재합니다."),
    INVALID_FILE_FORMAT(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "파일 형식이 유효하지 않습니다."),
    INVALID_FEEDBACK_ENUM(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "피드백 형식이 유효하지 않습니다."),
    REVIEW_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰 업데이트 중 오류가 발생했습니다."),
    FILE_SIZE_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "파일 크기가 허용된 한도를 초과했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ReviewErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
