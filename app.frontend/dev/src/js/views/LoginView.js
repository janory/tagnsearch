import React, { Component } from "react";
import { Container, Row, Column, Form, Field, Input, Button, Grid } from "semantic-react";
import { loginUser } from "../actions/userActions"
import { connect } from "react-redux"
import { Link } from "react-router"

@connect()
export default class Login extends Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        const redirectRoute = this.props.location.query.redirect || "/welcome";
        this.state = {
            username: "",
            password: "",
            redirectRoute: redirectRoute
        };
    }

    changeUsername(e) {
        this.state.username = e.target.value;
    }

    changePassowrd(e) {
        this.state.password = e.target.value;
    }

    login(e) {
        e.preventDefault();
        if (this.state.username !== "" && this.state.password !== "") {
            this.props.dispatch(loginUser(this.state.username, this.state.password, this.state.redirectRoute));
        }
    }

    render() {
        const containerStyle = {
            marginTop: "150px"
        };
        return (
            <Grid centered style={containerStyle}>
                <Row>
                    <Column width="5">
                        <Form>
                            <Field inline>
                                <Input onChange={this.changeUsername.bind(this)} icon="users" label="Username" type="text" fluid/>
                            </Field>
                            <Field inline>
                                <Input onChange={this.changePassowrd.bind(this)} icon="asterisk" label="Password" type="password" fluid/>
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
