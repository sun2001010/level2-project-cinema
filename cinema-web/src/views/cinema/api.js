import {post} from "../../config/http.js";
export const findAll=()=>{
    return post("/cinema/selectCinema")
}