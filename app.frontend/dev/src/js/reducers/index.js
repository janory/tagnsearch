import { combineReducers } from "redux"
import {routerStateReducer} from "redux-router";
import auth from "./authReducer"

export default combineReducers({
  auth,
  router: routerStateReducer
})
