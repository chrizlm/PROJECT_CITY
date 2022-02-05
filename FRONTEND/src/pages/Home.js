import * as React from 'react';
import {useEffect, useState} from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import AppUserService, {login} from "../service/AppUserService";
import {Form} from "../components/UseForm";
import {useHistory} from "react-router-dom";
import ParkPic from "../components/img/brydon-mccluskey-vMneecAwo34-unsplash.jpg"


function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      {' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

//login details initialized
const initialFieldValues = {
  username: "",
  password: "",
};


export default function Home() {

  const [loginDetail, setLoginDetail] = useState(initialFieldValues);
  const [currentPage, setCurrentPage] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

    const history = useHistory();


        const handleInputChange = e =>{

            const {name, value} = e.target
            setLoginDetail({
                ...loginDetail,
                [name]: value,
            })
        }


    const refreshPage = () =>{
        setCurrentPage(null);
        setCurrentIndex(-1);
    }


      const handleSubmit = (event) => {
        event.preventDefault();
         //alert("logins submitted");

          if(loginDetail.username === "" || loginDetail.password === ""){
              alert("email or password is missing");
          }else {

             alert("login details submitted");

              const data = {
                  username: loginDetail.username,
                  password: loginDetail.password,
              }

              AppUserService.login(data).then(response => {
                  if(response.data === null) {
                      alert("error login check if email or password is correct");
                  }else{

                      localStorage.setItem("user", JSON.stringify(response.data));

                      const isAdmin = JSON.parse(atob(response.data.split('.')[1])).roles[0] === 'ROLE_ADMIN';
                      const isAttendant = JSON.parse(atob(response.data.split('.')[1])).roles[0] === 'ROLE_ATTENDANT';
                      const isMotorist = JSON.parse(atob(response.data.split('.')[1])).roles[0] === 'ROLE_MOTORIST';
                      const email = JSON.parse(atob(response.data.split('.')[1])).sub;
                      console.log(email);

                      console.log(isAdmin);
                      console.log(response);
                      //refreshPage();
                      history.push("./about");
                      window.location.reload(true);
                      const retrievedPerson = JSON.parse(localStorage.getItem('user'));
                      console.log(retrievedPerson);
                  }
              })
                  .catch(error => {
                      alert("error login check if email or password is correct");
                      console.log(error)
                  });

          }
      };

    const logout = () => {
        localStorage.removeItem("user");
    };




  return (
      <>

    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}

        >
            <img src={ParkPic} />
        </Grid>
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate  sx={{ mt: 1 }}>
                <Form onSubmit={handleSubmit}>
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    label="Email Address"
                    name="username"
                    value={loginDetail.username}
                    onChange={handleInputChange}
                    autoComplete="email"
                    autoFocus
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    value={loginDetail.password}
                    onChange={handleInputChange}
                    autoComplete="current-password"
                />


              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                onClick={handleSubmit}
              >
                Sign In
              </Button>
                </Form>
              <Grid container>
                <Grid item>
                  <Link href="/MotoristRegistration" variant="body2">
                    {"Don't have an account? Sign Up"}
                  </Link>
                </Grid>
              </Grid>
              <Copyright sx={{ mt: 5 }} />
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
      </>
  );
}


