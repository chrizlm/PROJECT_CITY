package com.chris.cityparking.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ParkingLotToDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingLotId;

    private String parkingRegNo;

    private String parkingLotLocation;

    //unique
    private String parkingLotName;

    private int totalParkingSpaces;
}
