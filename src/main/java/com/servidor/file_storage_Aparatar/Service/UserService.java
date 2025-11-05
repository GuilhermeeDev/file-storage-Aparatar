package com.servidor.file_storage_Aparatar.Service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.servidor.file_storage_Aparatar.Model.UserEntity;
import com.servidor.file_storage_Aparatar.Repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity salvarUsuario(UserEntity userEntity){
        // pegando a senha para encriptografar
        String _senha = userEntity.getSenha();

        //processo de criptografia
        String encriptar = passwordEncoder.encode(_senha);
        userEntity.setSenha(encriptar);
        
        return userRepository.save(userEntity);
    }

    public String getHashPeloEmail(String email) {
        return userRepository.findByEmail(email)
            .map(UserEntity::getSenha)
            .orElse(null);
    }

    public boolean comparaHashSenha(String senhaDigitada, String email){
        String hash = getHashPeloEmail(email);
        if (hash == null) return false;
        return passwordEncoder.matches(senhaDigitada,hash);
    }

    public List<UserEntity> listarUsuarios(){
        return userRepository.findAll();
    }

    public UserEntity obterUsuarioPeloId(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deletarUsuarioPorID(Long id){
        userRepository.deleteById(id);
    }

}
