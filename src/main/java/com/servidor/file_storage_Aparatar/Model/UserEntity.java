package com.servidor.file_storage_Aparatar.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "Usuario")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome", length = 255)
    private String nome;
    
    @NotNull
    @Column(name = "email", length = 255)
    private String email;

    @NotNull
    @Column(name = "senha",length = 255)
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

    private String getUserEmail(){
        return this.email;
    }

    public void setUserEmail(String email){
        this.email = email;
    }

    public void setUserNome(String nome){
        this.nome = nome;
    }

    public void setUserSenha(String senha){
        this.senha = senha;
    }
}
