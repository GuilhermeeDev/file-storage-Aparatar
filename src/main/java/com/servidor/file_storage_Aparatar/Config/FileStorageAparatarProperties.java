package com.servidor.file_storage_Aparatar.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageAparatarProperties {
    
    private String uplaod_dir;

    public String getUploadDir() {
        return uplaod_dir;
    }

    public void setUploadDir(String uplaod_dir){
        this.uplaod_dir = uplaod_dir;
    }

}
