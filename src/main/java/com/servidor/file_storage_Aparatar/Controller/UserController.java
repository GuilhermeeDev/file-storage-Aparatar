package com.servidor.file_storage_Aparatar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.servidor.file_storage_Aparatar.Model.UserEntity;
import com.servidor.file_storage_Aparatar.Model.UserPasswordEntity;
import com.servidor.file_storage_Aparatar.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create_user")
    public UserEntity criar_usuario(@RequestBody UserEntity userEntity) {
        return  userService.salvarUsuario(userEntity);
    }

    @GetMapping("/list_users")
    public List<UserEntity> listar_usuarios () {
        return userService.listarUsuarios();
    }
    
    @GetMapping("/list_users/{id}")
    public ResponseEntity<UserEntity> getMethodName(@PathVariable Long id) {
        
        UserEntity usuario = userService.obterUsuarioPeloId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    
    }

    //metodo teste
    @PostMapping("/comparar/{id}")
    public ResponseEntity<String>compararHash(@RequestBody UserPasswordEntity userPasswordEntity,
    @PathVariable Long id) {

        String senhaDigitada = userPasswordEntity.getSenhaDigitada();
        Boolean teste = userService.comparaHashSenha(senhaDigitada, id);
        
        if (teste!=false) return ResponseEntity.ok("Pessoa validada!");
        
        return ResponseEntity.ok("Dado senhaDigitada: "+senhaDigitada+" Id: "+id+" resposta: "+teste);

    }
    
}
