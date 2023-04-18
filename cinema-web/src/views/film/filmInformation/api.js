

import {get,post} from "../../../config/http.js"
export  const findAll=()=>{
    return get("home/select")
}

export  const findOne=(params)=>{
    return post("films/selectById",params)
}
export const selectCollectNum=(fname)=>{
    return post("collect/findNum",fname)
}

export const selectByPrimaryKey=(fid)=>{
    return post("films/selectById",fid)
}

export const scoreAdd=(score)=>{
    return put("score/add",score)
}

export const commentsSelect=()=>{

}