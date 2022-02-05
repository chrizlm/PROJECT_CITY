import {
    Box,
    Button,
    Grid,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from '@mui/material';
import {makeStyles} from "@mui/styles";
import {Form} from "../components/UseForm";
import Controls from "../components/controls/Controls";
import Avatar from '@mui/material/Avatar';
import React, {useEffect, useState} from 'react';
import ParkingLotService from "../service/ParkingLotService";
import AttendantService from "../service/AttendantService";


const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    }
    ,
    buttondelete:{
    backgroundColor : "red",
},

});


const initialFieldValues = {
    parkingLotId: 0,
    parkingRegNo: "",
    parkingLotLocation: "",
    parkingLotName: "",
    totalParkingSpaces: 0,

};


export default function ParkingLot() {
    const [parkDetail, setParkDetail] = useState(initialFieldValues);
    const [parkLot, setParkLot] = React.useState([]);
    const [searchLocation, setSearchLocation] = React.useState("");
    const [currentParkingList, setCurrentParkingList] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);


    useEffect(() => {
        retrieveAllParkingLots();
    }, []);

    const onChangeSearchLocation = e => {
        const searchLocation = e.target.value;
        setSearchLocation(searchLocation);
    }

    const saveParkingLot = () => {
        const data = {
            parkingRegNo: parkDetail.parkingRegNo.toUpperCase(),
            parkingLotLocation: parkDetail.parkingLotLocation.toUpperCase(),
            parkingLotName: parkDetail.parkingLotName.toUpperCase(),
            totalParkingSpaces: parkDetail.totalParkingSpaces,
        };
        ParkingLotService.create(data).then(response => {
            alert("Parking lot registered");
            console.log(response)
        })
            .catch(error => {
                alert("error in registration");
                console.log(error)
            })
    }


    const retrieveAllParkingLots = () => {

        ParkingLotService.getAll().then(response => {
            setParkLot(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }

    const refreshList = () => {
        retrieveAllParkingLots();
        setCurrentParkingList(null);
        setCurrentIndex(-1);
    }


    const findParkingLotByLocation = () => {

        ParkingLotService.findByLocation(searchLocation.toUpperCase()).then(response => {
            setParkLot(response.data);
            console.log(response);
            console.log(setParkLot);
        })
            .catch(error => {
                console.log(error)
            })
    }


    const removeParkingLot = () => {
        ParkingLotService.removeAll().then(response => {
            console.log(response.data);
            refreshList();
        })
            .catch(e => {
                console.log(e);
            });
    }


    const handleInputChange = e => {

        const {name, value} = e.target
        setParkDetail({
            ...parkDetail,
            [name]: value,
        })

    }

    const handleDelete = (regNumber) => {
        ParkingLotService.remove(regNumber).then(response =>{
            alert("parking lot removed");
            refreshList();
            console.log(response);
        }).catch(error => {
            console.log(error)
        })

    };



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
                    <Avatar/> {/*name*/}
                    <Form onSubmit={saveParkingLot}>
                        <Grid container>
                            <Grid item xs={6}>
                                <Controls.Input
                                    variant="outlined"
                                    label="Parking LotReg"
                                    name="parkingRegNo"
                                    value={parkDetail.parkingRegNo}
                                    onChange={handleInputChange}
                                />

                                <Controls.Input
                                    variant="outlined"
                                    label="Parking Location"
                                    name="parkingLotLocation"
                                    value={parkDetail.parkingLotLocation}
                                    onChange={handleInputChange}
                                />

                                <Controls.Input
                                    variant="outlined"
                                    label="Parking Lot name"
                                    name="parkingLotName"
                                    value={parkDetail.parkingLotName}
                                    onChange={handleInputChange}
                                />

                                <Controls.Input
                                    variant="outlined"
                                    label="Parking Lot spaces"
                                    name="totalParkingSpaces"
                                    value={parkDetail.totalParkingSpaces}
                                    onChange={handleInputChange}
                                />


                            </Grid>

                            <Button variant="contained"
                                    fullWidth
                                    onClick={saveParkingLot}
                            >Submit</Button>

                        </Grid>

                    </Form>
                </Paper>
            </Box>
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
                            label="Search Parking Lot"
                            value={searchLocation}
                            onChange={onChangeSearchLocation}
                        />
                        <Button variant="contained"
                                onClick={findParkingLotByLocation}>Search</Button>
                    </div>
                    <div align="right">
                        <Button variant="contained"
                                onClick={retrieveAllParkingLots}>GetAll</Button>
                    </div>
                    <TableContainer component={Paper}>
                        <Table sx={{minWidth: 650}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Reg no.</TableCell>
                                    <TableCell align="right">location</TableCell>
                                    <TableCell align="right">Name</TableCell>
                                    <TableCell align="right">Total Space</TableCell>
                                    <TableCell align="right">Action</TableCell>

                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {parkLot.map((row, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                    >
                                        <TableCell>{row.parkingRegNo}</TableCell>
                                        <TableCell align="right">{row.parkingLotLocation}</TableCell>
                                        <TableCell align="right">{row.parkingLotName}</TableCell>
                                        <TableCell align="right">{row.totalParkingSpaces}</TableCell>
                                        <TableCell align="right"><Button variant="contained"
                                                                         fullWidth
                                                                         className={classes.buttondelete}
                                                                         onClick={() => handleDelete(row.parkingRegNo)}
                                        >DELETE</Button></TableCell>

                                    </TableRow>
                                ))
                                }

                            </TableBody>
                        </Table>
                    </TableContainer>
                    <div align="right">
                        <Button variant="contained"
                                onClick={removeParkingLot}>DeleteAll</Button>
                    </div>

                </Paper>
            </Box>
        </>
    )
}

