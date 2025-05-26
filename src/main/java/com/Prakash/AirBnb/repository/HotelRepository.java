package com.Prakash.AirBnb.repository;

import com.Prakash.AirBnb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel , Long> {
}
