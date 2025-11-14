package com.servidor.file_storage_Aparatar.Exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.servidor.file_storage_Aparatar.Model.ErrorMessageEntity;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageEntity> GenericException(Exception ex){
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null){ errorMessage = ex.toString(); }
        ErrorMessageEntity error = new ErrorMessageEntity( errorMessage,new Date(),"Erro interno no servidor.");
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageEntity> entityNotFound(EntityNotFoundException ex) {
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null) errorMessage = ex.toString();
        ErrorMessageEntity error = new ErrorMessageEntity(errorMessage, new Date(),"<Entidade> não encontrada.");
        return new ResponseEntity<>(error, new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

}

