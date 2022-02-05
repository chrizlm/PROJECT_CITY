//list of booking details
import {Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,} from '@mui/material';
import {makeStyles} from "@mui/styles";
import Controls from "../components/controls/Controls";
import React, {useEffect, useState} from 'react';
import ParkingDetailService from "../service/ParkingDetailService";
import BookingService from "../service/BookingService";

const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },

});

export default function BookingDetails(){

    const [bookingDetails, setBookingDetails] = React.useState([]);
    const [searchLocation, setSearchLocation] = React.useState("");
    const [numberPlate, setNumberPlate]= React.useState("");
    const [currentBookingDetailsList, setCurrentBookingDetailsList] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    useEffect(() =>{
        retrieveAllBookingDetails();
    }, []);

    const onChangeSearchNumberPlate = e =>{
        const numberPlate = e.target.value;
        setNumberPlate(numberPlate);
    }

    const retrieveAllBookingDetails = () =>{
        ParkingDetailService.getAll().then(response => {
            setBookingDetails(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }





    const findParkingDetailsByNumberPlate = () =>{

        BookingService.get(numberPlate.toUpperCase()).then(response =>{
            setBookingDetails(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }

    const classes = useStyles();

    return (
        <>
            <Box
                sx={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    '& > :not(style)': {
                        m: 1,
                        width: '100%',
                        height: '100%',
                    },
                }}
            >
                <Paper elevation={3} className={classes.pageContent}>
                    <div>
                        <Controls.Input
                            variant="outlined"
                            label="Search by Number Plate"
                            value={numberPlate}
                            onChange={onChangeSearchNumberPlate}
                        />
                        <Button variant="contained"
                                onClick={findParkingDetailsByNumberPlate}>Search</Button>
                    </div>
                    <div align="right">
                        <Button variant="contained"
                                onClick={retrieveAllBookingDetails}>GetAll</Button>
                    </div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>numberPlate</TableCell>
                                    <TableCell align="right">vehicleType</TableCell>
                                    <TableCell align="right">location</TableCell>
                                    <TableCell align="right">parkingLotName</TableCell>
                                    <TableCell align="right">parkingDate</TableCell>
                                    <TableCell align="right">parkTime</TableCell>
                                    <TableCell align="right">parkDuration</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {bookingDetails.map((row, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    >
                                        <TableCell>{row.numberPlate}</TableCell>
                                        <TableCell align="right">{row.vehicleType}</TableCell>
                                        <TableCell align="right">{row.location}</TableCell>
                                        <TableCell align="right">{row.parkingLotName}</TableCell>
                                        <TableCell align="right">{row.parkingDate}</TableCell>
                                        <TableCell align="right">{row.parkTime}</TableCell>
                                        <TableCell align="right">{row.parkDuration}</TableCell>
                                    </TableRow>
                                ))
                                }

                            </TableBody>
                        </Table>
                    </TableContainer>


                </Paper>
            </Box>
        </>
    )

}