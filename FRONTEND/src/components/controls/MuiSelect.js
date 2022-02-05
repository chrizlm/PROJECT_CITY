import { FormControl, InputLabel,FormHelperText, MenuItem, Select} from '@mui/material';
import React from 'react'

export default function MuiSelect(props){

    const{name, value, label, onChange, error=null, options} = props;

    return(
       <FormControl
       variant="outlined"
       {...(error && {error: true})}>
           <InputLabel>{label}</InputLabel>
           <Select
           name={name}
           value={value}
           label={label}
           onChange={onChange}>
               <MenuItem value="">none</MenuItem>
               {
                   options.map(
                       item => (<MenuItem key={item.id} value={item.id}>{item.title}</MenuItem>)
                   )
               }
           </Select>
           {error && <FormHelperText>{error}</FormHelperText>}
       </FormControl>
    )
}