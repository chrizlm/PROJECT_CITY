import { FormControl, FormControlLabel, FormLabel, Radio, RadioGroup as MuiRadioGroup } from '@mui/material';
import React from 'react'

export default function({genderItems,name,label,value,color,onChange}){

   const content = genderItems.map(
        (item) => (
            <FormControlLabel key={item.id} value={item.id} control={<Radio color="primary"/>} label={item.title} />
        )
    );

    return(
        <FormControl>
            <FormLabel>{label}</FormLabel>
                <MuiRadioGroup row
                            name={name}
                            label={label}
                            value={value}
                            color={color}
                            onChange={onChange}>
                                {content}
                </MuiRadioGroup>
                            
        </FormControl>

    )
}