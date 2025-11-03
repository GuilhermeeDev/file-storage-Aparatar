package com.servidor.file_storage_Aparatar.Repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servidor.file_storage_Aparatar.Model.FileEntity;
@Repository
public interface FileStorageRepository extends JpaRepository <FileEntity, Long> {
	
}
