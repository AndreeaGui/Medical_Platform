import { EventEmitter } from "events";
import restClient from "../rest/RestClient";

class MedicationPlan extends EventEmitter {
    constructor() {
        super();
        this.state = {

            medications: [{
                id:"1",
                name:"medication"
            }],
            medicationPlans: [{
                id:"2",
                name:"medicationPlan"
            }],
            patients: [{
                id:"3",
                name:"patient"
            }],
            newMedicationPlan:{
                id: "",
                patientName: "",
                patientId: "",
                startTime: "",
                endTime: ""
            },
            newMedicationPerPlan:
            {
                id: "",
                medicationPlanId: "",
                medicationId: "",
                medicationName: "",
                dosage: "",
                intake: ""
            },
            patientMedicationPlan:[{
                id:"1"
            }]

        };
    }

    loadAllPatients() {
        restClient.handleGetPatients().then(responseJson => {
            this.state = {
                ...this.state,
                patients: responseJson
            }
            this.emit("change", this.state);
        })
    }

    loadAllMedications() {

        restClient.handleGetMedications().then(responseJson => {
            this.state = {
                ...this.state,
                medications: responseJson
            }
            this.emit("change", this.state);
        })
    }
    
    loadAllMedicationPlans() {

        restClient.handleGetAllMedicationPlans().then(responseJson => {
            this.state = {
                ...this.state,
                medicationPlans: responseJson
            }
            this.emit("change", this.state);
        })
    }

    changeNewMedicationPlanProperty(property, value){
        this.state = {
            ...this.state,
            newMedicationPlan: {
                ...this.state.newMedicationPlan,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    changeNewMedicationPerPlanProperty(property, value){
        this.state = {
            ...this.state,
            newMedicationPerPlan: {
                ...this.state.newMedicationPerPlan,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    addMedicationPlan(medicationPlan){
        console.log(medicationPlan);
        restClient.handleAddMedicationPlan(medicationPlan).then(responseJson =>{
            this.state = {
                ...this.state,
                medicationPlans: this.state.medicationPlans.concat([responseJson])
            };
            console.log(responseJson);
            this.emit("change", this.state);
        });
    }

    addMedicationPerPlan(medicationPerPlan){
        console.log(medicationPerPlan);
        restClient.handleAddMedicationPerPlan(medicationPerPlan).then(responseJson =>{
            /*this.state = {
                ...this.state,
                medicationPerPlans: this.state.medicationPerPlans.concat([responseJson])
            };*/
            console.log(responseJson);
            this.emit("change", this.state);
        });
    }

    loadPatientMedicationPlan() {

        restClient.handleGetPatientMedicationPlan().then(responseJson => {
            this.state = {
                ...this.state,
                patientMedicationPlan: responseJson
            }
            console.log(responseJson);
            this.emit("change", this.state);
        })
    }
    


}

const medicationPlan = new MedicationPlan();

export default medicationPlan;