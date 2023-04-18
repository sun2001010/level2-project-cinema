import {get,post} from "../../config/http.js"
export  const findAll=()=>{
 return get("home/select")
}

export  const findOne=(params)=>{
 return post("films/selectById",params)
}
export const selectCollectNum=(fname)=>{
 return post("collect/findNum",fname)
}

export const selectAvg=(fname)=>{
 return post("score/selectAvg",fname)
}
export const selectComm=(fname)=>{
 return post("comments/select",fname)
}