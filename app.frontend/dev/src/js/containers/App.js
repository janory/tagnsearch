import React, { Component } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Container } from "semantic-react";
import { connect } from "react-redux"

export default class App extends Component {
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
