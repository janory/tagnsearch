import React, { Component } from "react";
import { Row, Column, Form, Field, Input, Button, Grid, Label, Icon } from "semantic-react";
import { registrateUser } from "../actions/userActions"
import { connect } from "react-redux"

@connect()
export default class Registration extends Component {
    constructor() {
        super();
        let user = new Map();
        user.set("username", "")
        user.set("password", "")
        user.set("firstName", "")
        user.set("lastName", "")
        this.state = { user };
    }

    changeInput(fieldName, event) {
        this.state.user.set(fieldName, event.target.value);
    }

    registrate(event) {
        event.preventDefault();
        let fields = this.state.user.entries();
        for (let eachField of fields) {
            if ( eachField[1] === "" ) { return; }
        }
        this.props.dispatch(registrateUser(this.state.user));
    }

    render() {
        const containerStyle = {
            marginTop: "250px"
        };
        return (
            <Grid centered style={containerStyle}>
                <Row>
                    <Column width="5">
                        <Form>
                            <Field inline>
                                <Input icon="asterisk" label="First name" fluid
                                    onChange={this.changeInput.bind(this, "firstName")} 
                                    iconComponent={() => <Label color="red" corner="right"><Icon name="asterisk"/></Label>}
                                />
                            </Field>
                            <Field inline>
                                <Input icon="asterisk" label="Last name" fluid
                                    onChange={this.changeInput.bind(this, "lastName")} 
                                    iconComponent={() => <Label color="red" corner="right"><Icon name="asterisk"/></Label>}
                                />
                            </Field>
                            <Field inline>
                                <Input icon="asterisk" label="Username" fluid
                                    onChange={this.changeInput.bind(this, "username")} 
                                    iconComponent={() => <Label color="red" corner="right"><Icon name="asterisk"/></Label>}
                                />
                            </Field>
                            <Field inline>
                                <Input icon="asterisk" label="Password" fluid
                                    onChange={this.changeInput.bind(this, "password")} 
                                    type="password"
                                    iconComponent={() => <Label color="red" corner="right"><Icon name="asterisk"/></Label>}
                                />
                            </Field>
                            <Button onClick={this.registrate.bind(this)} emphasis="primary" fluid>Registration</Button>
                        </Form> 
                    </Column>
                </Row>
            </Grid>
        );
    }
}
