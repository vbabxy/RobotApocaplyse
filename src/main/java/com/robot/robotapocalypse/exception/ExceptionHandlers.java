package com.robot.robotapocalypse.exception;


import com.robot.robotapocalypse.domain.AppResponse;
import com.robot.robotapocalypse.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    private final MessageSource messageSource;

    public ExceptionHandlers(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ResponseEntity<AppResponse> handleModelNotFoundException(ModelNotFoundException ex) {
        log.error("Not found exception thrown");
        final var appResponse = AppUtils.buildAppResponse("", false,
                "", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appResponse);
    }


    @ExceptionHandler(ModelAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<AppResponse> handleModelAlreadyExistException(ModelAlreadyExistException ex) {
        log.error("Model Already Exist exception thrown");
        final var appResponse = AppUtils.buildAppResponse("", false,
                "", ex.getArgumentSupplied());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<AppResponse> handleBadRequestException(BadRequestException ex) {
        log.error("Bad Request thrown");
        final var appResponse = AppUtils.buildAppResponse("", false,
                "", ex.getArgumentSupplied());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appResponse);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ResponseEntity<AppResponse> handleGeneralException(Exception ex) {
        log.error("General exception thrown");

        final var appResponse = AppUtils.buildAppResponse("", false,
                "", ex.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(appResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<AppResponse> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        FieldError error = result.getFieldError();

        if (error != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(processFieldError(error));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private AppResponse processFieldError(FieldError error) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()),
                null,
                currentLocale);

        return AppUtils.buildAppResponse("", false,
                "", msg);
    }


}
