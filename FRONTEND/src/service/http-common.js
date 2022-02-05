import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080",
    headers: {

        "Accept": "application/json",
        'Access-Control-Allow-Origin': 'http://localhost:3000',
        //"Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
        //"Content-Type": "application/x-www-form-urlencoded",
        // 'Access-Control-Allow-Credentials': 'true',
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('user')),

    }

});

