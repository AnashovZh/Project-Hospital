package zhanuzak.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomExceptionHandler {
    public ResponseEntity<String>handlerNotFoundException(CustomNotFoundException customNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customNotFoundException.getMessage());
    }
}
