import {get} from "../../config/http.js"
export  const findAll=()=>{
 return get("home/select")
}