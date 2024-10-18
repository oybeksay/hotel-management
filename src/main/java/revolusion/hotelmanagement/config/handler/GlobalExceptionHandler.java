package revolusion.hotelmanagement.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import revolusion.hotelmanagement.dto.AppErrorDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDTO> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        AppErrorDTO error = AppErrorDTO.builder()
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorPath(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        AppErrorDTO error = AppErrorDTO.builder()
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorPath(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}