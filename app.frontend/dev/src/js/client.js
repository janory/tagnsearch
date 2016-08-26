import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, hashHistory } from "react-router";

import Layout from './views/Layout';
import Login from './views/Login';
import Welcome from './views/Welcome';


const app = document.getElementById('app');

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={Layout}>
      <IndexRoute component={Login}></IndexRoute>
      <Route path="/welcome/:username/:token" component={Welcome} />
    </Route>
  </Router>,
app);