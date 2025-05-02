package com.Kunal.Login.Exception;

import com.Kunal.Login.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandeler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExists(UserAlreadyExists userAlreadyExists, WebRequest webRequest){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                userAlreadyExists.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongLoginCredentials.class)

    public ResponseEntity<ErrorResponseDto> handleWrongLogin(WrongLoginCredentials wrongLoginCredentials, WebRequest webRequest){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                wrongLoginCredentials.getMessage(),
                LocalDateTime.now()
        );
        return  new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotExists.class)

    public ResponseEntity<ErrorResponseDto> handleUserNotExist(UserNotExists userNotExists, WebRequest webRequest){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                userNotExists.getMessage(),
                LocalDateTime.now()
        );
        return  new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}
