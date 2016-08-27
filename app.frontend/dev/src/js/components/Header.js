import React, { Component } from "react";
import { Menu, MenuItem, Container } from "semantic-react";

export default class Header extends Component {
    render() {
        return (
            <Container>
				<Menu>
				    <MenuItem>First item</MenuItem>
				    <MenuItem>Second Item</MenuItem>
				</Menu>
			</Container>
        );
    }
}
