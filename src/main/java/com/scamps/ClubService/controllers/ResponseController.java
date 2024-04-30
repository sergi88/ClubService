package com.scamps.ClubService.controllers;

import com.scamps.ClubService.models.generics.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseController {

    public static <T>ResponseEntity<ApiResponse<T>> successResponse(T data, String message) {
        return ResponseEntity.ok(new ApiResponse<>(true, message, data));
    }

    public static ResponseEntity<ApiResponse<Void>> successResponse(String message) {
        return successResponse(null, message);
    }

    public static <T>ResponseEntity<ApiResponse<T>> errorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ApiResponse<>(false, message, null));
    }
}
