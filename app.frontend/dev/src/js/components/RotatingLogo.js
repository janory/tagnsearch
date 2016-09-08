import React, { Component } from "react"
import Hashtag from "../icons/hashtag"
import Search from "../icons/search"
import CloudUpload from "../icons/cloudupload"
import { MorphReplaceResize } from "react-svg-morph"


export default class RotatingLogo extends Component {
    constructor(props) {
        super(props);
        this.iconIndex = 0;
        this.swap();
    }


    swap() {
        setInterval(() => {
            this.iconIndex++
            if ( this.iconIndex === 3 ) { this.iconIndex = 0; }
            this.forceUpdate();
        }, 1500)
    }
    render() {
        const icons = 
        [
            <CloudUpload key="upload" />,
            <Hashtag key="tag" />,
            <Search key="search" />
        ]
        return (
            <div style={{textAlign: "center "}}>
                <MorphReplaceResize width={70} height={70} duration={500}>
                    {icons[this.iconIndex]}
                </MorphReplaceResize>
            </div>
        );
    }
}
