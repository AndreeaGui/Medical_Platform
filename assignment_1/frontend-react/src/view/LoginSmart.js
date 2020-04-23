import React, { Component } from "react";
import Login from "./Login";
import login from "../model/login";
import loginPresenter from '../presenter/LoginPreseneter';


const mapModelStateToComponentState = modelState => ({
    username: modelState.username,
    password: modelState.password
});

export default class LoginSmart extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(login.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        login.addListener("change", this.listener);
    }

    componentWillUnmount(){
        login.removeListener("change", this.listener);
    }

    render () {
        return (
            <Login
                username={this.state.username}
                password = {this.state.password}
                onChange={loginPresenter.onChange}
                onLogin={loginPresenter.onLogin}/>
        );
    }
}