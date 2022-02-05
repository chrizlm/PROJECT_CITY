import http from "./http-common"

const getAll = () => {
    return http.get("/apiv1/parkingLot/all");
};

const get = parkinglocation => {
    return http.get(`/apiv1/parkingLot/get/${parkinglocation}`);
};

const create = data => {
    return http.post("/apiv1/parkingLot/save", data);
};

const updateAlt = (id, data) => {
    return http.put(`/apiv1/parkingLot/update/${id}`, data);
};

const update = (data) => {
    return http.put(`/apiv1/parkingLot/update`, data);
};

const remove = id => {
    return http.delete(`/apiv1/parkingLot/${id}`);
};

const removeAll = () => {
    return http.delete(`/apiv1/parkingLot/all`);
};

const findByLocation = location => {
    return http.get(`/apiv1/parkingLot/get/${location}`);
};

export default {
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    findByLocation
};