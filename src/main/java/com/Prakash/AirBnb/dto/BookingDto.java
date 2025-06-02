package com.Prakash.AirBnb.dto;

import com.Prakash.AirBnb.entity.Guest;
import com.Prakash.AirBnb.entity.Hotel;
import com.Prakash.AirBnb.entity.Room;
import com.Prakash.AirBnb.entity.User;
import com.Prakash.AirBnb.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
//    private Hotel hotel;
//    private Room room;
//    private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
