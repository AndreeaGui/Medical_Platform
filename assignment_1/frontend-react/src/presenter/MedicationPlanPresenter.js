import medicationPlan from "../model/medicationPlan"

class MedicationPlanPresenter {

    //PATIENTS

    updatePatientsList() {
        medicationPlan.loadAllPatients();
    }

    updateMedicationsList(){
        medicationPlan.loadAllMedications();
    }

    updateMedicationPlanList(){
        medicationPlan.loadAllMedicationPlans();
    }

    onCreateMedicationPlan(){
        medicationPlan.addMedicationPlan({
            "id": medicationPlan.state.newMedicationPlan.id,
            "patientName": medicationPlan.state.newMedicationPlan.patientName,
            "patientId": medicationPlan.state.newMedicationPlan.patientId,
            "startTime": medicationPlan.state.newMedicationPlan.startTime,
            "endTime": medicationPlan.state.newMedicationPlan.endTime
        });

        medicationPlan.changeNewMedicationPlanProperty("id", "");
        medicationPlan.changeNewMedicationPlanProperty("patientName", "");
        medicationPlan.changeNewMedicationPlanProperty("patientId", "");
        medicationPlan.changeNewMedicationPlanProperty("startTime", "");
        medicationPlan.changeNewMedicationPlanProperty("endTime", ""); 
    }

    onChangeMedicationPlan(property, value){
        medicationPlan.changeNewMedicationPlanProperty(property, value);
    }

    onCreateMedicationPerPlan(){
        medicationPlan.addMedicationPerPlan({
            "id": medicationPlan.state.newMedicationPerPlan.id,
            "medicationPlanId": medicationPlan.state.newMedicationPerPlan.medicationPlanId,
            "medicationId": medicationPlan.state.newMedicationPerPlan.medicationId,
            "medicationName": medicationPlan.state.newMedicationPerPlan. medicationName,
            "dosage": medicationPlan.state.newMedicationPerPlan.dosage,
            "intake": medicationPlan.state.newMedicationPerPlan.intake
        });

        medicationPlan.changeNewMedicationPerPlanProperty("id", "");
        medicationPlan.changeNewMedicationPerPlanProperty("medicationPlanId", "");
        medicationPlan.changeNewMedicationPerPlanProperty("medicationId", "");
        medicationPlan.changeNewMedicationPerPlanProperty("medicationName", "");
        medicationPlan.changeNewMedicationPerPlanProperty("dosage", "");
        medicationPlan.changeNewMedicationPerPlanProperty("intake", "");
    }

    onChangeMedicationPerPlan(property, value){
        medicationPlan.changeNewMedicationPerPlanProperty(property, value);
    }

    updatePatientMedicationPlans(){
        medicationPlan.loadPatientMedicationPlan();
    }


}

const medicationPlanPresenter = new MedicationPlanPresenter();

export default medicationPlanPresenter;