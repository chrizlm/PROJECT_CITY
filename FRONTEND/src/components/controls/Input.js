import React from 'react'
import TextField from '@mui/material/TextField';

export default function({name, label, value, onChange, error=null, variant}){

    

    return(
       <TextField 
            variant= {variant}
            label={label}
            name={name}
            value={value}
            onChange={onChange}
            {...(error && {error: true, helperText:error})}
       />
     
    )
}