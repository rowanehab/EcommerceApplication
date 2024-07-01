package com.example.demo.Controller;

import com.example.demo.Exceptions.EmailAlreadyExistsException;
import com.example.demo.Exceptions.UsernameAlreadyExistsException;
import com.example.demo.response.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(EmailAlreadyExistsException exc){
        UserErrorResponse error=new UserErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UsernameAlreadyExistsException exc) {
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.CONFLICT.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc) {
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }










    /*
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        String errorMessage = e.getMessage();
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        String errorMessage = e.getMessage();
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        String errorMessage = "An error occurred: " + e.getMessage();
        logger.error(errorMessage, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }*/
}

