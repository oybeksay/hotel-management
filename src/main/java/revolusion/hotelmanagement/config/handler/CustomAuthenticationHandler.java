package revolusion.hotelmanagement.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import revolusion.hotelmanagement.dto.AppErrorDTO;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationHandler implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String errorPath = request.getRequestURI();
        String errorMessage = authException.getMessage();
        int errorCode = 401;
        AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage,errorCode,errorPath, LocalDateTime.now());
        response.setStatus(errorCode);
        ServletOutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, appErrorDTO);
    }
}
