import React, { Component } from 'react';
import { Header } from "semantic-react";


export default class Welcome extends Component {
    constructor() {
        super();
        this.state = {
            username: '',
            password: ''
        }
    }

    componentWillMount() {}

    render() {
        return (
            <div>
                <Header>Hi</Header>
                <div>{this.state.username}</div>
                <div>{this.state.password}</div>
            </div>
        );
    }
}
