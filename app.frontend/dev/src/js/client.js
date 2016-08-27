import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import store from "./store"
import routes from "./routes"
import {Provider} from 'react-redux';
import {ReduxRouter, Router, hashHistory} from 'redux-router';
//import {Router, hashHistory} from 'react-router';


const app = document.getElementById('app');

ReactDOM.render(
	<Provider store={store}>
		<ReduxRouter>{routes}</ReduxRouter>
	</Provider>
,app);