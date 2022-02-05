//list of booking details
import {Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,} from '@mui/material';
import {makeStyles} from "@mui/styles";
import Controls from "../components/controls/Controls";
import React, {useEffect, useState} from 'react';
import ParkingDetailService from "../service/ParkingDetailService";
import BookingService from "../service/BookingService";
import BlackListServices from "../service/BlackListServices";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import {DialogActions} from "@mui/material";
import moment from "moment";
import AuthService from "../service/auth.service";

const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },
    detailDisplay : {
    textAlign : 'center',
},
    buttondelete:{
        backgroundColor : "red",
    },
    twobuttons:{
        flexDirection: "row" ,
        marginLeft: 20,
        justifyContent: 'space-evenly'
    }


});

export default function BlackListAdmin(){

    const [blackListDetails, setBlackListDetails] = React.useState([]);
    const [searchLocation, setSearchLocation] = React.useState("");
    const [numberPlate, setNumberPlate]= React.useState("");
    const [currentBookingDetailsList, setCurrentBookingDetailsList] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [open, setOpen] = React.useState(false);


    useEffect(() =>{
        retrieveAllDefaulters();
    }, []);

    const onChangeSearchNumberPlate = e =>{
        const numberPlate = e.target.value;
        setNumberPlate(numberPlate);
    }

    const retrieveAllDefaulters = () =>{
        BlackListServices.getAllDefaulters().then(response => {
            setBlackListDetails(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }





    const findDefaulterByNumberPlate = () =>{

        BlackListServices.getDefaulter(numberPlate.toUpperCase()).then(response =>{
            setBlackListDetails(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }



    const viewDetails = () =>{
        setOpen(true);

    }

    const handleClose = () => {
        setOpen(false);
    };

    const handleDelete = (numberPlate) => {
        BlackListServices.removeDefaulter(numberPlate).then(response =>{
            alert("motorist removed from blacklist");
            refreshList();
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
        setOpen(false);
    };

    const refreshList = () => {
        setCurrentIndex(-1);
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
                                onClick={findDefaulterByNumberPlate}>Search</Button>
                    </div>
                    <div align="right">
                        <Button variant="contained"
                                onClick={retrieveAllDefaulters}>GetAll</Button>
                    </div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>numberPlate</TableCell>
                                    <TableCell align="right">location</TableCell>
                                    <TableCell align="right">parkingLotName</TableCell>
                                    <TableCell align="right">Date</TableCell>
                                    <TableCell align="right">Time</TableCell>
                                    <TableCell align="right">Remarks</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {blackListDetails.map((row, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    >
                                        <TableCell>{row.numberPlate}</TableCell>
                                        <TableCell align="right">{row.location}</TableCell>
                                        <TableCell align="right">{row.parkingLotName}</TableCell>
                                        <TableCell align="right">{row.dateOfIssue}</TableCell>
                                        <TableCell align="right">{row.timeOfIssue}</TableCell>
                                        <TableCell align="right">
                                        <Button variant="contained"
                                                fullWidth
                                                onClick={viewDetails}
                                        >View Issue</Button>
                                        <Dialog
                                            open={open}
                                            onClose={handleClose}
                                            aria-labelledby="alert-dialog-title"
                                            aria-describedby="alert-dialog-description"
                                        >
                                            <DialogTitle id="alert-dialog-title">
                                                {"Parking Charges"}
                                            </DialogTitle>
                                            <DialogContent>
                                                <DialogContentText id="alert-dialog-description" className={classes.detailDisplay}>
                                                    {row.remarks}
                                                </DialogContentText>
                                                <DialogActions >
                                                <Button variant="contained"
                                                        fullWidth
                                                        onClick={handleClose}
                                                >RETAIN</Button>
                                                <Button variant="contained"
                                                        fullWidth
                                                        className={classes.buttondelete}
                                                        onClick={() => handleDelete(row.numberPlate)}
                                                >DELETE</Button>
                                                </DialogActions>
                                            </DialogContent>
                                        </Dialog>
                                        </TableCell>
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