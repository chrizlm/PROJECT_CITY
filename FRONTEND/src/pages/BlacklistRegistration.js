import {Box, Button, Grid, Paper,} from '@mui/material';
import {makeStyles} from "@mui/styles";
import {Form} from "../components/UseForm";
import Controls from "../components/controls/Controls";
import Avatar from '@mui/material/Avatar';
import React, {useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import BlackListServices from "../service/BlackListServices";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import DataService from "../service/DataService";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import DatePicker from "@mui/lab/DatePicker";
import TimePicker from "@mui/lab/TimePicker";
import moment from "moment";


const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },

});




const initialFieldValues = {
    id: 0,
    numberPlate: "",
    location: "",
    parkingLotName: "",
    dateOfIssue: "",
    timeOfIssue: "",
    remarks: "",
};



export default function BlacklistRegistration(){
    const [blackListDetail, setBlackListDetail] = useState(initialFieldValues);
    const [selected, setSelected] = React.useState("");
    const [searchParkingLot, setSearchParkingLot] = useState("");
    const [locationData, setLocationData] = useState([]);
    const [parkingLotData, setParkingLotData] = useState([]);
    const [value, setValue] = React.useState(null);





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

    const getPkLotList = ({item}) => {

        if(item === searchParkingLot) {
            DataService.getParkingData(item).then(response => {
                setParkingLotData(response.data);
                console.log(response);
            }).catch(error => {
                console.log(error)
            })
        }


    };


    const handleInputChange = e =>{

        const {name, value} = e.target
        setBlackListDetail({
            ...blackListDetail,
            [name]: value,
        })
    }

    const handleOptionOne = e =>{


        const {name, value} = e.target
        setSelected(value)
        setBlackListDetail({
            ...blackListDetail,
            [name]: value,
        })


        //const searchParkingLot = e.target.value;
        setSearchParkingLot(e.target.value);

    }

    const handleOptionTwo = e =>{
        const {name, value} = e.target
        setBlackListDetail({
            ...blackListDetail,
            [name]: value,
        })
    }


    const handleSubmit = () =>{
        const submittedDate = moment(blackListDetail.dateOfIssue).format('YYYY-MM-DD')
        const submittedTime = moment(blackListDetail.timeOfIssue).format('HH:mm')
        const data = {
            numberPlate: blackListDetail.numberPlate.toUpperCase(),
            location: blackListDetail.location.toUpperCase(),
            parkingLotName: blackListDetail.parkingLotName.toUpperCase(),
            dateOfIssue: submittedDate,
            timeOfIssue: submittedTime,
            remarks: blackListDetail.remarks,
        }

        BlackListServices.registerDefaulter(data).then(response =>{
            alert("blacklisted motorist");
            console.log(response);
        }).catch(error =>{
            console.log(error)
        });
    };






    const classes = useStyles();

    return(
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
                    <Avatar /> {/*name*/}
                    <Form onSubmit={handleSubmit}>
                        <Grid container>
                            <Grid item xs={6}>

                                <TextField
                                    variant="outlined"
                                    label="Remarks"
                                    placeholder="Remarks"
                                    multiline
                                    rows={7}
                                    maxRows={7}
                                    name="remarks"
                                    value={blackListDetail.remarks}
                                    onChange={handleInputChange}
                                />
                            </Grid>
                            <Grid item xs={6}>
                                <Controls.Input
                                    variant="outlined"
                                    label="Number Plate"
                                    name="numberPlate"
                                    value={blackListDetail.numberPlate}
                                    onChange={handleInputChange}
                                />




                                <LocalizationProvider dateAdapter={AdapterDateFns}>
                                    <DatePicker
                                        label="Date of Issue"
                                        value={value}

                                        onChange={(date) => {
                                            setValue(date);

                                            setBlackListDetail({
                                                ...blackListDetail,
                                                dateOfIssue : date,
                                            })
                                        }}
                                        renderInput={(params) => <TextField {...params}
                                                                            name="parkingDate" value={blackListDetail.dateOfIssue}
                                        />}
                                    />
                                </LocalizationProvider>



                                <LocalizationProvider dateAdapter={AdapterDateFns}>
                                    <TimePicker
                                        label="Time of Issue"
                                        placeholder="Time of Issue"
                                        value={value}
                                        onChange={(date) => {
                                            setValue(date);

                                            setBlackListDetail({
                                                ...blackListDetail,
                                                timeOfIssue : date,
                                            })
                                        }}
                                        renderInput={(params) => <TextField {...params}
                                                                            name="parkTime" value={blackListDetail.timeOfIssue}/>}
                                    />
                                </LocalizationProvider>

                                <FormControl fullWidth>
                                    <InputLabel id="dependant-dropdown">location</InputLabel>
                                    <Select
                                        labelId="dependant-dropdown"
                                        id="dependant-dropdown"
                                        name="location"
                                        value={blackListDetail.location}
                                        label="Location"
                                        onChange={handleOptionOne}
                                    >{locationData.map((item ,index) =>
                                        <MenuItem value={item} key={index}

                                                  onClick={getPkLotList({item})}>{item}</MenuItem>)}
                                    </Select>
                                </FormControl>
                                <FormControl>
                                    <InputLabel id="dropdown">Park Lot</InputLabel>
                                    <Select
                                        labelId="dropdown"
                                        id="dropdown"
                                        name="parkingLotName"
                                        value={blackListDetail.parkingLotName}
                                        label="Park Lot"
                                        onChange={handleOptionTwo}>{
                                        parkingLotData.map((item, index) =>
                                            <MenuItem value={item} key={index} >{item}</MenuItem> )
                                    }</Select>
                                </FormControl>

                            </Grid>

                        </Grid>
                        <Button variant="contained"
                                fullWidth
                                onClick={handleSubmit}
                        >Submit</Button>
                    </Form>
                </Paper>
            </Box>
        </>
    )
}