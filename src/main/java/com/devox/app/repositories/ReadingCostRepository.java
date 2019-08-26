package com.devox.app.repositories;

import com.devox.app.model.Entities.ReadingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingCostRepository  extends JpaRepository<ReadingCost, Integer> {
}
