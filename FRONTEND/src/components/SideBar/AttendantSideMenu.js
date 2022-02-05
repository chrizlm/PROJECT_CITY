import React from "react"
import * as AiIcons from "react-icons/ai";
import * as MdIcons from "react-icons/md";
import {MdAccountCircle} from "react-icons/md";
import {BiDetail, RiParkingBoxLine} from "react-icons/all";


export const AttendantSideMenu = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiOutlineHome />,
        className: 'nav-text'
    },
    {
        title: 'BookingDetails',
        path: '/bookingDetails',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'ParkingLotDetails',
        path: '/parkingLotsDetails',
        icon: <BiDetail />,
        className: 'nav-text'
    },
    {
        title: 'BlackList Registration',
        path: '/blackListRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'Account',
        path: '/accountAttendant',
        icon: <MdAccountCircle />,
        className: 'nav-text'
    },
    {
        title: 'Support',
        path: '/support',
        icon: <MdIcons.MdHelpOutline />,
        className: 'nav-text'
    },
    {
        title: 'About',
        path: '/about',
        icon: <AiIcons.AiOutlineInfoCircle />,
        className: 'nav-text'
    },


    //booking details
    //parkinglot details
]
