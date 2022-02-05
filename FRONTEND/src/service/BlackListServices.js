import http from "./http-common"

const registerDefaulter = data =>{
    return http.post("/apiv1/blacklist/save", data);
}

const removeDefaulter = numberPlate =>{
    return http.delete(`/apiv1/blacklist/${numberPlate}`);
}

const getDefaulter = numberPlate =>{
    return http.get(`/apiv1/blacklist/${numberPlate}`);
}

const getAllDefaulters = () =>{
    return http.get("/apiv1/blacklist/all");
}



export default {
    registerDefaulter,
    removeDefaulter,
    getDefaulter,
    getAllDefaulters,
};