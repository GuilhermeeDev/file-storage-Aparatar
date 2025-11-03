package com.servidor.file_storage_Aparatar.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.servidor.file_storage_Aparatar.Config.FileStorageAparatarProperties;
import com.servidor.file_storage_Aparatar.Model.FileEntity;
import com.servidor.file_storage_Aparatar.FileStorageAparatarApplication;
import com.servidor.file_storage_Aparatar.Repository.FileStorageRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileStorageService {

    private final FileStorageAparatarApplication fileStorageAparatarApplication;

    private final FileStorageRepository fileStorageRepository;
    private final Path fileStorageLocation;
    
    public FileStorageService(FileStorageRepository fileStorageRepository, FileStorageAparatarProperties fileStorageLocation,
    FileStorageAparatarApplication fileStorageAparatarApplication){
        this.fileStorageRepository = fileStorageRepository;

        this.fileStorageLocation = Paths.get(fileStorageLocation.getUploadDir())
        .toAbsolutePath().normalize();

        this.fileStorageAparatarApplication = fileStorageAparatarApplication;
    }

    public ResponseEntity<String> salvarArquivo(MultipartFile file, String fileName){
        try{
            //Criando o caminho atraves da concatenação do diretorio de upload + nome do arquivo recebido + normalização
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            //Gerar o link de download
            String uriArquivo = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/files/download/")
            .path(fileName).toUriString();

            //Salvando dados da entidade do arquivo.
            FileEntity _file = new FileEntity();
            _file.setNomeArquivo(fileName);
            _file.setUriArquivo(uriArquivo);
            fileStorageRepository.save(_file);
            
            return ResponseEntity.ok("Arquivo recebido com sucesso: "+uriArquivo);

        } catch (IOException ex){
            return ResponseEntity.badRequest().build();
        }
    }
   
    public ResponseEntity<Resource> baixarArquivo(String fileName, HttpServletRequest request){

         Path filePath = fileStorageLocation.resolve(fileName).normalize();
    
        try {
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {contentType = "application/octet-stream";}

            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<List<String>> listarArquivos() {
        try {
            List<String> fileNames = Files.list(fileStorageLocation)
            .map(Path::getFileName)
            .map(Path::toString)
            .collect(Collectors.toList());

            return ResponseEntity.ok(fileNames);

        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}