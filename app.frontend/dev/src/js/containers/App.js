import React, { Component } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Error from "../components/Error";
import Logo from "../components/Logo";
import { Container } from "semantic-react";
import { connect } from "react-redux"
import { clearError } from "../actions/errorActions"
import { logoutUser } from "../actions/userActions"
import stylesIgnored from "../../global.css";

@connect((state) => {
    return {
        isAuthenticated: state.user.isAuthenticated,
        error: state.error
    };
})
export default class App extends Component {

    clearError() {
        this.props.dispatch(clearError());
    }

    logoutUser() {
        this.props.dispatch(logoutUser());
    }

    render() {
        return (
            <div>
                { this.props.isAuthenticated ? <Header logoutUser={this.logoutUser.bind(this)} /> : "" }
                <Logo />
                <Container>{this.props.children}</Container>
                <Footer/>
                <Error clearError={this.clearError.bind(this)} error={this.props.error} />
            </div>
        );
    }
}
