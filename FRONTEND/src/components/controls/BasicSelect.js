import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function BasicSelect(props) {
  const [car, setCar] = React.useState('');

  const handleChange = (event) => {
    setCar(event.target.value);
  };

  const{name, value, label, onChange, error=null, options} = props;
  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">CarType</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          name={name}
          value={car}
          label={label}
          onChange={handleChange}
        >
          <MenuItem value='Saloon'>Saloon</MenuItem>
          <MenuItem value='Lorry'>Lorry</MenuItem>
          <MenuItem value='Trailer'>Traller</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}