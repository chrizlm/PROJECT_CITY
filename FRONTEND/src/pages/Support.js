import React, {useEffect, useState} from 'react'
import {makeStyles} from "@mui/styles";
import {Box, Button, Grid, Paper} from '@mui/material';
import Controls from "../components/controls/Controls";
import {Form} from "../components/UseForm";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import TextField from "@mui/material/TextField";


const useStyles = makeStyles({
    pageContent: {
        margin: "40px",
        padding: "24px",
    },

});


const initialFieldValues = {
    type: "",
    content: "",
    sender: "",
};

var socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);


export default function Support() {
    const [messageDetail, setMessageDetail] =useState(initialFieldValues);
    //const [stompClient, setStompClient] = useState();


    useEffect(() =>{
        connection();
    }, []);

    const connection = (event) =>{
       // var socket = new SockJS('http://localhost:8080/ws');
        //stompClient = Stomp.over(socket);

        stompClient.connect({
            'Access-Control-Allow-Origin': 'http://localhost:3000',
        }, onConnected)
    }

    const onConnected = () =>{
        stompClient.subscribe('http://localhost:8080/topic/public', onMessageReceived);
        stompClient.send(
            "http://localhost:8080/app/chat.addUser",
            {'Access-Control-Allow-Origin': 'http://localhost:3000',},
            JSON.stringify({sender: "userA", type: 'JOIN'})
        )
    }

    const handleSubmitMessage = (event) =>{
        event.preventDefault();

        const data = {
            type: messageDetail.type,
            content: messageDetail.content,
            sender: messageDetail.sender,
        }

        stompClient.send("http://localhost:8080/app/chat.sendMessage", {'Access-Control-Allow-Origin': 'http://localhost:3000',}, JSON.stringify(data));

    };

    const onMessageReceived =(messagePayload) =>{
        var message = JSON.parse(messagePayload.body);
        if(message.type === 'JOIN') {
            message.content = message.sender + ' joined!';
        } else if (message.type === 'LEAVE') {
            message.content = message.sender + ' left!';
        }


        var messageText = document.createTextNode(message.content);
        return(
            <div>{messageText}</div>
        )
    }


    const handleInputChange = e =>{

        const {name, value} = e.target
        setMessageDetail({
            ...messageDetail,
            [name]: value,
        })
    }

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
                <div>
                    <h1>Support</h1>
                </div>
                <Paper elevation={3} className={classes.pageContent}>
                   <ul>
                       {onMessageReceived}
                   </ul>

                    <Form onSubmit={handleSubmitMessage}>
                        <Grid container>
                            <Grid item xs={6}>
                                <TextField
                                    variant="outlined"
                                    label="Help"
                                    placeholder="Type any issue you need of assistance with ..."
                                    multiline
                                    rows={7}
                                    maxRows={7}
                                    name="help"
                                    value={messageDetail.content}
                                    onChange={handleInputChange}
                                />
                                <Button variant="contained"
                                        fullWidth
                                        onClick={handleSubmitMessage}
                                >SEND</Button>
                            </Grid>
                        </Grid>
                    </Form>
                </Paper>
            </Box>

        </>

    )
}


