import {get, post,del,put} from "../../../config/http.js";

export const list = (params,page=1,limit=10)=>{
    return get("/films/select",{
        params:{
            ...params,
            page,
            limit
        }
    });
}
export const add = (params)=>{
    return put('/films/insert',params)
}
export const deleted = (params)=>{
    return post("/films/deleteById",params)
}
export const selectById =(params)=>{
    return post("/films/selectById",params)
}
export const update = (params)=>{
    return put("/films/updateById",params)
}