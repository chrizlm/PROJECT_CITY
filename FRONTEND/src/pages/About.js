import React from 'react'
import {Box, Paper, CardHeader} from "@mui/material";
import {makeStyles} from "@mui/styles";

const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
        textAlign : 'center'
    },
    noteabout : {
        textAlign : 'center',
    }

});



function About() {

    const classes = useStyles();

    return (
        <div>

            <Paper elevation={3} className={classes.pageContent}>
                <CardHeader title="CITY PARKING LOT APP" />
                <Box sx={{ minWidth: 800 }}>
                    <p className={classes.noteabout}>This application is all about improving your parking experience within the city <br/>
                        You will be able to find bookings spaces more easily in the city at whatever time and date of your liking<br/>
                        please go through the menu to explore the various sections we have in store for you<br/>
                        <br/>
                        contact information:<br/>
                        cityeparkingappinfo@gmail.com<br/>
                        0712515497
                    </p>
                </Box>
            </Paper>
        </div>
    )
}

export default About

