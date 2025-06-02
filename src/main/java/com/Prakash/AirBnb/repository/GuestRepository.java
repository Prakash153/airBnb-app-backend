package com.Prakash.AirBnb.repository;

import com.Prakash.AirBnb.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
