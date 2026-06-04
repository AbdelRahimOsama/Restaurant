package com.spring.resturant.config;


import com.spring.resturant.controller.vm.ExceotionResponseVm;
import com.spring.resturant.dto.Exception.*;
import com.spring.resturant.service.bundel.BundleTranslatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({IdMustNull.class, NotFound.class, Required.class, Positive.class, Found.class, HttpMediaTypeNotSupportedException.class, HandlerMethodValidationException.class})
    public ResponseEntity<ExceotionResponseVm> handelSystemException(Exception e) {
        String bundleMessage_en = BundleTranslatorService.getBundleMessage_en(e.getMessage());
        String bundleMessage_ar = BundleTranslatorService.getBundleMessage_ar(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceotionResponseVm(bundleMessage_en,bundleMessage_ar, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceotionResponseVm> handelSystemException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errors_en = fieldErrors.stream()
                .map(fieldError -> BundleTranslatorService.getBundleMessage_en(fieldError.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        String errors_ar = fieldErrors.stream()
                .map(fieldError -> BundleTranslatorService.getBundleMessage_ar(fieldError.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ExceotionResponseVm(errors_en,errors_ar, HttpStatus.BAD_REQUEST));
    }
}
