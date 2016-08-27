import React, { Component } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { Container } from "semantic-react";
import { connect } from "react-redux"

@connect((state) => {
    return {
        isAuthenticated: state.user.isAuthenticated
    };
})
export default class App extends Component {
    render() {
        return (
            <div>
                { this.props.isAuthenticated ? <Header/> : "" }
                <Container>{this.props.children}</Container>
                <Footer/>
            </div>
        );
    }
}
