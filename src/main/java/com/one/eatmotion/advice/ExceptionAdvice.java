package com.one.eatmotion.advice;

import com.one.eatmotion.advice.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(NotFoundResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected AppError userNotFoundException(HttpServletRequest request, NotFoundResourceException e) {
        // 예외 처리의 메시지를 MessageSource 에서 가져오도록 수정
        return AppError.builder().code(Integer.valueOf(getMessage("notFoundResource.code"))).message(getMessage("notFoundResource.message")).build();
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected AppError userNotUserException(HttpServletRequest request, NotFoundUserException e) {
        return AppError.builder().code(Integer.valueOf(getMessage("notFoundUser.code"))).message(getMessage("notFoundUser.message")).build();
    }

    @ExceptionHandler(NotFoundEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected AppError userNotEmailException(HttpServletRequest request, NotFoundEmailException e) {
        return AppError.builder().code(Integer.valueOf(getMessage("notFoundEmail.code"))).message(getMessage("notFoundEmail.message")).build();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected AppError invalidPasswordException(HttpServletRequest request, InvalidPasswordException e) {
        return AppError.builder().code(Integer.valueOf(getMessage("invalidPassword.code"))).message(getMessage("invalidPassword.message")).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected AppError accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return AppError.builder().code(Integer.valueOf(getMessage("accessDenied.code"))).message(getMessage("accessDenied.message")).build();
    }


    // code 정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code 정보, 추가 argument 로 현재 locale 에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
