package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
