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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


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
    public List<UserEntity> listarUsuarios () {
        return userService.listarUsuarios();
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<UserEntity> listarUsuarioPorId(@PathVariable Long id) {
        
        UserEntity usuario = userService.getUsuarioPeloId(id);
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

    @DeleteMapping("/del/{id}")
    public ResponseEntity deletarUsuarioPorID(@PathVariable Long id){
        userService.deletarUsuarioPorID(id);
        return ResponseEntity.ok("Usuario deletado com sucesso!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id,
    @RequestBody UserEntity userAtt) {

        UserEntity _user = userService.getUsuarioPeloId(id);
        String novoNome = userAtt.getNome();
        String novoEmail = userAtt.getEmail();
        String novoSenha = userAtt.getSenha();

        if (novoNome!="") _user.setNome(novoNome);
        if (novoEmail!="") _user.setEmail(novoEmail);
        if (novoSenha!="") _user.setSenha(novoSenha);

        userService.salvarUsuario(_user);

        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }
    
}
