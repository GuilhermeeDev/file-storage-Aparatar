package com.servidor.file_storage_Aparatar.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message="O nome não pode ser vazio!")
    @Column(name = "user_name", length = 255)
    private String nome;
    
    //Senha em String para manipulação de criptografia.
    @NotNull(message = "A senha não pose ser vazia!")
    @Column(name = "user_password",length = 255)
    private String senha;

    public Long getId(){
        return this.id;
    }

    public String getUserNome(){
        return this.nome;
    }

    public String getUserSenha(){
        return this.senha;
    }

    public void setUserNome(String nome){
        this.nome = nome;
    }

    public void setUserSenha(String senha){
        this.senha = senha;
    }
}
