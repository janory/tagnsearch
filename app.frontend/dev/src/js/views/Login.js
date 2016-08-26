import React, { Component } from 'react';
import { Container, Row, Column, Form, Field, Input, Button, Grid } from 'semantic-react';
import axios from 'axios';
import md5 from 'md5';

export default class Login extends Component {
	constructor(){
		super();
		this.state = {
			username: '',
			password: ''
		};
	}

	static contextTypes = {
		router: React.PropTypes.object.isRequired
	};

	componentDidMount() {
	}

	componentWillMount() {
	}

	changeUsername(e) {
		this.state.username = e.target.value;
	}

	changePassowrd(e) {
		this.state.password = e.target.value;
	}

	login (e) {
		e.preventDefault();
		axios.post('login', {
			username: this.state.username,
			password: md5(this.state.password)
		})
		.then((response) => {
			this.context.router.push("/welcome/" + this.state.username + '/' + response.data);
		})
		.catch((error) => {
			console.log(error);
		});
	}

	render() {
		const containerStyle = {
			marginTop: "60px"
		};
		const formStyle = {
			//width: "60%"
		};
		return (
			<Grid centered style={containerStyle}>
			<Row>
			<Column width="8">
			<Form>
			<Field inline>
			<Input  onChange={this.changeUsername.bind(this)} icon="users" label="Username" type="text" fluid/>
			</Field>
			<Field inline>
			<Input  onChange={this.changePassowrd.bind(this)} icon="asterisk" label="Password" type="password" fluid/>
			</Field>
			<Button onClick={this.login.bind(this)} animated={"fade"}  color={"teal"} fluid>
			<div class="visible content">Loign</div>
			<div class="hidden content">
			<i class="right arrow icon"></i>
			</div>
			</Button>
			</Form>	
			</Column>
			</Row>
			</Grid>
			);
	}
}
/*
Login.contextTypes = {
    router: React.PropTypes.func.isRequired
};*/