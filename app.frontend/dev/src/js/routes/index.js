import React from "react";
import { Route, IndexRedirect } from "react-router";
import App from "../containers/App";
import { LoginView, WelcomeView } from "../views";
import { requireAuthentication } from "../utils";

export default (
    <Route path="/" component={App}>
        <IndexRedirect to="/login" />
        <Route path="/login" component={LoginView}></Route>
        <Route path="/welcome" component={requireAuthentication(WelcomeView)} />
    </Route>
);
