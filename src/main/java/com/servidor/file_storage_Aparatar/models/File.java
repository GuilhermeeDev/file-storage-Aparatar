package com.servidor.file_storage_Aparatar.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "File")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "arquivo ")
    private String nomeArquivo;

    @NonNull
    private String UriArquivo;

    //    public File(Long id, String nomeArquivo, String UriArquivo){
    //        this.id = id;
    //        this.nomeArquivo = nomeArquivo;
    //        this.UriArquivo = UriArquivo;
    //    }
    
    public Long getId() {
        return id;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setUriArquivo(String UriArquivo) {
        this.UriArquivo = UriArquivo;
    }

    public String getUriArquivo() {
        return UriArquivo;
    }
}
