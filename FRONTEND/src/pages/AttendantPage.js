//LIST OF ALL ATTENDANTS
import {
    Box,
    Button,
    DialogActions,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from '@mui/material';
import {makeStyles} from "@mui/styles";
import Controls from "../components/controls/Controls";
import React, {useEffect, useState} from 'react';
import AdminServices from "../service/AdminServices";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import {Form} from "../components/UseForm";
import BlackListServices from "../service/BlackListServices";
import AttendantService from "../service/AttendantService";

const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },
    buttondelete:{
        backgroundColor : "red",
    },

});

const initialFieldValues = {

    areaAssigned: ""

};


const initialAttendantValues = {
    attendantID: 0,
    firstName: "",
    lastName: "",
    email: "",
    gender: "",
    password: "",
    areaAssigned: ""

};

export default function AttendantPage(props) {
    const [attendants, setAttendants] = React.useState([]);
    const [searchLocation, setSearchLocation] = React.useState("");
    const [regEmail, setRegEmail]= React.useState("");
    const [currentAttendantsList, setCurrentAttendantsList] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [editAreaDetail, setEditAreaDetail] = useState(initialFieldValues);
    const [attendantDetail, setAttendantDetail] =useState(initialAttendantValues);
    const [open, setOpen] = React.useState(false);





    const handleInputChange = e =>{

        const {name, value} = e.target
        setEditAreaDetail({
            ...editAreaDetail,
            [name]: value,
        })
    }

    useEffect(() =>{
        retrieveAllAttendants();
    }, []);

    const onChangeSearchRegistrationNumber = e =>{
        const regEmail = e.target.value;
        setRegEmail(regEmail);
    }

    const retrieveAllAttendants = () =>{
        AdminServices.getAllAttendants().then(response => {
            setAttendants(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
    }





    const findAttendantByRegEmail = () =>{
        AttendantService.getAttendantInfoTwo(regEmail).then(response =>{
            setAttendants(response.data);
            console.log(response);
        }).catch(error => {
            console.log(error)
        })
    }

    const handleDelete = (email) => {
        AttendantService.removeAttendant(email).then(response =>{
            alert("attendant removed");
            refreshList();
            console.log(response);
        }).catch(error => {
            console.log(error)
        })

    };

    const handleEdit = (email) => {
        AttendantService.getAttendantInfo(email).then(response =>{
            setAttendantDetail(response.data);
            console.log(response);
            if(response.data){
                const data = {
                    attendantID: response.data.attendantID,
                    firstName: response.data.firstName.toUpperCase(),
                    lastName: response.data.lastName.toUpperCase(),
                    email: response.data.email,
                    gender: response.data.gender,
                    password: response.data.password,
                    areaAssigned: editAreaDetail.areaAssigned.toUpperCase(),
                }
                AttendantService.updateAttendant(data).then(response =>{
                    alert("attendant has been re-assigned");
                    refreshList();
                    console.log(response);
                }).catch(error => {
                    console.log(error)
                })
            }


        }).catch(error => {
            console.log(error)
        })

        setOpen(false);
    };

    const refreshList = () => {
        setCurrentIndex(-1);
    }

    const viewDetails = () =>{
        setOpen(true);

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
                    <div>
                        <Controls.Input
                            variant="outlined"
                            label="Search by Email"
                            value={regEmail}
                            onChange={onChangeSearchRegistrationNumber}
                        />
                        <Button variant="contained"
                                onClick={findAttendantByRegEmail}>Search</Button>
                    </div>
                    <div align="right">
                        <Button variant="contained"
                                onClick={retrieveAllAttendants}>GetAll</Button>
                    </div>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>

                                    <TableCell align="right">firstName</TableCell>
                                    <TableCell align="right">lastName</TableCell>
                                    <TableCell align="right">email</TableCell>
                                    <TableCell align="right">gender</TableCell>
                                    <TableCell align="right">areaAssigned</TableCell>
                                    <TableCell align="right">Edit</TableCell>
                                    <TableCell align="right">Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {attendants.map((row, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    >
                                        <TableCell>{row.firstName}</TableCell>
                                        <TableCell align="right">{row.lastName}</TableCell>
                                        <TableCell align="right">{row.email}</TableCell>
                                        <TableCell align="right">{row.gender}</TableCell>
                                        <TableCell align="right">{row.areaAssigned}</TableCell>
                                        <TableCell align="right">
                                            <Button variant="contained"
                                                    fullWidth
                                                    onClick={viewDetails}
                                            >Edit</Button>
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
                                                    <Form onSubmit={() => handleEdit(row.email)}>
                                                    <DialogContentText id="alert-dialog-description" className={classes.detailDisplay}>
                                                        RELOCATE ATTENDANT TO: <Controls.Input
                                                        variant="outlined"
                                                        label="Area Assigned"
                                                        name="areaAssigned"
                                                        value={editAreaDetail.areaAssigned}
                                                        onChange={handleInputChange}
                                                    />
                                                    </DialogContentText>
                                                    <DialogActions >

                                                        <Button variant="contained"
                                                                fullWidth
                                                                onClick={() => handleEdit(row.email)}
                                                        >OK</Button>
                                                    </DialogActions>
                                                    </Form>
                                                </DialogContent>
                                            </Dialog>
                                        </TableCell>
                                        <TableCell align="right"><Button variant="contained"
                                                                         fullWidth
                                                                         className={classes.buttondelete}
                                                                         onClick={() => handleDelete(row.email)}
                                        >DELETE</Button></TableCell>
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