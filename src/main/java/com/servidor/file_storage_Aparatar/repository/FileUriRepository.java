package com.servidor.file_storage_Aparatar.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servidor.file_storage_Aparatar.models.File;

@Repository
public interface FileUriRepository extends JpaRepository <File, Long> {

}
