import React, { Component } from "react";
import { Modal, Icon, Content, Description, Header } from "semantic-react";

export default class Error extends Component {
    
    onCloseModal() {
        this.props.clearError();
    }

    render() {
        return (
            <div>
                <Modal active={this.props.error.messageTitle !== null}>
                    <Icon name="close" onClick={this.onCloseModal.bind(this)}/>
                    <Header >Ooops... Something went wrong!</Header>
                    <Content image>
                       <Icon name="warning" size="huge" color="red"/>
                        <Description>
                            <Header component="h2">{this.props.error.messageTitle}</Header>
                            <p>
                                {this.props.error.messageBody}
                            </p>
                        </Description>
                    </Content>
                </Modal>
            </div>
        );
    }
}
