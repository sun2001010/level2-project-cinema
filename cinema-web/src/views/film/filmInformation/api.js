import {get, post} from '../../../config/http.js';

export const findAll=()=>{
    return get("/filmplayer/select")
}