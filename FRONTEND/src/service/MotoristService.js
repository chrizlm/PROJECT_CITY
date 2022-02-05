import http from "./http-common"

const changePassword = data =>{
    return http.put("/apiv1/motorist/updatePassword", data);
}

const getMotoristInfo = email =>{
    return http.get(`/apiv1/motorist/get/${email}`);
}

const registerMotorist = data =>{
    return http.post("/apiv1/motorist/save", data);
}

const getMotorist = id =>{
    return http.get(`/apiv1/motorist/getDetails/${id}`);
}

const updateMotorist = data =>{
    return http.put("/apiv1/motorist/update", data);
}

const deleteMotorist = id =>{
    return http.delete(`/apiv1/motorist/${id}`);
}



export default {
    changePassword,
    getMotoristInfo,
    registerMotorist,
    getMotorist,
    updateMotorist,
    deleteMotorist,
};