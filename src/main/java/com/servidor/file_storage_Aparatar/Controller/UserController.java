package com.servidor.file_storage_Aparatar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servidor.file_storage_Aparatar.Service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create_user")
    public ResponseEntity<String> criar_usuario(@RequestBody String nome, String senha) {
        
        return userService.salvar_usuario(nome, senha);
    
    }
    

}
