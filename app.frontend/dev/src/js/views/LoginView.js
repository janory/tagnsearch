import React, { Component } from "react";
import { Row, Column, Form, Field, Input, Button, Grid, Divider, Text, Label, Header } from "semantic-react"
import { loginUser } from "../actions/userActions"
import { connect } from "react-redux"
import { Link } from "react-router"
import RotatingLogo from "../components/RotatingLogo"

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

    changeInput(fieldName, event) {
        this.setState({
            [fieldName]: event.target.value
        });
    }

    login(event) {
        event.preventDefault();
        if (this.state.username !== "" && this.state.password !== "") {
            this.props.dispatch(loginUser(this.state.username, this.state.password, this.state.redirectRoute));
        }
    }

    render() {
        const containerStyle = {
            marginTop: "60px"
        };
        const loginBoxStyle = {
            backgroundColor: "white",
            padding: "20px",
            borderRadius: "10px",
            boxShadow: "0px 0px 6px black"
        };
        return (
            <Grid>
                <Row centered  style={containerStyle}>
                    <Column width="6" style={loginBoxStyle}>
                        <Grid>
                            <Row centered>
                                <Column width="5">
                                </Column>
                                <Column width="6">
                                    <RotatingLogo />
                                </Column>
                                <Column width="5" />
                            </Row>
                        </Grid>
                        <Divider aligned="horizontal"><Header>Login</Header></Divider>
                        <Form>
                            <Field inline>
                                <Input onChange={this.changeInput.bind(this, "username")}
                                    icon="users" label="Username" type="text" fluid
                                    labelComponent={(props) => <Label style={{width: "90px"}} {...props}/>} />

                            </Field>
                            <Field inline>
                                <Input onChange={this.changeInput.bind(this, "password")}
                                icon="asterisk" label="Password" type="password" fluid
                                labelComponent={(props) => <Label style={{width: "90px"}} {...props}/>} />
                            </Field>
                            <Button onClick={this.login.bind(this)} emphasis="primary" fluid>
                                Loign
                            </Button>
                        </Form>
                        <Divider />
                        <Text style={{textAlign: "right"}}><Link to="registration" >Not a user yet? Registrate here!</Link></Text>
                    </Column>
                </Row>
            </Grid>
        );
    }
}
