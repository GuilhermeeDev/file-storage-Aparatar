package com.servidor.file_storage_Aparatar.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.servidor.file_storage_Aparatar.Exception.AppExceptionHandler;
import com.servidor.file_storage_Aparatar.Model.LoginRequestDTO;
import com.servidor.file_storage_Aparatar.Model.UserEntity;
import com.servidor.file_storage_Aparatar.Service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    private final AppExceptionHandler appExceptionHandler;
    
    private UserService userService;

    public UserController(UserService userService, AppExceptionHandler appExceptionHandler){
        this.userService = userService;
        this.appExceptionHandler = appExceptionHandler;
    }

    @PostMapping("/create")
    public UserEntity criar_usuario(@Valid @RequestBody UserEntity userEntity) {
        return  userService.salvarUsuario(userEntity);
    }

    @GetMapping("/list")
    public List<UserEntity> listarUsuarios () {
        try{
            return userService.listarUsuarios();
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possivel listar os usuários.", ex);
        }
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<UserEntity> listarUsuarioPorId(@PathVariable Long id) {
        UserEntity usuario = userService.getUsuarioPeloId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?>compararHash(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        UserEntity usuario = userService.getUsuarioPorEmail(loginRequestDTO.getEmail());
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário com email " + loginRequestDTO.getEmail() + " não encontrado.");
        }
        boolean autenticar = userService.comparaHashSenha(loginRequestDTO.getSenha(),loginRequestDTO.getEmail());
        if (!autenticar) {
            throw new IllegalArgumentException("Credenciais inválidas: a senha informada não corresponde ao usuário.");
        }
        return ResponseEntity.ok(Map.of( "status", "success", "message", "Usuário autenticado."));    
    
    }

    @DeleteMapping("/del/{id}")
    public void  deletarUsuarioPorID(@PathVariable Long id){
        userService.deletarUsuarioPorID(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> atualizarUsuarioPorID(@Valid @PathVariable Long id,
    @RequestBody UserEntity userAtt) { 

        UserEntity _user = userService.getUsuarioPeloId(id);
        if (_user == null) {
            throw new EntityNotFoundException("Usuário com id " + id + " não encontrado");
        }

        String novoNome = userAtt.getNome();
        String novoEmail = userAtt.getEmail();
        String novoSenha = userAtt.getSenha();

        if (novoNome != null && !novoNome.isBlank()) _user.setNome(novoNome);
        if (novoEmail != null && !novoEmail.isBlank()) _user.setEmail(novoEmail);
        if (novoSenha != null && !novoSenha.isBlank()) _user.setSenha(novoSenha);
        userService.salvarUsuario(_user);

        return ResponseEntity.ok("Usuario atualizado com sucesso.");

    }
    
}
