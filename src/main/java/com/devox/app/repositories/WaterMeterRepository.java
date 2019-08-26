package com.devox.app.repositories;

import com.devox.app.model.Entities.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterMeterRepository extends JpaRepository<WaterMeter, Integer> {
}
