package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class ParkingLotAndDates {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String parkingLotLocation;

    @Size(max = 40)
    private String parkingLotName;

    private String regNo;

    private Integer totalCapacity;

    private Integer availableSpace = 5;


    @Column(length = 60)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;


    @OneToMany(mappedBy = "parkingLotAndDates", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ParkingDetails> parkingDetails;


}

/**
 * table of parking lot with date attached
 */