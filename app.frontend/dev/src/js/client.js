import React from "react";
import ReactDOM from "react-dom";
import store from "./store"
import routes from "./routes"
import { Provider } from "react-redux";
import { ReduxRouter } from "redux-router";

const app = document.getElementById("app");

ReactDOM.render(
    <Provider store={store}>
        <ReduxRouter>{routes}</ReduxRouter>
    </Provider>
, app);
