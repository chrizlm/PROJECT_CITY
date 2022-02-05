import AuthService from "../../service/auth.service";
import MenuItem from "@mui/material/MenuItem";
import * as React from "react";
import {useEffect, useState} from "react";
import {MotoristSideMenu} from "./MotoristSideMenu";
import {AttendantSideMenu} from "./AttendantSideMenu";
import {AdminSideMenu} from "./AdminSideMenu";
import {Link, useHistory} from "react-router-dom";
import {styled, useTheme} from '@mui/material/styles';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import {SideMenu} from "./SideMenu";

const drawerWidth = 240;

const Main = styled('main', { shouldForwardProp: (prop) => prop !== 'open' })(
    ({ theme, open }) => ({
        flexGrow: 1,
        padding: theme.spacing(3),
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        marginLeft: `-${drawerWidth}px`,
        ...(open && {
            transition: theme.transitions.create('margin', {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
            marginLeft: 0,
        }),
    }),
);

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
    transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: `${drawerWidth}px`,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
    justifyContent: 'flex-end',
}));

export default function SideNavBar() {


    //from prev code
    const [isAuthenticated, setIsAuthenticated] = React.useState(false);
    const [unknownPage, setUnknownPage] = useState(false);
    const[motoristPage, setMotoristPage] = useState(false);
    const[attendantPage, setAttendantPage] = useState(false);
    const[adminPage, setAdminPage] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    const history = useHistory();


    useEffect(() =>{
        const user = AuthService.getCurrentUser();

        //const isAdmin = JSON.parse(atob(user.split('.')[1])).role === 'ROLE_MOTORIST';

        if(user){
            setCurrentUser(user);
            setMotoristPage(JSON.parse(atob(user.split('.')[1])).roles[0] === 'ROLE_MOTORIST');
            setAttendantPage(JSON.parse(atob(user.split('.')[1])).roles[0] === 'ROLE_ATTENDANT');
            setAdminPage(JSON.parse(atob(user.split('.')[1])).roles[0] === 'ROLE_ADMIN');
        }else {
            setUnknownPage(true);
        }
    }, []);




    const logout = () =>{
        setOpen(false);
        setIsAuthenticated(false);
        console.log("loggedInUser:" + isAuthenticated);
        history.push("./");
        window.location.reload(true);
        AuthService.logout();
    }


    const parseJwt = (token) => {
        try {
            return JSON.parse(atob(token.split(".")[1]));
        } catch (e) {
            return null;
        }
    };

    const verifyTokenExp = () => {
        const user = JSON.parse(localStorage.getItem("user"));
        if (user) {
            const decodedJwt = parseJwt(user);

            if (decodedJwt.exp * 1000 < Date.now()) {
                alert("time expired you are logged out");
                logout();
            }
        }
    } ;


    useEffect(() =>{
        verifyTokenExp();
    }, []);

/*


    useEffect(() =>{
        const user = AuthService.getCurrentUser();
        const time = JSON.parse(atob(user.split('.')[1])).exp

        if (time < Date.now() / 1000) {
            history.push("./Home");
            localStorage.clear();
        }
    }, []);


*/





    const theme = useTheme();
    const [open, setOpen] = React.useState(false);

    const handleDrawerOpen = () => {
        setOpen(true);
    };

    const handleDrawerClose = () => {
        setOpen(false);
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <CssBaseline />
            <AppBar position="fixed" open={open}>
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        sx={{ mr: 2, ...(open && { display: 'none' }) }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" noWrap component="div">
                        Parking App
                    </Typography>
                </Toolbar>
            </AppBar>
            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },
                }}
                variant="persistent"
                anchor="left"
                open={open}
            >
                <DrawerHeader>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'ltr' ? <ChevronLeftIcon /> : <ChevronRightIcon />}
                    </IconButton>
                </DrawerHeader>
                <Divider />
                <div>

                    {motoristPage && (

                        MotoristSideMenu.map((menuitem, index) => {
                            return (
                                <List>
                                    <div key={index}>
                                        <Link to={menuitem.path}>
                                        <MenuItem onClick={handleDrawerClose}>
                                            <li key={index}>
                                                    {menuitem.icon}
                                                    {menuitem.title}
                                            </li>
                                        </MenuItem>
                                        </Link>
                                    </div>
                                </List>
                            );
                        })
                    )
                    }
                    {attendantPage && (
                        AttendantSideMenu.map((menuitem, index) => {
                            return (
                                <List>
                                    <div key={index}>
                                        <Link to={menuitem.path}>
                                        <MenuItem onClick={handleDrawerClose}>
                                            <li key={index}>
                                                    {menuitem.icon}
                                                    {menuitem.title}
                                            </li>
                                        </MenuItem>
                                        </Link>
                                    </div>
                                </List>
                            );
                        }))
                    }
                    {adminPage && (
                        AdminSideMenu.map((menuitem, index) => {
                            return (
                                <List>
                                    <div key={index}>
                                        <Link to={menuitem.path}>
                                        <MenuItem onClick={handleDrawerClose}>
                                                    <li key={index}>
                                                    {menuitem.icon}
                                                    {menuitem.title}
                                                    </li>
                                        </MenuItem>
                                        </Link>
                                    </div>
                                </List>
                            );
                        }))
                    }
                    {unknownPage && (
                        SideMenu.map((menuitem, index) => {
                        return (
                            <div key={index}>
                                        <Link to={menuitem.path} >
                                            <MenuItem onClick={handleDrawerClose}>
                                            <li key={index} >
                                            {menuitem.icon}
                                            {menuitem.title}
                                            </li>
                                            </MenuItem>
                                        </Link>
                            </div>
                        );
                    }))
                    }
                    <Divider />
                    <List>
                        <div><MenuItem onClick={logout}>Logout</MenuItem></div>
                    </List>
                </div>
            </Drawer>
            <Main open={open}>

            </Main>
        </Box>
    );
}
