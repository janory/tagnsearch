import { combineReducers } from "redux"
import { routerStateReducer } from "redux-router";
import user from "./userReducer"
import error from "./errorReducer"

/*
export default combineReducers({
	error,
    user,
    router: routerStateReducer
})
*/

// Based on this:
// http://stackoverflow.com/questions/35622588/how-to-reset-the-state-of-a-redux-store/35641992#35641992
const appReducer = combineReducers({
    error,
    user,
    router: routerStateReducer
})

const rootReducer = (state, action) => {
    if (action.type === "LOGOUT_USER") {
        localStorage.removeItem("token");
        // User voiud 0 instead of undefined
        // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/void
        // http://eslint.org/docs/rules/no-undefined
        state = void 0;
    }

    return appReducer(state, action)
}

export default rootReducer;
