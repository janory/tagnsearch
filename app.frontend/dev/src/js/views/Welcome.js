import React, { Component } from 'react';
import axios from 'axios';

export default class Welcome extends Component {
	constructor() {
		super();
		this.state = {
			username: '',
			password: ''
		}
	}

	componentWillMount() {
		const config = {
		  headers: {'Authorization': 'Bearer ' + this.props.params.token}
		};
		axios.get('/users/' + this.props.params.username, config)
		.then((response) => {
			this.setState({
				username: response.data.username,
				password: response.data.password
			});
		})
		.catch((error) => {
			console.log(error);
		});
	}

	render() {
		return (
			<div>
				<div>{this.state.username}</div>
				<div>{this.state.password}</div>
			</div>
		);
	}
}
