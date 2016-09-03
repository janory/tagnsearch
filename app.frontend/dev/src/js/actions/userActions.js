import axios from "axios";
import md5 from "md5";
import { push } from "redux-router";


export function loginUser(username, password, redirectTo) {
    return dispatch => {
    	dispatch({type: "LOGIN_USER", payload: ""})
    	axios.post("api/login", {
            username: username,
            password: md5(password)
        }).then((response) => {
        	dispatch({type: "LOGIN_USER_FULFILLED", payload: response})
        	dispatch(push(redirectTo))
      	}).catch((err) => {
        	dispatch({type: "LOGIN_USER_REJECTED", payload: err})
      	})
    }
}