package com.servidor.file_storage_Aparatar.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.servidor.file_storage_Aparatar.FileStorageAparatarProperties;
import com.servidor.file_storage_Aparatar.models.File;
import com.servidor.file_storage_Aparatar.repository.FileUriRepository;

public class ManagementFileService {

    @Autowired
    private final FileUriRepository fileUriRepository;

    //variavel que representa o diretorio utilizado.
    private final Path fileStorageLocation;

    public ManagementFileService(FileUriRepository fileUriRepository, FileStorageAparatarProperties fileStorageAparatarProperties) {
        this.fileUriRepository = fileUriRepository;

        //passando a propriedade que possui os metodos para acessar o caminho relativo do diretorio.
        //pegando o caminho absoluto do diretorio.  
        this.fileStorageLocation = Paths.get(fileStorageAparatarProperties.getUploadDir())
        .toAbsolutePath().normalize();

    }

    public ResponseEntity<String> salvarArquivo(MultipartFile file){
        
        @SuppressWarnings("null")
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            //Criando o caminho atraves da concatenação do diretorio de upload + nome do arquivo recebido + normalização
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            //Gerar o link de download
            String uriArquivo = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/files/download/").path(fileName).toUriString();

            File _file = new File();
            _file.setNomeArquivo(fileName);
            _file.setUriArquivo(uriArquivo);
            _file = fileUriRepository.save(_file);

            return ResponseEntity.ok("Upload realizado com sucesso.");

        } catch (IOException ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
