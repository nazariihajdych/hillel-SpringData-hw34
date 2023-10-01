package ua.hillel.springdataapp.exeption.globalExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.springdataapp.exeption.EntityNotFoundException;
import ua.hillel.springdataapp.model.dto.ErrorDTO;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException occurred");
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAnyException(Exception e) {
        log.error("Exception {} occurred", e.toString());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
