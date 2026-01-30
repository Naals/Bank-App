package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAllByStore(Store store);
}
