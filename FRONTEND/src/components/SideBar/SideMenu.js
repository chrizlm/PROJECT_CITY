import React from "react"
import * as AiIcons from "react-icons/ai";
import {FcAbout} from "react-icons/fc";
import {GiArchiveRegister} from "react-icons/gi"


export const SideMenu = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiOutlineHome />,
        className: 'nav-text'
    },
    {
        title: 'Registration',
        path: '/motoristRegistration',
        icon: <AiIcons.AiOutlineBook />,
        className: 'nav-text'
    },
    {
        title: 'About',
        path: '/about',
        icon: <AiIcons.AiOutlineInfoCircle />,
        className: 'nav-text'
    },


]
