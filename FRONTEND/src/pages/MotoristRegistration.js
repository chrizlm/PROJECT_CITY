import {Box, Button, Grid, Paper,} from '@mui/material';
import {makeStyles} from "@mui/styles";
import {Form} from "../components/UseForm";
import Controls from "../components/controls/Controls";
import Avatar from '@mui/material/Avatar';
import React, {useState} from 'react';
import MotoristService from "../service/MotoristService";
import TextField from "@mui/material/TextField";
import {useHistory} from "react-router-dom";


const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },

});

const genderItems = [
    { id: "male", title: "Male" },
    { id: "female", title: "Female" },
    { id: "others", title: "Others" },
];



const initialFieldValues = {
    id: 0,
    firstName: "",
    lastName: "",
    email: "",
    gender: "",
    password: ""

};



export default function MotoristRegistration(){
    const [motoristDetail, setMotoristDetail] =useState(initialFieldValues);

    const history = useHistory();

    const handleInputChange = e =>{

        const {name, value} = e.target
        setMotoristDetail({
            ...motoristDetail,
            [name]: value,
        })
    }


    const handleSubmit = (event) =>{
        event.preventDefault();
        const data = {
            //id: motoristDetail.id,
            firstName: motoristDetail.firstName.toUpperCase(),
            lastName: motoristDetail.lastName.toUpperCase(),
            email: motoristDetail.email,
            gender: motoristDetail.gender,
            password: motoristDetail.password,
        }

        MotoristService.registerMotorist(data).then(response =>{
            alert("successful registration");
            console.log(response);
            history.push("./Home");
        }).catch(error =>{
            alert("registration error");
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
                                <Controls.Input
                                    variant="outlined"
                                    label="First Name"
                                    name="firstName"
                                    value={motoristDetail.firstName}
                                    onChange={handleInputChange}
                                />
                                <Controls.Input
                                    variant="outlined"
                                    label="Last Name"
                                    name="lastName"
                                    value={motoristDetail.lastName}
                                    onChange={handleInputChange}
                                />
                                <Controls.Input
                                    variant="outlined"
                                    label="Email"
                                    name="email"
                                    value={motoristDetail.email}
                                    onChange={handleInputChange}
                                />

                            </Grid>
                            <Grid item xs={6}>
                                <Controls.RadioGroup
                                    variant="outlined"
                                    name="gender"
                                    label="Gender"
                                    color="primary"
                                    value={motoristDetail.gender}
                                    genderItems={genderItems}
                                    onChange={handleInputChange}
                                />

                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    value={motoristDetail.password}
                                    onChange={handleInputChange}
                                    autoComplete="current-password"
                                />

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