import { combineReducers } from "redux"
import { routerStateReducer } from "redux-router";
import user from "./userReducer"

export default combineReducers({
    user,
    router: routerStateReducer
})
