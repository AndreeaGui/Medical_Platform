import React, { Component } from "react";
import MedicationPlan from "./MedicationPlan";
import medicationPlan from "../model/medicationPlan";
import medicationPlanPresenter from "../presenter/MedicationPlanPresenter";

const mapModelStateToComponentState = modelState => ({
    patients: modelState.patients,
    medications: modelState.medications,
    medicationPlans: modelState.medicationPlans,

    startTime: modelState.newMedicationPlan.startTime, 
    endTime: modelState.newMedicationPlan.endTime, 
    patientId: modelState.newMedicationPlan.patientId,
    dosage: modelState.newMedicationPerPlan.dosage, 
    intake: modelState.newMedicationPerPlan.intake,  
    medicationPlanId: modelState.newMedicationPerPlan.medicationPlanId, 
    medicationId: modelState.newMedicationPerPlan.medicationId

});

export default class CaregiversListSmart extends Component{
    constructor(){
        super();
        medicationPlanPresenter.updatePatientsList();
        medicationPlanPresenter.updateMedicationsList();
        medicationPlanPresenter.updateMedicationPlanList();
        this.state = mapModelStateToComponentState(medicationPlan.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        medicationPlan.addListener("change", this.listener);
    }

    componentWillUnmount(){
        medicationPlan.removeListener("change", this.listener);
    }

    render () {
        return (
            <MedicationPlan
            patients={this.state.patients} 
            medications={this.state.medications}
            medicationPlans={this.state.medicationPlans}
            onCreateMedicationPlan={medicationPlanPresenter.onCreateMedicationPlan}
            onChangeMedicationPlan={medicationPlanPresenter.onChangeMedicationPlan}
            onCreateMedicationPerPlan={medicationPlanPresenter.onCreateMedicationPerPlan}
            onChangeMedicationPerPlan={medicationPlanPresenter.onChangeMedicationPerPlan}
            startTime={this.state.startTime}
            endTime={this.state.endTime}
            dosage={this.state.dosage}
            intake={this.state.intake}
            patientId={this.state.patientId}
            medicationPlanId={this.state.medicationPlanId}
            medicationId={this.state.medicationId}/>
        );
    }
}