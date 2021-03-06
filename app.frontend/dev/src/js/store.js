import logger from "redux-logger"
import thunk from "redux-thunk"
import promise from "redux-promise-middleware"
import { applyMiddleware, compose, createStore } from "redux"
import { reduxReactRouter } from "redux-router"
import routes from "./routes"
import createHistory from "history/lib/createBrowserHistory"

import reducer from "./reducers"

const store = compose(
    applyMiddleware(
        logger(),
        promise(),
        thunk),
    reduxReactRouter({
        routes,
        createHistory
    })
)(createStore)(reducer);

export default store;
