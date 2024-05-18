package br.com.fiap.smartsuplify.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutForm(){
        return "auth/logout";
    }

    
}
