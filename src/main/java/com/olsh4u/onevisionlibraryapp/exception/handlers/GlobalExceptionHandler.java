package com.olsh4u.onevisionlibraryapp.exception.handlers;

import com.olsh4u.onevisionlibraryapp.dto.ResponseData;
import com.olsh4u.onevisionlibraryapp.exception.BookAlreadyExistsException;
import com.olsh4u.onevisionlibraryapp.exception.BookNotSavedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.olsh4u.onevisionlibraryapp.dto.ResponseData.response;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<String>> handleException(Exception ex) {
        log.error("Unknown error. " , ex);
        return response(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData<String>> handleValidationException(Exception ex) {
        log.error("Validation failed. " , ex);
        return response( ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotSavedException.class)
    public ResponseEntity<ResponseData<String>> handleBookNotSaved(Exception ex) {
        log.error("handleBookNotSaved() triggered by " , ex);
        return response(ex.getMessage() , HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ResponseData<String>> handleBookAlreadyExists(Exception ex){
        log.error("handleBookAlreadyExists() triggered by "  , ex);
        return response(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

}
