import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";

const Dropdown = () => {
  const [area, setArea] = React.useState("");
  const [parklot, setParklot] = React.useState("");
  /** "selected" here is state variable which will hold the
   * value of currently selected dropdown.
   */
  const [selected, setSelected] = React.useState("");

  /** Function that will set different values to state variable
   * based on which dropdown is selected
   */
  const changeSelectOptionHandler = (event) => {
    setSelected(event.target.value);
    setArea(event.target.value);
  };

  const handleChange = (event) => {
    setParklot(event.target.value);
  };

  /** Different arrays for different dropdowns */
  const algorithm = [
    "Searching Algorithm",
    "Sorting Algorithm",
    "Graph Algorithm",
  ];
  const language = ["C++", "Java", "Python", "C#"];
  const dataStructure = ["Arrays", "LinkedList", "Stack", "Queue"];

  /** Type variable to store different array for different dropdown */
  let type = null;

  /** This will be used to create set of options that user will see */
  let options = null;

  /** Setting Type variable according to dropdown */
  if (selected === "Algorithm") {
    type = algorithm;
  } else if (selected === "Language") {
    type = language;
  } else if (selected === "Data Structure") {
    type = dataStructure;
  }

  /** If "Type" is null or undefined then options will be null,
   * otherwise it will create a options iterable based on our array
   */
  if (type) {
    options = type.map((el) => (<MenuItem key={el} value={el}>{el}</MenuItem>));
  }
  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="dependant-dropdown">Area</InputLabel>
        <Select
          labelId="dependant-dropdown"
          id="dependant-dropdown"
          value={area}
          label="Area"
          onChange={changeSelectOptionHandler}
        >
          <MenuItem value='Choose...'>Choose...</MenuItem>
          <MenuItem value='Algorithm'>Algorithm</MenuItem>
          <MenuItem value='Language'>Language</MenuItem>
          <MenuItem value='Data Structure'>Data Structure</MenuItem>
        </Select>
        </FormControl>
        <FormControl>
        <InputLabel id="dropdown">Park Lot</InputLabel>
        <Select
        labelId="dropdown"
        id="dropdown"
        value={parklot}
        label="Parklot"
        onChange={handleChange}>{options}</Select>
      </FormControl>
    </Box>
  );
};

export default Dropdown;
