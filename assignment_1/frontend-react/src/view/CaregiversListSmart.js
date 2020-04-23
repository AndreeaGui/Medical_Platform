import React, { Component } from "react";
import CaregiversList from "./CaregiversList";
import doctor from "../model/doctor";
import doctorPresenter from "../presenter/DoctorPresenter";

const mapModelStateToComponentState = modelState => ({
    caregivers :  modelState.caregivers,
    username: modelState.newCaregiver.username,
    password: modelState.newCaregiver.password,
    name: modelState.newCaregiver.name,
    birthDate: modelState.newCaregiver.birthDate,
    gender: modelState.newCaregiver.gender,
    caregiverId: modelState.newCaregiver.id
});

export default class CaregiversListSmart extends Component{
    constructor(){
        super();
        doctorPresenter.updateCaregiversList();
        this.state = mapModelStateToComponentState(doctor.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        doctor.addListener("change", this.listener);
    }

    componentWillUnmount(){
        doctor.removeListener("change", this.listener);
    }

    render () {
        return (
            <CaregiversList
                username={this.state.username}
                name = {this.state.name}
                password = {this.state.password}
                birthDate = {this.state.birthDate}
                gender = {this.state.gender}
                onCreateCaregiver={doctorPresenter.onCreateCaregiver} 
                onChangeCaregiver={doctorPresenter.onChangeCaregiver}
                onDeleteCaregiver={doctorPresenter.onDeleteCaregiver}
                onEditCaregiver={doctorPresenter.onEditCaregiver}
                caregivers = {this.state.caregivers}/>
        );
    }
}