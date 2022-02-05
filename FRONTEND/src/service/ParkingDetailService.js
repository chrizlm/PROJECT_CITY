import http from "./http-common"

const getAll = () => {
    return http.get("/apiv1/parkingdetails/all");
};

const get = numberPlate => {
    return http.get(`/apiv1/parkingdetails/get/${numberPlate}`);
};


const create = (data, motoristEmail) => {
    return http.post(`/apiv1/parkingdetails/save/${motoristEmail}`, data);
};

const checkBookingSpace = data =>{
    return http.post("/apiv1/parkingdetails/checkSpace",data);
}

const checkAmount = data =>{
    return http.post("/apiv1/parkingdetails/amount",data);
}

const updateAlt = (id, data) => {
    return http.put(`/apiv1/parkingdetails/update/${id}`, data);
};

const update = (data) => {
    return http.put(`/apiv1/parkingdetails/update`, data);
};

const remove = numberPlate => {
    return http.delete(`/apiv1/parkingdetails/${numberPlate}`);
};

const removeAll = () => {
    return http.delete(`/apiv1/parkingdetails/all`);
};

/*
const findByLocation = location => {
    return http.get(`/parkingdetails/get/${location}`);
};
 */

export default {
    checkAmount,
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    checkBookingSpace,
};