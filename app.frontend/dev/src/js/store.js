import logger from "redux-logger"
import thunk from "redux-thunk"
import promise from "redux-promise-middleware"
import { applyMiddleware, compose, createStore } from "redux"
import { reduxReactRouter } from "redux-router"

import routes from "./routes"
import createHistory from "history/lib/createBrowserHistory"

import reducer from "./reducers"

const middleware = applyMiddleware(logger(), promise(), thunk);
/*
const createStoreWithMiddleware = compose(
    middleware,
    reduxReactRouter({ routes, createHistory })
);
*/
const store = compose(
  reduxReactRouter({
    routes,
    createHistory
  }),
  middleware
)(createStore)(reducer);
 
//export default createStore(reducer, middleware)
//let store = createStoreWithMiddleware(createStore)(reducer);
export default store;