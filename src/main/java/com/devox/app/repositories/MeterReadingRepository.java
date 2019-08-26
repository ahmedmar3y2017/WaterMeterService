package com.devox.app.repositories;

import com.devox.app.model.Entities.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {
}
