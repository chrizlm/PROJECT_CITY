import PerfectScrollbar from 'react-perfect-scrollbar';
import {
    Box,
    Button,
    Card,
    CardHeader,
    Grid,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
} from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import {makeStyles} from "@mui/styles";
import {Form} from "../components/UseForm";
import Controls from "../components/controls/Controls";
import Avatar from '@mui/material/Avatar';
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';
import React, {useEffect, useState} from 'react'
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import TextField from "@mui/material/TextField";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import DatePicker from '@mui/lab/DatePicker';
import TimePicker from "@mui/lab/TimePicker";
import ParkingDetailService from "../service/ParkingDetailService";
import DataService from "../service/DataService";
import moment from "moment";
import ParkingLotsWithDatesService from "../service/ParkingLotsWithDatesService";
import AuthService from "../service/auth.service";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import Dialog from "@mui/material/Dialog";


const useStyles = makeStyles({
  pageContent: {
    margin: "40px",
    padding: "24px",
  },
    formlogin : {
        textAlign : 'center',
    }
 
});


  const initialFieldValues = {
    id: 0,
    numberPlate: "",
    vehicleType: "",
    location: "",
    parkingLotName: "",
    parkingDate: "",
    parkTime: "",
    parkDuration: "",
   
  };

  const initialSearchDetailsValues = {
      location: "",
      parkingLotName: "",
      parkingDate: "",
  }



