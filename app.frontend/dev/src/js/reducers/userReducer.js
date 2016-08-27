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
    fetching: false,
    fetched: false,
    error: null
}, action) {
    /*
    switch (action.type) {
    case "LOGIN_USER": {
    return {...state, fetching: true}
    }
    }
    */
    return state;
}
