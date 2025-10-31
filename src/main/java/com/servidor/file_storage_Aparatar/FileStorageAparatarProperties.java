package com.servidor.file_storage_Aparatar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "filePath")
public class FileStorageAparatarProperties {
    
    private String uplaod_dirs;

    public String getUploadDir() {
        return uplaod_dirs;
    }

    public void setUploadDir(String uplaod_dirs){
        this.uplaod_dirs = uplaod_dirs;
    }

}
