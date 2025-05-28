package com.Prakash.AirBnb.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

//  @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timeStamp;
//    response can either be data field or error
    private T data ;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        // calling default constructor
        this();
        this.error = error;
    }

    public ApiResponse(T data) {
        // calling default constructor
        this();
        this.data = data;
    }
}
