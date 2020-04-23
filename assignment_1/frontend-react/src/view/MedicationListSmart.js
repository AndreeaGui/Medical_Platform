import React, { Component } from "react";
import MedicationList from "./MedicationList";
import doctor from "../model/doctor";
import doctorPresenter from "../presenter/DoctorPresenter";

const mapModelStateToComponentState = modelState => ({
    medications :  modelState.medications,
    name: modelState.newMedication.name,
    sideEffect: modelState.newMedication.sideEffect,
    medicationId: modelState.newMedication.id
});

export default class MedicationsListSmart extends Component{
    constructor(){
        super();
        doctorPresenter.updateMedicationsList();
        this.state = mapModelStateToComponentState(doctor.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        //doctor.addListener("change", this.listener);
        doctor.addListener("change", this.listener);
    }

    componentWillUnmount(){
        doctor.removeListener("change", this.listener);
    }

    render () {
        return (
            <MedicationList
                name = {this.state.name}
                sideEffect = {this.state.sideEffect}
                onCreateMedication={doctorPresenter.onCreateMedication} 
                onChangeMedication={doctorPresenter.onChangeMedication}
                onDeleteMedication={doctorPresenter.onDeleteMedication}
                onEditMedication={doctorPresenter.onEditMedication}
                medications = {this.state.medications}/>
        );
    }
}