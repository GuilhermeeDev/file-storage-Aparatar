package com.servidor.file_storage_Aparatar.Model;

import java.util.Date;

public class ErrorMessageEntity {
    
    private String message;
    private Date data;
    private String details;


    public ErrorMessageEntity(String message, Date data, String details){
        this.data = data;
        this.message = message;
        this.details = details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setDate(Date data){
        this.data = data;
    }

    public String getDetails(String details) {
        return this.details;
    }

    public String getMessage(){
        return this.message;
    }

    public Date getData(){
        return this.data;
    }
}
