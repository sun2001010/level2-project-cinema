import {get, post, put} from "../../../config/http.js";

export const list = (params) =>{
    return get("/session/select",params)
}
export const deleted = (params)=>{
    return post("/session/deleteById",params)
}
export const update = (params)=>{
    return put("/session/updateById",params)
}
