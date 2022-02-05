import * as React from 'react';
import {useEffect, useState} from 'react';
import {Box, Button, Card, CardContent, CardHeader, Divider, Grid, Paper, TextField} from '@mui/material';
import AuthService from "../service/auth.service";
import Stack from "@mui/material/Stack";
import Avatar from "@mui/material/Avatar";
import Typography from "@mui/material/Typography";
import AttendantService from "../service/AttendantService";
import {makeStyles} from "@mui/styles";

const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },

});

const initialFieldValues = {
    id: 0,
    firstName: "",
    lastName: "",
    email: "",
    gender: "",
    password: "",
    areaAssigned: "",

};

const passwordInit = {
    password: "",
    confirm: ""
}

export default function AccountSettingsAttendant(){
    const [userDetails , setUserDetails] = useState(initialFieldValues);
    const [values, setValues] = useState(passwordInit);



    useEffect(() =>{
        const user = AuthService.getCurrentUser();


        if(user){
            const isAttendant = JSON.parse(atob(user.split('.')[1])).roles[0] === 'ROLE_ATTENDANT';
            const attendantEmail = JSON.parse(atob(user.split('.')[1])).sub;
            if(isAttendant){
                AttendantService.getAttendantInfo(attendantEmail).then(response => {
                    setUserDetails(response.data);
                    console.log(response);
                }).catch(error => {
                    console.log(error)
                })
            }
        }
    }, []);


    const handleSubmit = (event) =>{
        event.preventDefault();
        const data = {
            email: userDetails.email,
            newpassword: values.password,
        }


        const isConfirmedPassword = values.password === values.confirm;

        if(isConfirmedPassword){
            AttendantService.changePassword(data).then(response =>{
                alert("password changed");
                console.log(response);
            }).catch(error =>{
                console.log(error)
            });
        }

    };





    const handleChange = (event) => {
        setValues({
            ...values,
            [event.target.name]: event.target.value
        });
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
                    <Grid container>
                        <Grid item xs={6}>
                <Card sx={{ maxWidth: 500 }}>
                    <CardHeader
                        subheader="Account Information"
                        title="Attendant Details"
                    />
                    <Divider />
                    <CardContent>
                        <Stack direction="row" spacing={2}>
                            <Avatar>T</Avatar>
                        </Stack>
                        <Typography gutterBottom variant="h5" component="div">
                            {userDetails.firstName}
                        </Typography>

                        <Typography variant="body1" color="text.secondary">
                            <Grid item xs={9}>
                                <b>First Name:</b> {userDetails.firstName}<br/>
                                <b>Last Name:</b> {userDetails.lastName}<br/>
                                <b>Gender:</b> {userDetails.gender}<br/>
                                <b>Email:</b> {userDetails.email}<br/>
                                <b>AreaAssigned:</b> {userDetails.areaAssigned}<br/>
                            </Grid>
                        </Typography>
                    </CardContent>

                </Card>
                        </Grid>
                        <Grid item xs={6}>
                            <form onSubmit={handleSubmit}>
                                <Card>
                                    <CardHeader
                                        subheader="Update password"
                                        title="Password"
                                    />
                                    <Divider />
                                    <CardContent>
                                        <TextField
                                            fullWidth
                                            label="Password"
                                            margin="normal"
                                            name="password"
                                            onChange={handleChange}
                                            type="password"
                                            value={values.password}
                                            variant="outlined"
                                        />
                                        <TextField
                                            fullWidth
                                            label="Confirm password"
                                            margin="normal"
                                            name="confirm"
                                            onChange={handleChange}
                                            type="password"
                                            value={values.confirm}
                                            variant="outlined"
                                        />
                                    </CardContent>
                                    <Divider />
                                    <Box
                                        sx={{
                                            display: 'flex',
                                            justifyContent: 'flex-end',
                                            p: 2
                                        }}
                                    >
                                        <Button
                                            color="primary"
                                            variant="contained"
                                            onClick={handleSubmit}
                                        >
                                            Update
                                        </Button>
                                    </Box>
                                </Card>
                            </form>
                        </Grid>
                    </Grid>
                </Paper>
            </Box>
            </>
    );
};