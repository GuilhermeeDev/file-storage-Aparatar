package com.servidor.file_storage_Aparatar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.servidor.file_storage_Aparatar.Model.LoginRequestDTO;
import com.servidor.file_storage_Aparatar.Model.UserEntity;
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

    @PostMapping("/create")
    public UserEntity criar_usuario(@RequestBody UserEntity userEntity) {
        return  userService.salvarUsuario(userEntity);
    }

    @GetMapping("/list")
    public List<UserEntity> listar_usuarios () {
        return userService.listarUsuarios();
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<UserEntity> getMethodName(@PathVariable Long id) {
        
        UserEntity usuario = userService.obterUsuarioPeloId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    
    }

    @PostMapping("/login")
    public ResponseEntity<?>compararHash(@RequestBody LoginRequestDTO loginRequestDTO) {

        String emailDigitado = loginRequestDTO.getEmail();
        String senhaDigitada = loginRequestDTO.getSenha();

        Boolean teste = userService.comparaHashSenha(senhaDigitada, emailDigitado);
        
        if (teste!=false) return ResponseEntity.ok("Pessoa validada!");
        
        return ResponseEntity.ok("Dado senhaDigitada: "+senhaDigitada+" Email: "+emailDigitado+" resposta: "+teste);

    }
    
}
