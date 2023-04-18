import {get, post} from "../../config/http.js";
import axios from "axios";

const http2 = axios.create({
    baseURL: 'http://localhost/baiduapi',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})
const http3 = axios.create({
    baseURL: 'http://localhost/local',
    headers:{
        'Content-Type': 'application/json;charset=UTF-8'
    }
})
export const findCinema=()=>{
    return post("/cinema/selectCinema")
}
export const findHall=()=>{
    return get("/hall/select")
}
export const autoPosition=()=>{
    return http3.get('/ip?ip=&coor=bd09ll&ak=c3gfkMibVm5lrIExPAYqVzI6FjiUIgoT')
}
export const position=(index)=>{
    return http2.get("/?&output=json&localtion=showLocaltion" +
        "&ak=c3gfkMibVm5lrIExPAYqVzI6FjiUIgoT&address="+index)
}
export const buyTicket=()=>{
    return post()
}