package com.Prakash.AirBnb.dto;

import com.Prakash.AirBnb.entity.User;
import com.Prakash.AirBnb.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
