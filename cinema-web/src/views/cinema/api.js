import {get, post} from "../../config/http.js";
import axios from "axios";

const http2 = axios.create({
    baseURL: 'http://api.map.baidu.com/geocoding/v3',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})
export const findCinema=()=>{
    return post("/cinema/selectCinema")
}
export const findHall=()=>{
    return get("/hall/select")
}
export const position=(index)=>{
    return http2.get("/?&output=json&localtion=showLocaltion" +
        "&ak=c3gfkMibVm5lrIExPAYqVzI6FjiUIgoT&address="+index)
}
export const buyTicket=()=>{
    return post()
}