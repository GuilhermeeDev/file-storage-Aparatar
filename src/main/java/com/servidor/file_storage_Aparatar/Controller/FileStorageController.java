package com.servidor.file_storage_Aparatar.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.servidor.file_storage_Aparatar.Service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService){
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> UploadFile(@RequestParam("file") MultipartFile file) {
        
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            return ResponseEntity.badRequest().body("Nome do arquivo não pode ser nulo.");
        }

        String fileName = StringUtils.cleanPath(originalFilename);
        
        try {

           return fileStorageService.salvarArquivo(file, fileName);
        
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Arquivo não foi recebido.");
        }    
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        return fileStorageService.baixarArquivo(fileName, request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listarArquivos() {
        return fileStorageService.listarArquivos();
    }
    
    
}

    
