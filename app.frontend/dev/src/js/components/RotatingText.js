import React, { Component } from "react"
import { Row, Grid, Header } from "semantic-react";

export default class RotatingText extends Component {
    constructor(props) {
        super(props);
        this.iconIndex = 0;
        this.swap();
    }


    swap() {
        setInterval(() => {
            this.iconIndex++
                if (this.iconIndex === 3) { this.iconIndex = 0; }
            this.forceUpdate();
        }, 1500)
    }
    render() {
        const textStyle = {
            fontStyle: "italic",
            fontWeight: "bold"
        };
        const texts = [
            <Header style={textStyle} component="h3">Upload</Header>,
            <Header style={textStyle} component="h3">Tag</Header>,
            <Header style={textStyle} component="h3">Serach</Header>
        ]
        return (
            <Grid centered>
                <Row />
                <Row>{texts[this.iconIndex]}</Row>
                <Row />
            </Grid>
        );
    }
}
