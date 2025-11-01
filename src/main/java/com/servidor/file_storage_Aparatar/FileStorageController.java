package com.servidor.file_storage_Aparatar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class FileStorageController {

    private final Path FileStorageLocation;

    public FileStorageController( FileStorageAparatarProperties FileStorageLocation){

        this.FileStorageLocation = Paths.get(FileStorageLocation.getUploadDir())
        .toAbsolutePath().normalize();
    
    }

    @PostMapping("/upload")
    public ResponseEntity<String> UploadFile(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return ResponseEntity.badRequest().body("Nome do arquivo não pode ser nulo.");
        }
        String fileName = StringUtils.cleanPath(originalFilename);

        try{
            //Criando o caminho atraves da concatenação do diretorio de upload + nome do arquivo recebido + normalização
            Path targetLocation = FileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);

            //Gerar o link de download
            String uriArquivo = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/files/download/").path(fileName).toUriString();

            return ResponseEntity.ok("Arquivo recebido com sucesso: " + uriArquivo);
 
        } catch (IOException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Path filePath = FileStorageLocation.resolve(fileName).normalize();

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

    @GetMapping("/list")
    public ResponseEntity<List<String>> listarArquivos() throws IOException {
        List<String> fileNames = Files.list(FileStorageLocation)
        .map(Path::getFileName)
        .map(Path::toString)
        .collect(Collectors.toList());

        return ResponseEntity.ok(fileNames);
    }
    
    
}

    
