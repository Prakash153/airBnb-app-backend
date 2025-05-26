package com.Prakash.AirBnb.repository;

import com.Prakash.AirBnb.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory , Long> {
}
