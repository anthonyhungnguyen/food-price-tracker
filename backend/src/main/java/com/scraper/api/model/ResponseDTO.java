package com.scraper.api.model;

import lombok.Data;

@Data
public class ResponseDTO {
    private boolean isSuccess;
    private int status;
    private String message;
}
