import React from "react"
import * as AiIcons from "react-icons/ai";
import * as MdIcons from "react-icons/md";
import {MdAccountCircle} from "react-icons/md";
import {FcAbout} from "react-icons/fc";


export const MotoristSideMenu = [
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
        title: 'Registration',
        path: '/motoristRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'Account',
        path: '/accountMotorist',
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
