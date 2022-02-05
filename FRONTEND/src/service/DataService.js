import http from "./http-common"

/*
const getFreeSpace = parkingLotName =>{
    return http.get(`/apiv1/booking/freeSpace/${parkingLotName}`);
}
*/

const getOccupiedSpaces = parkingLotName =>{
    return http.get(`/apiv1/booking/occupiedSpaces/${parkingLotName}`);
}

const getLocData = () => {
    return http.get("/apiv1/booking/locdata");
};

const getLocListData = () =>{
    return http.get("/apiv1/booking/locdataList");
}

const getParkingData = locationName => {
    return http.get(`/apiv1/booking/parkingdata/${locationName}`);
};



export default {
    getLocListData,
    getOccupiedSpaces,
    getLocData,
    getParkingData,
};