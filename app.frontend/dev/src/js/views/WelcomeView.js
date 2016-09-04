import React, { Component } from 'react';


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
                ASDF
                <div>{this.state.username}</div>
                <div>{this.state.password}</div>
            </div>
        );
    }
}
