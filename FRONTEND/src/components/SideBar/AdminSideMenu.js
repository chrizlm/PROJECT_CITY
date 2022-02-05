import React from "react"
import * as AiIcons from "react-icons/ai";
import * as MdIcons from "react-icons/md";
import {MdAccountCircle} from "react-icons/md";
import {FcAbout} from "react-icons/fc";
import {BiDetail, CgDetailsMore, RiParkingBoxLine} from "react-icons/all";


export const AdminSideMenu = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiOutlineHome />,
        className: 'nav-text'
    },
    {
        title: 'Bookings',
        path: '/booking',
        icon: <AiIcons.AiOutlineCar />,
        className: 'nav-text'
    },
    {
        title: 'Attendant Registration',
        path: '/attendantRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'Motorist Registration',
        path: '/motoristRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'BlackList Registration',
        path: '/blackListRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'ParkingLot Registration',
        path: '/parkingLot',
        icon: <RiParkingBoxLine />,
        className: 'nav-text'
    },
    {
        title: 'ParkingLots',
        path: '/parkingLotsDetails',
        icon: <BiDetail />,
        className: 'nav-text'
    },
    {
        title: 'AttendantsList',
        path: '/attendantsList',
        icon: <CgDetailsMore />,
        className: 'nav-text'
    },
    {
        title: 'BookingDetails',
        path: '/bookingDetails',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'BlackList',
        path: '/defaulterList',
        icon: <CgDetailsMore />,
        className: 'nav-text'
    },
    {
        title: 'Account',
        path: '/accountAdmin',
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
]
