export default function reducer(state = {
    id: null,
    username: null,
    password: null,
    firstName: null,
    lastName: null,
    role: null,
    token: null,
    isAuthenticated: false,
    isAuthenticating: false,
    statusText: null,
    fetched: false,
    error: null
}, action) {

    switch (action.type) {
        case "LOGIN_USER": {
                localStorage.removeItem("token");
                return {...state, isAuthenticating: true, isAuthenticated: false }
        }
        case "LOGIN_USER_FULFILLED": {
                localStorage.setItem("token", action.payload.data);
                return {...state, isAuthenticating: false, isAuthenticated: true }
        }
        case "LOGIN_USER_REJECTED": {
                localStorage.removeItem("token");
                return {...state, isAuthenticating: false, isAuthenticated: false }
        }
    }

    return state;
}