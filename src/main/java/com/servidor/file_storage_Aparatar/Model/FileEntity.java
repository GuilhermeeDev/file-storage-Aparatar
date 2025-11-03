package com.servidor.file_storage_Aparatar.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Arquivo")
public class FileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "O nome não pode ser vazio.")
    @Column(name = "Nome_arquivo", length = 255)
    private String nome_arquivo;

    // @NotBlank(message = "A URI do arquivo não pode ser vazia.")
    @Column(name = "Uri_arquivo")
    private String uri_arquivo;

    @Column(name = "Descricao_arquivo")
    private String descricao_arquivo;
    


    public String getNomeArquivo() {
        return this.nome_arquivo;
    }

    public void setNomeArquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public void setUriArquivo(String uri_arquivo){
        this.uri_arquivo = uri_arquivo;
    }

    public String getUriArquivo() {
        return this.uri_arquivo;
    }

    public void setDescricaoArquivo(String descricao_arquivo){
        this.descricao_arquivo = descricao_arquivo;
    }

    public String getDescricaoArquivo() {
        return this.descricao_arquivo;
    }
}
