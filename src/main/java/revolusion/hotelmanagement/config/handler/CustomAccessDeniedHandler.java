package revolusion.hotelmanagement.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import revolusion.hotelmanagement.dto.AppErrorDTO;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String errorPath = request.getRequestURI();
        String errorMessage = accessDeniedException.getMessage();
        int errorCode = 403;
        AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage,errorCode,errorPath, LocalDateTime.now());
        response.setStatus(errorCode);
        ServletOutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, appErrorDTO);
    }
}
