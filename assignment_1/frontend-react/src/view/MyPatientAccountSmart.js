import React, { Component } from "react";
import MyPatientAccount from "./MyPatientAccount";
import medicationPlan from "../model/medicationPlan";
import medicationPlanPresenter from "../presenter/MedicationPlanPresenter";


const mapModelStateToComponentState = modelState => ({
    medicationPlan: modelState.patientMedicationPlan
});

export default class MyDoctorAccountSmart extends Component{
    constructor(){
        super();
        medicationPlanPresenter.updatePatientMedicationPlans();
        this.state = mapModelStateToComponentState(medicationPlan.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        medicationPlan.addListener("change", this.listener);
    }

    componentWillUnmount(){
        medicationPlan.removeListener("change", this.listener);
    }

    render () {
        return (
            <MyPatientAccount
                medicationPlans={this.state.medicationPlan}/>
        );
    }
}