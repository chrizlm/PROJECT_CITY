import React, {useState} from "react";
import SideNavBar from "../components/SideBar/SideNavBar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./Home";
import Bookings from "./Bookings";
import About from "./About";
import Support from "./Support";
import MotoristRegistration from './MotoristRegistration';
import AttendantRegistration from "./AttendantRegistration";
import ParkingLot from "./ParkingLot";
import ParkingLotDetails from "./ParkingLotDetails";
import AttendantPage from "./AttendantPage";
import BookingDetails from "./BookingDetails";
import AccountSettingsAdmin from "./AccountSettingsAdmin";
import AccountSettingsAttendant from "./AccountSettingsAttendant";
import AccountSettingsMotorist from "./AccountSettingsMotorist";
import BlacklistRegistration from "./BlacklistRegistration";
import BlackListAdmin from "./BlackListAdmin";
import {Link} from "@mui/material";

export default function PagesRoutes() {


  return (
    <>
      <Router>
        <SideNavBar />
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route exact path="/booking" component={Bookings}></Route>
          <Route exact path="/bookingDetails" component={BookingDetails}></Route>
          <Route exact path="/about" component={About}></Route>
          <Route exact path="/support" component={Support}></Route>
          <Route exact path="/motoristRegistration" component={MotoristRegistration}></Route>
          <Route exact path="/attendantRegistration" component={AttendantRegistration}></Route>
          <Route exact path="/parkingLot" component={ParkingLot}></Route>
          <Route exact path="/parkingLotsDetails" component={ParkingLotDetails}></Route>
          <Route exact path="/attendantsList" component={AttendantPage}></Route>
          <Route exact path="/accountAdmin" component={AccountSettingsAdmin}></Route>
          <Route exact path="/accountAttendant" component={AccountSettingsAttendant}></Route>
          <Route exact path="/accountMotorist" component={AccountSettingsMotorist}></Route>
          <Route exact path="/blackListRegistration" component={BlacklistRegistration}></Route>
          <Route exact path="/defaulterList" component={BlackListAdmin}></Route>
        </Switch>
      </Router>
    </>
  );
}

