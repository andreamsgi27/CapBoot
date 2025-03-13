package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language> {
    
}
