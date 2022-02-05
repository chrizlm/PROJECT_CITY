import http from "./http-common"




const login = data => {
    return http.post("/apiv1/appUser/login", data);
};


const refreshToken = () =>{
    return http.get("/apiv1/appUser/token/refresh");
}



export default {
    login,
    refreshToken,
};