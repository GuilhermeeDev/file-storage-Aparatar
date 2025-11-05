package com.servidor.file_storage_Aparatar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserPasswordEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senhaDigitada;

    public void setSenhaDigitada(String senhaDigitada){
        this.senhaDigitada = senhaDigitada;
    }

    public String getSenhaDigitada(){
        return senhaDigitada;
    }

}
