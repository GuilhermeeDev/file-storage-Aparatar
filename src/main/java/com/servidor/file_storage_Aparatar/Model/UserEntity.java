package com.servidor.file_storage_Aparatar.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Usuario")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "senha",length = 255)
    private String senha;

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getSenha(){
        return this.senha;
    }

    public String getEmail(){
        return this.email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}
