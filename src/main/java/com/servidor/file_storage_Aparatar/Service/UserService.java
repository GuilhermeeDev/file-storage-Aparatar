package com.servidor.file_storage_Aparatar.Service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.servidor.file_storage_Aparatar.Model.UserEntity;
import com.servidor.file_storage_Aparatar.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity salvarUsuario(UserEntity userEntity) {
        // pegando a senha para encriptografar
        String _senha = userEntity.getSenha();
        if (_senha == null || _senha.isBlank()) {
            throw new IllegalArgumentException("Não foi possível salvar o usuário: campo 'senha' está vazio.");
        }
        //processo de criptografia
        String encriptar = passwordEncoder.encode(_senha);
        userEntity.setSenha(encriptar);
        
        return userRepository.save(userEntity);
    }

    public String getHashPeloEmail(String email) {
        return userRepository.findByEmail(email).map(UserEntity::getSenha).orElse(null);
    }

    public UserEntity getUsuarioPorEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean comparaHashSenha(String senhaDigitada, String email){
        String hash = getHashPeloEmail(email);
        if (hash == null) return false;
        return passwordEncoder.matches(senhaDigitada,hash);
    }

    public List<UserEntity> listarUsuarios(){
        return userRepository.findAll();
    }

    public UserEntity getUsuarioPeloId(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deletarUsuarioPorID(Long id){
        try{
            userRepository.deleteById(id);
        }catch (EntityNotFoundException e){
           throw new EntityNotFoundException("Usuario com id: "+id+" não encontrado.");
        }
    }
}
