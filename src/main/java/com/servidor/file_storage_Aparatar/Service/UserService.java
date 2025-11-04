package com.servidor.file_storage_Aparatar.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.servidor.file_storage_Aparatar.Model.UserEntity;
import com.servidor.file_storage_Aparatar.Repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> salvar_usuario(String nome, String senha){

        try {
            UserEntity _user = new UserEntity();
            _user.setUserNome(nome);
            _user.setUserSenha(senha);
            userRepository.save(_user);
            return ResponseEntity.ok("Usuario criado com sucesso");
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    
}
