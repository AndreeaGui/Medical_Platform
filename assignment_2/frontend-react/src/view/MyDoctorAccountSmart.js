import React, { Component } from "react";
import MyDoctorAccount from "./MyDoctorAccount";
import login from "../model/login";
import loginPresenter from '../presenter/LoginPreseneter';


const mapModelStateToComponentState = modelState => ({
    username: modelState.loggedUsername,
    password: modelState.loggedPassword,
    name: modelState.loggedName,
    birthDate: modelState.loggedBirthDate,
    gender: modelState.loggedGender
});

export default class MyDoctorAccountSmart extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(login.state);
        console.log("presenter: "+ this.state.username);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        login.addListener("change", this.listener);
    }

    componentWillUnmount(){
        login.removeListener("change", this.listener);
    }

    render () {
        return (
            <MyDoctorAccount
                username={this.state.username}
                password = {this.state.password}
                birthDate={this.state.birthDate}
                name={this.state.name}
                gender={this.state.gender}
                seePatients={loginPresenter.seePatients}
                seeCaregivers={loginPresenter.seeCaregivers}
                seeMedications={loginPresenter.seeMedications}
                addMedicationPlan={loginPresenter.addMedicationPlan}/>
        );
    }
}