import React, { Component } from "react";
import { Menu, MenuItem, Container } from "semantic-react";

export default class Header extends Component {

    logout() {
        this.props.logoutUser();
    }

    render() {
        return (
            <Container>
                <Menu borderless>
                    <Menu>
                        <MenuItem>First item</MenuItem>
                        <MenuItem>Second Item</MenuItem>
                    </Menu>
                    <Menu floated="right">
                        <MenuItem onClick={this.logout.bind(this)}>Logout</MenuItem>
                    </Menu>
                </Menu>
            </Container>
        );
    }
}
