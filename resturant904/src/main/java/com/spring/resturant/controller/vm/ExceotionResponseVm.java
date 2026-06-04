package com.spring.resturant.controller.vm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExceotionResponseVm {

    private String message_en;

    private String message_ar;

    private HttpStatus status;

    private LocalDate date;

    public ExceotionResponseVm(String message_en,String message_ar, HttpStatus status) {
        this.message_en = message_en;
        this.message_ar = message_ar;
        this.status = status;
        this.date = LocalDate.now();
    }
}
