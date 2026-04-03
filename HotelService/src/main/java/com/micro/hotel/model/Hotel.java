package com.micro.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
   @Id
   private String id;
   private String name;
   private String location;
   private String about;
}
