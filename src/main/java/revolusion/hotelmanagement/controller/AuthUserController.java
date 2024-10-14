package revolusion.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.config.security.jwt.JwtTokenUtil;
import revolusion.hotelmanagement.domein.AuthRole;
import revolusion.hotelmanagement.domein.TokenRequest;
import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.service.AuthUserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody AuthUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authUserService.save(dto));
    }

    @PostMapping("/token")
    public String getToken(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.username();
        String password = tokenRequest.password();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenUtil.generateToken(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> updateRole(@RequestBody AuthUserDTO dto , AuthRole role) {
        return ResponseEntity.noContent().build();
    }
}