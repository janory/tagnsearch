import React from 'react';
import {Route, IndexRedirect} from 'react-router';

import Layout from '../views/Layout';
import Login from '../views/Login';
import Welcome from '../views/Welcome';
import { requireAuthentication } from '../utils';

export default(
	<Route path="/" component={Layout}>
  		<IndexRedirect to="/login" />
		<Route path="/login" component={Login}></Route>
		<Route path="/welcome" component={requireAuthentication(Welcome)} />
	</Route>
);