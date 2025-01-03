package app.blogging.blogging_application.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(FetchException.class)
    public ResponseEntity<?> fetchExceptionHandler(FetchException ex){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("error", "Fetch operation failed");
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("cause", ex.getCause() != null ? ex.getCause().getMessage() : "N/A");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CreationException.class)
    public ResponseEntity<?> createExceptionHandler(CreationException ex){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("error", "Create operation failed");
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("cause", ex.getCause() != null ? ex.getCause().getMessage() : "N/A");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, Object> resp = new HashMap<>();
        resp.put("timestamp", LocalDateTime.now());
        resp.put("status", HttpStatus.BAD_REQUEST.value());
        resp.put("error", "Argument Not Valid");
        resp.put("message", ex.getMessage());
        resp.put("cause", ex.getCause() != null ? ex.getCause().getMessage() : "N/A");

        return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
    }
}
