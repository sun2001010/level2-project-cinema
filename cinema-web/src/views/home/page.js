import {get, post} from "../../config/http.js"
export  const findAll=()=>{
 return get("home/select")
}
export  const findOne=(params)=>{
 return post("/films/selectById",params)
}