package com.scamps.ClubService.controllers.generics;

import com.scamps.ClubService.ValuesConfig;
import com.scamps.ClubService.controllers.ResponseController;
import com.scamps.ClubService.controllers.ValidateController;
import com.scamps.ClubService.models.ResponseMessage;
import com.scamps.ClubService.models.generics.ApiResponse;
import com.scamps.ClubService.models.generics.GenericEntity;
import com.scamps.ClubService.models.services.generics.GenericService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public class GenericController<T extends GenericEntity<T>> implements ValidateController {

    protected final GenericService<T> service;
    protected final Logger logger;

    public GenericController(GenericService<T> service, Logger logger) {
        this.service = service;
        this.logger = logger;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLUBMANAGER')")
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody T object, BindingResult result){
        try{
            ResponseEntity<?> errors = validation(result);
            if (errors != null) return (ResponseEntity<ApiResponse<T>>) errors;

            service.create(object);
            return ResponseController.successResponse(object, ValuesConfig.getMessage(ResponseMessage.DEFAULTSUCCESS));
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseController.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ValuesConfig.getMessage(ResponseMessage.DEFAULTERROR));
        }
    }

    @GetMapping("/{id}/details")
    @ResponseBody
    public ResponseEntity<ApiResponse<T>> findById(@PathVariable Long id){
        try{
            T object = service.getById(id);
            if (object == null)
                return ResponseController.errorResponse(HttpStatus.NOT_FOUND, ValuesConfig.getMessage(ResponseMessage.DEFAULTNOTFOUND));

            return ResponseController.successResponse(object, ValuesConfig.getMessage(ResponseMessage.DEFAULTSUCCESS));
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseController.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ValuesConfig.getMessage(ResponseMessage.DEFAULTERROR));
        }
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<ApiResponse<T>> update(@Valid @RequestBody T object, BindingResult result, @PathVariable Long id){
        try{
            ResponseEntity<?> errors = validation(result);
            if (errors != null) return (ResponseEntity<ApiResponse<T>>) errors;

            T obj = service.getById(id);
            if (obj == null)
                return ResponseController.errorResponse(HttpStatus.NOT_FOUND, ValuesConfig.getMessage(ResponseMessage.DEFAULTNOTFOUND));

            service.update(object);
            return ResponseController.successResponse(object, ValuesConfig.getMessage(ResponseMessage.DEFAULTSUCCESS));
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseController.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ValuesConfig.getMessage(ResponseMessage.DEFAULTERROR));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLUBMANAGER')")
    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<ApiResponse<T>> delete(@PathVariable Long id){
        try{
            T object = service.getById(id);
            if (object == null)
                return ResponseController.errorResponse(HttpStatus.NOT_FOUND, ValuesConfig.getMessage(ResponseMessage.DEFAULTERROR));

            service.delete(object);
            return ResponseController.successResponse(null, ValuesConfig.getMessage(ResponseMessage.DEFAULTSUCCESS));
        } catch (Exception e){
            logger.error(e.getMessage());
            return ResponseController.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ValuesConfig.getMessage(ResponseMessage.DEFAULTERROR));
        }
    }
}
