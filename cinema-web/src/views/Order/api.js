import {get, put, del, post} from "../../config/http.js";
// export  const getOrderInfo=(params)=>{
//     return post('/order/info',params)
// }
export  const buy=(params)=>{
    return put('/order',params)
}
export  const buySeats=(params)=>{
    return put('/orderdetail',params)
}

export  const canselOrder=(oid)=>{
    return post ('/order/cancel',oid)
}
export  const delOrder=(oid)=>{
    return post ('/order/delete',oid)
}
export const getOrderInfo = (id,page=1,limit=10) => {

    return post('/order/info', {
            id,
            page,
            limit
    })
}
export  const getFilmInfo=(name)=>{
    return post ('/order/film',name)
}