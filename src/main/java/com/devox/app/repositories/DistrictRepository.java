package com.devox.app.repositories;

import com.devox.app.model.Entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {



}
