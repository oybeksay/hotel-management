package revolusion.hotelmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.config.security.jwt.JwtTokenUtil;
import revolusion.hotelmanagement.domein.TokenRequest;
import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.dto.ResetPasswordDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.service.AuthUserService;
import revolusion.hotelmanagement.service.ResetPasswordService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth User")
public class AuthUserController {

    private final AuthUserService authUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final ResetPasswordService resetPasswordService;


    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthUserDTO dto) {
        authUserService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @Operation(summary = "Get token for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token retrieved successfully", content = @Content(schema = @Schema(implementation = String.class)))
    })

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.username();
        String password = tokenRequest.password();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return ResponseEntity.ok(jwtTokenUtil.generateToken(username));
    }

    @Operation(summary = "Activate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User activated successfully", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activate(@Parameter(description = "Activation code", required = true) @PathVariable String code) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authUserService.activateAccount(code));
    }

    @Operation(summary = "Create a password reset token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset token created successfully", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Parameter(description = "Email address", required = true) @RequestParam String email) {
        return ResponseEntity.ok(resetPasswordService.createPasswordResetToken(email));
    }

    @Operation(summary = "Reset password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset successfully", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
        return ResponseEntity.ok(resetPasswordService.resetPassword(dto));
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully", content = @Content(schema = @Schema(implementation = Void.class)))
    })

    @PutMapping("/update")
    public ResponseEntity<Void> updateRole(@RequestBody AuthUser authUser) {
        authUserService.update(authUser);
        return ResponseEntity.noContent().build();
    }
}