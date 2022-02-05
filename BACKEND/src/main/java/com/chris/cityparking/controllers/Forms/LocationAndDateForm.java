package com.chris.cityparking.controllers.Forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LocationAndDateForm {
    private String location;
    private String parkingLotName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date parkingDate;
}

/**
 * used on the controller for the body request
 */