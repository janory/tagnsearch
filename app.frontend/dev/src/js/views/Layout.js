import React, { Component } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import { Container } from 'semantic-react';

export default class Layout extends Component {
	render() {
		return (
			<div>
				<Header/>
				<Container>{this.props.children}</Container>
				<Footer/>
			</div>
		);
	}
}
