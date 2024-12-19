package ufersa.com.br.labor_renter.api.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(buildError("Conflito nos parâmetros enviados", "Erro de Cadastro: " + e.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(buildError("Recurso não encontrado", "Erro de Recurso: " + e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder("Erro(s) de Validação: ");
        e.getBindingResult().getAllErrors().forEach(error -> {
            message.append(error.getDefaultMessage()).append("; ");
        });

        return new ResponseEntity<>(buildError("Argumento Inválido", message.toString(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return new ResponseEntity<>(buildError("Violação de Integridade dos dados", "Erro de Violação de Integridade: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> buildError(String error, String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message.trim());
        body.put("timestamp", LocalDateTime.now());

        return body;
    }
}
