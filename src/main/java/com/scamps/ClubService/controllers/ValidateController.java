package com.scamps.ClubService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract interface ValidateController {

    default ResponseEntity<?> validation(BindingResult result) {
        if (result.hasErrors()) {
            return ResponseController.errorResponse(HttpStatus.BAD_REQUEST, result.getAllErrors().get(0).getDefaultMessage());
        }
        return null;
    }
}
