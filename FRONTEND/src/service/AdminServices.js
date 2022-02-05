import http from "./http-common"

/*
register
get
getall
update
delete
delete all
 */
/** admin */
const getAdmin = email =>{
    return http.get(`/apiv1/admin/get/${email}`);
}

const changePassword = data =>{
    return http.put("/apiv1/admin/updatePassword", data);
}

/** attendants */

const registerAttendant = data => {
    return http.post("/apiv1/attendant/save", data);
};

const getAttendant = id =>{
    return http.get(`/apiv1/attendant/getDetails/${id}`);
}

const getAllAttendants = () =>{
    return http.get("/apiv1/attendant/getall");
}

const updateAttendant = data =>{
    return http.put("/apiv1/attendant/update", data);
}

const deleteAttendant = id =>{
    return http.delete(`/apiv1/attendant/${id}`);
}

/** motorist */

const registerMotorist = data =>{
    return http.post("/apiv1/motorist/save", data);
}

const getMotorist = id =>{
    return http.get(`/apiv1/motorist/getDetails/${id}`);
}

const getAllMotorists = () =>{
    return http.get("/apiv1/motorist/getall");
}

const updateMotorist = data =>{
    return http.put("/apiv1/motorist/update", data);
}

const deleteMotorist = id =>{
    return http.delete(`/apiv1/motorist/${id}`);
}


/** appuser */

// get app users
const getAppUsers = () =>{
    return http.get("/apiv1/appUser/");
}
// save app user
const saveAppUser = data =>{
    return http.post("/apiv1/appUser/save", data);
}
// save role
const saveRole = data =>{
    return http.post("/apiv1/appUser/role/save", data);
}
// add role
const addRoleToUser = data =>{
    return http.post("/apiv1/appUser/role/addtouser", data);
}

export default {
    changePassword,
    getAdmin,

    registerAttendant,
    getAttendant,
    getAllAttendants,
    updateAttendant,
    deleteAttendant,

    registerMotorist,
    getMotorist,
    getAllMotorists,
    updateMotorist,
    deleteMotorist,

    getAppUsers,
    saveAppUser,
    saveRole,
    addRoleToUser,
};