import {get} from "../../config/http.js";

export const list = () =>{
    return get('/films/select')
}