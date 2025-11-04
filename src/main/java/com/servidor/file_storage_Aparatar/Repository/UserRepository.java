package com.servidor.file_storage_Aparatar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servidor.file_storage_Aparatar.Model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long> {
    
}
