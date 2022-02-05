//contains all the list of parking lots


import PerfectScrollbar from 'react-perfect-scrollbar';
import {Box, Button, Card, CardHeader, Table, TableBody, TableCell, TableHead, TableRow,} from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import {Form} from "../components/UseForm";
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';
import React, {useEffect, useState} from 'react'
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import ParkingLotService from "../service/ParkingLotService";
import DataService from "../service/DataService";


export default function ParkingLotDetails(props){
    const [parkLot, setParkLot] = React.useState([]);
    const [searchLocation, setSearchLocation] = React.useState("");
    const [locationData, setLocationData] = useState([]);
    const [currentParkingList, setCurrentParkingList] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    useEffect(() =>{
        retrieveAllParkingLotsAreaData();
    }, []);

    const retrieveAllParkingLotsAreaData = () =>{
        DataService.getLocData().then(response => {
            setLocationData(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }

    const refreshList = () =>{
        retrieveAllParkingLots();
        setCurrentParkingList(null);
        setCurrentIndex(-1);
    }



    useEffect(() =>{
        retrieveAllParkingLots();
    }, []);

    const retrieveAllParkingLots = () =>{
        ParkingLotService.getAll().then(response => {
            setParkLot(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }

    const onChangeSearchLocation = e =>{
        const searchLocation = e.target.value;
        setSearchLocation(searchLocation);
    }

    const findParkingLotByLocation = () =>{
        ParkingLotService.findByLocation(searchLocation.toUpperCase()).then(response => {
            setParkLot(response.data);
            console.log(response);
            console.log(setParkLot);
            refreshList();
        })
            .catch(error => {
                console.log(error)
            })
    }




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

                <Form>

                    <FormControl fullWidth>
                        <InputLabel id="parkings">Find parking lots</InputLabel>
                        <Select
                            labelId="parkings"
                            id="parkings"
                            name="location"
                            value={searchLocation}
                            label="Find parking lots by Area"
                            onChange={onChangeSearchLocation}
                        >{locationData.map((item ,index) =>
                            <MenuItem value={item} key={index}>{item}</MenuItem>)}
                        </Select>
                        <Button variant="contained"
                                fullWidth
                                onClick={findParkingLotByLocation}
                        >Search</Button>
                    </FormControl>



                    <div align="right">
                        <Button variant="contained"
                                onClick={retrieveAllParkingLots}>GetAll</Button>
                    </div>

                </Form>

                <Card {...props}>
                    <CardHeader title="Parking lots" />
                    <PerfectScrollbar>
                        <Box sx={{ minWidth: 800 }}>
                            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>
                                            parkingLotId
                                        </TableCell>
                                        <TableCell>
                                            parkingLotName
                                        </TableCell>
                                        <TableCell>
                                            totalParkingSpaces
                                        </TableCell>


                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {parkLot.map((park) =>(
                                        <TableRow
                                            hover
                                            key={park.parkingLotId}
                                        >
                                            <TableCell>
                                                {park.parkingLotId}
                                            </TableCell>
                                            <TableCell>
                                                {park.parkingLotName}
                                            </TableCell>
                                            <TableCell>
                                                {park.totalParkingSpaces}
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </Box>
                    </PerfectScrollbar>
                    <Box
                        sx={{
                            display: 'flex',
                            justifyContent: 'flex-end',
                            p: 2
                        }}
                    >
                        <Button
                            color="primary"
                            endIcon={<ArrowRightIcon fontSize="small" />}
                            size="small"
                            variant="text"
                        >
                            View all
                        </Button>
                    </Box>

                </Card>
            </Box>
            </>
    )
}