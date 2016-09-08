import React, { Component } from "react"
import { Container, Grid, Row, Column, Header } from "semantic-react"

export default class Logo extends Component {

    render() {
        const containerStyle = {
            marginTop: "100px"
        };
        const fontStyle = {
            fontFamily: "Lalezar, cursive"
        };
        const fontColor1 = {
            color: "#eb586f",
            fontSize: "55px",
            marginRight: "15px"
        };
        const fontColor2 = {
            color: "black",
            fontSize: "45px",
            opacity: "0.5"
        };
        const fontColor3 = {
            color: "#ffe390",
            fontSize: "55px",
            marginLeft: "15px"
        };
        return (
            <Container>
                <Grid>
                    <Row centered  style={containerStyle}>
                        <Column width="6">
                            <Header aligned="centered">
                                <span style={Object.assign({}, fontStyle, fontColor1)}>tag</span>
                                <span style={Object.assign({}, fontStyle, fontColor2)}>'n'</span>
                                <span style={Object.assign({}, fontStyle, fontColor3 )}>search</span>
                            </Header>
                        </Column>
                    </Row>
                </Grid>
            </Container>
        );
    }
}
