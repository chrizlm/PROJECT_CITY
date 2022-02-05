import React, { useState } from 'react'
import { makeStyles } from '@mui/styles';

export function UseForm(initialFieldValues, validateOnchange=false, validate){

    const [values, setValues] = useState(initialFieldValues);
    const [errors, setErrors] = useState(initialFieldValues);


    const handleInputChange = e =>{
 
        const {name, value} = e.target  
        setValues({
            ...values,
            [name]:value   
        })
        if(validateOnchange)
        validate({[name]:value})
    }

    const resetForm = () =>{
        setValues(initialFieldValues);
        setErrors({})
    }

    return{
        values,
        setValues,
        errors,
        setErrors,
        resetForm,
        handleInputChange
    }
}

const useStyles = makeStyles({
    root : {
        '& .MuiFormControl-root':{
            width : '80%',
            margin : '8px'
        }
    }
})

export function Form(props){

    const classes = useStyles();
    const{childern, ...other} = props;

    return(
        <form className={classes.root} {...other}>
            {props.children}
        </form>
    )
}