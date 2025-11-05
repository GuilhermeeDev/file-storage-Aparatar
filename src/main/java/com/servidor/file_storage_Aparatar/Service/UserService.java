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
        //pegando a senha para encriptografar
        String _senha = userEntity.getUserSenha();

        //processo de criptografia
        String encriptar = passwordEncoder.encode(_senha);
        userEntity.setUserSenha(encriptar);
        
        return userRepository.save(userEntity);
    }

    public String getSenhaPeloID(Long id) {
        return userRepository.findById(id)
            .map(UserEntity::getUserSenha)
            .orElse(null);
    }

    public boolean comparaHashSenha(String senhaDigitada, Long id){
        String hash = getSenhaPeloID(id);
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
