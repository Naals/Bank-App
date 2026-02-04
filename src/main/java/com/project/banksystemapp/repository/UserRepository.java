package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByStore(Store store);
    List<User> findByBranch(Branch branch);

}
