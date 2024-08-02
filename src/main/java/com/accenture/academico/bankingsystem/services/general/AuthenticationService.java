package com.accenture.academico.bankingsystem.services.general;
import com.accenture.academico.bankingsystem.domain.user.User;
import com.accenture.academico.bankingsystem.dto.AuthenticationDTO;
import com.accenture.academico.bankingsystem.dto.ResponseToken;
import com.accenture.academico.bankingsystem.exception.InternalLogicException;
import com.accenture.academico.bankingsystem.repositories.UserRepository;
import com.accenture.academico.bankingsystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseToken login(AuthenticationDTO authenticationDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return token;
    }

    public void validateToken(String token) {
        if(!tokenService.isValidToken(token)) {
            throw new InternalLogicException("token invalid");
        }
    }

    public ResponseToken tokenRefresh(String tokenRefresh) {
        return tokenService.genNewToken(tokenRefresh);
    }
}
