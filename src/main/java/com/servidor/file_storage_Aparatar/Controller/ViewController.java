package com.servidor.file_storage_Aparatar.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController     {

    @GetMapping("/")
    public String ViewHome() {
        return "ViewHome";
    }

    @GetMapping("/login")
    public String ViewLogin() {
        return "ViewLoginAcount";
    }

    @GetMapping("/register")
    public String ViewCreate() {
        return "ViewCreateAcount";
    }

    @GetMapping("/storage")
    public String getMethodName() {
        return "ViewStorageFiles";
    }
    
    
}
