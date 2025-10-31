package com.servidor.file_storage_Aparatar.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.servidor.file_storage_Aparatar.service.ManagementFileService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class FileStorageController {

    private final ManagementFileService managementFileService;

    public FileStorageController( ManagementFileService managementFileService){
        //Conectando o controller com seu respectivo service
        this.managementFileService = managementFileService;
    }

    @PostMapping("/upload")
    public void UploadFile(@RequestParam MultipartFile file) {
        managementFileService.salvarArquivo(file);
    }
}
