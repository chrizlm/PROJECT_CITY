package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ParkingDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long parkingDetailID;

    private String numberPlate;

    private String vehicleType;

    private String location;

    //will have to get fixed select list to min error
    private String parkingLotName;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC+3")
    private Date parkingDate;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm", timezone = "UTC+3")
    private Date parkTime;

    private int parkDuration;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm", timezone = "UTC+3")
    private Date expiryParkTime;

    private double amountPaid;


    @ManyToOne
    @JoinColumn(name="parkinglot_id")
    @JsonBackReference
    private ParkingLotAndDates parkingLotAndDates;


}

/**
 * basic needed information to store as booking
 * attach a parking lot and date of the record to it
 */