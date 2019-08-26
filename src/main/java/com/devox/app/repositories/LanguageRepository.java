package com.devox.app.repositories;

import com.devox.app.model.Entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository  extends JpaRepository<Language, Integer> {



}
