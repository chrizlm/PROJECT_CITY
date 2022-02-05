import React from 'react'
import {Button} from '@mui/material';
import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
    root:{
        margin: '4px'
    },
    label:{
        textTransform: 'none'
    }
})

export default function MuiButton(props){

    const {text, color, size, variant, onClick, ...other} = props;
    const classes = useStyles();

    return(
        <Button
        variant={variant || "contained"}
        size={size || "large"}
        color={color || "primary"}
        onClick={onClick}
        {...other}
        classes={{root:classes.root, label:classes.label}}>
            {text}
        </Button>
    )
}