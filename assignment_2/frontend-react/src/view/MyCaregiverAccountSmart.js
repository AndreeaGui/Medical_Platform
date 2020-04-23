import React, { Component } from "react";
import MyCaregiverAccount from "./MyCaregiverAccount";
import caregiver from "../model/caregiver";
import caregiverPresenter from "../presenter/CaregiverPresenter"


const mapModelStateToComponentState = modelState => ({
    patients: modelState.patients
});

export default class MyDoctorAccountSmart extends Component{
    constructor(){
        super();
        caregiverPresenter.updatePatientsListPerCaregiver();
        this.state = mapModelStateToComponentState(caregiver.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        caregiver.addListener("change", this.listener);
    }

    componentWillUnmount(){
        caregiver.removeListener("change", this.listener);
    }

    render () {
        return (
            <MyCaregiverAccount
                patients={this.state.patients}/>
        );
    }
}