package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreAdminId(Long adminId);
}
