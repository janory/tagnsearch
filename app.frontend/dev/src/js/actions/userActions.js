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
            dispatch({type: "ERROR_SET", payload: err})
        })
    }
}

export function registrateUser(user) {
    return dispatch => {
        dispatch({type: "REGISTRATE_USER", payload: ""})
        axios.put("api/registration", {
            username: user.get("username"),
            password: md5(user.get("password")),
            firstName: user.get("firstName"),
            lastName: user.get("lastName")
        })
        .then((response) => {
            dispatch({type: "REGISTRATE_USER_FULFILLED", payload: response})
            dispatch(push("/login"))
        }).catch((err) => {
            dispatch({type: "ERROR_SET", payload: err})
        })
    }
}

export function logoutUser() {
     return dispatch => {
        dispatch({type: "LOGOUT_USER", payload: ""})
        dispatch(push("/login"))
    }
}
