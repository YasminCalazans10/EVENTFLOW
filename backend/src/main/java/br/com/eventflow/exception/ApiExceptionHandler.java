package br.com.eventflow.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<?> notFound(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("erro","Registro não encontrado"));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("erro",e.getMessage()==null?"Dados inválidos":e.getMessage()));}
 @ExceptionHandler(IllegalStateException.class) ResponseEntity<?> rule(IllegalStateException e){return ResponseEntity.status(409).body(Map.of("erro",e.getMessage()));}
}
