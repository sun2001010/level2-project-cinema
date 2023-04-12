import {get, put, del, post} from "../../config/http.js";
export  const getSeatInfo=(params)=>{
    return post('/select',params)
}
export  const getSeatStatus=(params)=>{
    return post('/select/check',params)
}