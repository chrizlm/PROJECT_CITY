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
                    <p className={classes.noteabout}>This application is all about improving your parking experience within the city<br/>
                    </p>
                </Box>
            </Paper>
        </div>
    )
}

export default About