export default function Bookings(props) {
      const [parkDetail, setParkDetail] = useState(initialFieldValues);
  const [searchParkDetails, setSearchParkDetails] = useState(initialSearchDetailsValues);
  const [selected, setSelected] = React.useState("");
  const [value, setValue] = React.useState(null);
    const [searchValue, setSearchValue] = React.useState(null);
    const [parkLot, setParkLot] = React.useState([]);
  const [searchLocation, setSearchLocation] = React.useState("");
  const [locationData, setLocationData] = useState([]);
  const [parkingLotData, setParkingLotData] = useState([]);
  const [searchParkingLot, setSearchParkingLot] = useState("");
  const [freeSpace, setFreeSpace] = useState(0);
    const [open, setOpen] = React.useState(false);
    const [amount, setAmount] = useState(0);




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




  const getPkLotList = (item) => {

      if(item === searchParkingLot) {
          DataService.getParkingData(item).then(response => {
              setParkingLotData(response.data);
              console.log(response);
          }).catch(error => {
              console.log(error)
          })
      }


  };


   




  const retrieveAllParkingLots = () =>{
      const currentDate = Date.now();
      const submittedDate = moment(currentDate).format('YYYY-MM-DD')

      ParkingLotsWithDatesService.getByDateOnly(submittedDate).then(response => {
      setParkLot(response.data);
      console.log(response);
    }).catch(error => {
      console.log(error)
    })
  }

  const handleInputChange = e =>{
    const {name, value} = e.target
    setParkDetail({
      ...parkDetail,
      [name]: value,
    })


  }

   const handleOptionOne = e =>{


       const {name, value} = e.target
     setSelected(value)
     setParkDetail({
       ...parkDetail,
       [name]: value,
     })


     const searchParkingLot = e.target.value;
     setSearchParkingLot(searchParkingLot);

   }



    const handleOptionOneSearch = e =>{
        const {name, value} = e.target
        setSelected(value)
        setSearchParkDetails({
            ...searchParkDetails,
            [name]: value,
        })


        //const searchParkingLot = e.target.value;
        setSearchParkingLot(e.target.value);

    }
  const handleOptionTwo = e =>{
    const {name, value} = e.target
    setParkDetail({
      ...parkDetail,
      [name]: value,
    })
  }

    const handleOptionTwoSearch = e =>{
        const {name, value} = e.target
        setSearchParkDetails({
            ...searchParkDetails,
            [name]: value,
        })
    }





  const saveParkingDetail = () =>{

    const submittedDate = moment(parkDetail.parkingDate).format('YYYY-MM-DD')
    const submittedTime = moment(parkDetail.parkTime).format('HH:mm')
      const user = AuthService.getCurrentUser();
      const motoristEmail = JSON.parse(atob(user.split('.')[1])).sub;
    const data ={
      numberPlate: parkDetail.numberPlate.toUpperCase(),
      vehicleType: parkDetail.vehicleType,
      location: parkDetail.location.toUpperCase(),
      parkingLotName: parkDetail.parkingLotName.toUpperCase(),
      parkingDate: submittedDate,
      parkTime: submittedTime,
      parkDuration: parkDetail.parkDuration,

    };
      ParkingDetailService.checkAmount(data).then(response => {
          console.log(response)
          setAmount(response.data);
      }).catch(error => {
          console.log(error)
      });
    ParkingDetailService.checkBookingSpace(data).then(response =>{
      console.log(response)
      setFreeSpace(response.data);

      if(response.data >= 0){
        ParkingDetailService.create(data, motoristEmail).then(response => {
            alert("booking successful");
          console.log(response)
        })
            .catch(error => {
                alert("error booking");
              console.log(error)
            })
      }else{
        alert("error submitting data");
      }
    })

      setOpen(true);

  }





    const findParkingLotByLocationAndDate = () =>{

        const submittedDate = moment(searchParkDetails.parkingDate).format('YYYY-MM-DD')
        const data ={

            location: searchParkDetails.location,
            parkingLotName: searchParkDetails.parkingLotName,
            parkingDate: submittedDate,

        };

        ParkingLotsWithDatesService.getParkingDataSearch(data).then(response => {
            setParkLot(response.data);
            console.log(response);
            console.log(setParkLot);
        })
            .catch(error => {
                console.log(error)
            })
    }

    const handleClose = () => {
        setOpen(false);
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
        <Avatar /> {/*name*/}
      <Form onSubmit={saveParkingDetail}>
      <Grid container>
        <Grid item xs={6}>
          <Controls.Input
            variant="outlined"
            label="Number Plate"
            name="numberPlate"
            value={parkDetail.numberPlate}
            onChange={handleInputChange}
          />



          <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label">CarType</InputLabel>
            <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select-label"
                name="vehicleType"
                value={parkDetail.vehicleType}
                label="Vehicle Type"
                onChange={handleInputChange}
            >
                <MenuItem value='Motorcycle'>Motorcycle</MenuItem>
                <MenuItem value='Tricycle'>Tricycle(Tuktuk)</MenuItem>
                <MenuItem value='Car'>Car</MenuItem>
                <MenuItem value='Lorry'>Lorry</MenuItem>
            </Select>
          </FormControl>





          <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DatePicker
                label="Booking Date"
                value={value}

                onChange={(date) => {
                  setValue(date);

                  setParkDetail({
                    ...parkDetail,
                    parkingDate : date,
                  })
                }}
                renderInput={(params) => <TextField {...params}
                name="parkingDate" value={parkDetail.parkingDate}
                />}
            />
          </LocalizationProvider>



          <LocalizationProvider dateAdapter={AdapterDateFns}>
            <TimePicker
                label="Booking time"
                placeholder="Booking Time"
                value={value}
                onChange={(date) => {
                  setValue(date);

                  setParkDetail({
                    ...parkDetail,
                    parkTime : date,
                  })
                }}
                renderInput={(params) => <TextField {...params}
                    name="parkTime" value={parkDetail.parkTime}/>}
            />
          </LocalizationProvider>






        </Grid>

        <Grid item xs={6}>




          <FormControl fullWidth>
            <InputLabel id="dependant-dropdown">location</InputLabel>
            <Select
                labelId="dependant-dropdown"
                id="dependant-dropdown"
                name="location"
                value={parkDetail.location}
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
                value={parkDetail.parkingLotName}
                label="Park Lot"
                onChange={handleOptionTwo}>{
                  parkingLotData.map((item, index) =>
                  <MenuItem value={item} key={index} >{item}</MenuItem> )
            }</Select>
          </FormControl>


          <Controls.Input
              variant="outlined"
              label="Park Duration"
              name="parkDuration"
              value={parkDetail.parkDuration}
              onChange={handleInputChange}
          />
          </Grid>
        </Grid>
        <Button variant="contained"
        fullWidth
                onClick={saveParkingDetail}
        >Submit</Button>
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
                  <DialogContentText id="alert-dialog-description" className={classes.formlogin}>
                      Charges for this parking is : {amount}
                      </DialogContentText>
                  <Button variant="contained"
                          fullWidth
                          onClick={handleClose}
                  >OK</Button>
              </DialogContent>
          </Dialog>

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

                <CardHeader title="SEARCH FOR PARKING LOTS AND AVAILABLE SPACES" />
                    <Box sx={{ minWidth: 800 }}>

        <Form onSubmit={findParkingLotByLocationAndDate}>


            <LocalizationProvider dateAdapter={AdapterDateFns}>
                <DatePicker
                    label="Search Date"
                    Value={searchValue}

                    onChange={(date) => {
                        setValue(date);

                        setSearchParkDetails({
                            ...searchParkDetails,
                            parkingDate : date,
                        })
                    }}
                    renderInput={(params) => <TextField {...params}
                                                        name="parkingDate" value={searchParkDetails.parkingDate}
                    />}
                />
            </LocalizationProvider>

            <FormControl fullWidth>
                <InputLabel id="dependant-dropdown">location</InputLabel>
                <Select
                    labelId="dependant-dropdown"
                    id="dependant-dropdown"
                    name="location"
                    value={searchParkDetails.location}
                    label="Location"
                    onChange={handleOptionOneSearch}
                >{locationData.map((item ,index) =>
                    <MenuItem value={item} key={index}
                              onClick={getPkLotList(item)}>{item}</MenuItem>)}
                </Select>
            </FormControl>
            <FormControl>
                <InputLabel id="dropdown">Park Lot</InputLabel>
                <Select
                    labelId="dropdown"
                    id="dropdown"
                    name="parkingLotName"
                    value={searchParkDetails.parkingLotName}
                    label="Park Lot"
                    onChange={handleOptionTwoSearch}>{
                    parkingLotData.map((item, index) =>
                        <MenuItem value={item} key={index} >{item}</MenuItem> )
                }</Select>
            </FormControl>

          <FormControl fullWidth>

              <Button variant="contained"
                      fullWidth
                      onClick={findParkingLotByLocationAndDate}
              >Search</Button>



          </FormControl>



          <div align="right">
            <Button variant="contained"
                    onClick={retrieveAllParkingLots}>Today</Button>
          </div>

        </Form>
                    </Box>






    <CardHeader title="Parking lots" />

          <PerfectScrollbar>
            <Box sx={{ minWidth: 800 }}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>

                    <TableCell>
                      parkingLotName
                    </TableCell>
                    <TableCell>
                      totalParkingSpaces
                    </TableCell>
                    <TableCell>
                      AvailableSpaces
                    </TableCell>

                  </TableRow>
                </TableHead>
                <TableBody>
                  {
                    parkLot.map((park) =>(

                        <TableRow
                            hover
                            key={park.parkingLotId}
                        >

                          <TableCell>
                            {park.parkingLotName}
                          </TableCell>
                          <TableCell>
                            {park.totalCapacity}
                          </TableCell>
                          <TableCell>
                            {park.availableSpace}
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


            </Paper>
        </Box>
  </>
    )
}


