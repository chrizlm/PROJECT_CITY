
import React, {useEffect, useState} from 'react'
import {makeStyles} from "@mui/styles";
import {Box, Button, Grid, Paper, Card, CardActions, CardContent, CardMedia, Typography} from '@mui/material';
import ParkPic from "../components/img/brett-jordan-erLrY4aKztg-unsplash.jpg"


const useStyles = makeStyles({
    pageContent: {
        marginLeft: "600px",
        paddingLeft: "440px",


    },

});





export default function Support() {


    const classes = useStyles();
    return (
        <>



            <Box
                sx={{
                    display: 'flex',
                    justifyContent: "center",
                    alignItems: "center",
                    marginTop: "20px",
                    flexWrap: 'wrap',
                    '& > :not(style)': {
                        m: 1,
                        width: '100%',
                        height: '100%',
                    },
                }}
            >
                <Paper elevation={3} className={classes.pageContent}>
                    <Card sx={{ maxWidth: 345}}>
                        <CardMedia
                            component="img"
                            alt="help chat"
                            height="140"
                            image={ParkPic}/>
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">Online Help Chat</Typography>
                            <Typography variant="body2" color="text.secondary">
                                In case of any challenges experienced within this application please click the button below and you will be redirected to a chat box <br/>
                                In the chat box you can relay your complaints with the support team directly
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <a href={"https://tawk.to/chat/625166de7b967b11798990ef/1g06vjm7k"}>
                            <Button size="fullWidth" variant="contained">TO SUPPORT CHAT BOX</Button>
                            </a>
                        </CardActions>
                    </Card>

                </Paper>
            </Box>

        </>

    )
}


/*<a href={"https://tawk.to/chat/625166de7b967b11798990ef/1g06vjm7k"}>
    <Button variant="contained"
            fullWidth
    >TO CHAT BOX</Button>
</a>*/


