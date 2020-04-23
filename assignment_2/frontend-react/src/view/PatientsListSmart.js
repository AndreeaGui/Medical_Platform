import React, { Component } from "react";
import PatientsList from "./PatientsList";
import doctor from "../model/doctor";
import doctorPresenter from "../presenter/DoctorPresenter";

const mapModelStateToComponentState = modelState => ({
    patients :  modelState.patients,
    username: modelState.inputUsername,
    password: modelState.inputPassword,
    name: modelState.inputName,
    birthDate: modelState.inputBirthDate,
    gender: modelState.inputGender,
    medicalRecord: modelState.inputMedicalRecord,
    caregiverId: modelState.inputCaregiverId,
    doctorId: modelState.inputDoctorId,
    patientId: modelState.inputId,
    caregivers: modelState.caregivers,
    doctors: modelState.doctors
});

export default class PatientsListSmart extends Component{
    constructor(){
        super();
        doctorPresenter.updatePatientsList();
        doctorPresenter.updateCaregiversList();
        doctorPresenter.updateDoctorsList();
        this.state = mapModelStateToComponentState(doctor.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        doctor.addListener("changePatientsList", this.listener);
        doctor.addListener("changeAddPatient", this.listener);
        doctor.addListener("changeProperty", this.listener);

    }

    componentWillUnmount(){
        doctor.removeListener("changePatientsList", this.listener);
        doctor.removeListener("changeAddPatient", this.listener);
        doctor.removeListener("changeProperty", this.listener);

    }

    render () {
        return (
            <PatientsList
                username={this.state.username}
                name = {this.state.name}
                password = {this.state.password}
                birthDate = {this.state.birthDate}
                gender = {this.state.gender}
                medicalRecord = {this.state.medicalRecord}
                caregiverId = {this.state.caregiverId}
                doctorId = {this.state.doctorId}
                onCreate={doctorPresenter.onCreate} 
                onChange={doctorPresenter.onChange}
                onDelete={doctorPresenter.onDelete}
                onEdit={doctorPresenter.onEdit}
                patients = {this.state.patients}
                caregivers={this.state.caregivers}
                doctors={this.state.doctors}/>
        );
    }
}