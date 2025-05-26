package com.Prakash.AirBnb.repository;

import com.Prakash.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room , Long > {
}
