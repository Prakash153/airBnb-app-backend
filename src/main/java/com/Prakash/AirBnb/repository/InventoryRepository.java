package com.Prakash.AirBnb.repository;

import com.Prakash.AirBnb.entity.Inventory;
import com.Prakash.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory , Long> {
     void deleteByRoom( Room room);
}
