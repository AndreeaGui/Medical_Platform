import { EventEmitter } from "events";
import restClient from "../rest/RestClient";

class Doctor extends EventEmitter {
    constructor() {
        super();
        this.state = {
            patients: [{
                username: "aaaa@g.com",
                name: "nume1",
                id: "1"
            }, {
                username: "email@email.com",
                name: "nume2",
                id: "2"
            }],

            inputId: "",
            inputUsername: "",
            inputName:"",
            inputPassword:"",
            inputBirthDate: "",
            inputGender: "", 
            inputMedicalRecord: "",
            inputCaregiverId: "",
            inputDoctorId: "",

            newCaregiver: {
                id:"",
                username: "",
                name:"",
                password:"",
                birthDate: "",
                gender: ""
            },

            caregivers: [{
                username: "aaaa@g.com",
                name: "nume1",
                id: "1"
            },{
                username: "aaaabbb@g.com",
                name: "caregiver2",
                id: "2"
            }],

            medications: [{
                id: "1",
                name: "Nurofen",
                sideEffect: "death"
            }, {
                id: "2",
                name: "Diane",
                sideEffect: "infertility"
            }],

            newMedication: {
                id: "",
                name: "",
                sideEffect: ""
            },

            doctors:[{
                id: "2",
                username: "Doctor D",
                name: "doctor"
            }]
        };
    }

    //PATIENTS

    loadAllPatients() {
        //login.loginUser();
        restClient.handleGetPatients().then(responseJson => {
            this.state = {
                ...this.state,
                patients: responseJson
            }
            this.emit("changePatientsList", this.state);
        })
    }

    addPatient(patient){
        console.log(patient);
        restClient.handleAddPatient(patient).then(responseJson =>{
            this.state = {
                ...this.state,
                patients: this.state.patients.concat([responseJson])
            };
            console.log(responseJson);
            this.emit("changeAddPatient", this.state);
        });
    }

    deletePatient(patientId){
        console.log(patientId);
        restClient.handleDeletePatient(patientId).then(
            () => this.loadAllPatients()
        )
    }

    editPatient(patient, patientId){
        console.log(patientId);
        restClient.handleEditPatient(patient, patientId).then(
            () => this.loadAllPatients()
        )
    }

    changeStateProperty(property, value){
        this.state= {
            ...this.state,
            [property]: value
        }
        this.emit("changeProperty", this.state);
    }

    //CAREGIVERS

    loadAllCaregivers() {

        restClient.handleGetCaregivers().then(responseJson => {
            this.state = {
                ...this.state,
                caregivers: responseJson
            }
            this.emit("change", this.state);
        })
    }

    addCaregiver(caregiver){
        console.log(caregiver);
        restClient.handleAddCaregiver(caregiver).then(responseJson =>{
            this.state = {
                ...this.state,
                caregivers: this.state.caregivers.concat([responseJson])
            };
            console.log(responseJson);
            this.emit("change", this.state);
        });
    }

    deleteCaregiver(caregiverId){
        console.log(caregiverId);
        restClient.handleDeleteCaregiver(caregiverId).then(
            () => this.loadAllCaregivers()
        )
    }

    editCaregiver(caregiver, caregiverId){
        console.log(caregiverId);
        restClient.handleEditCaregiver(caregiver, caregiverId).then(
            () => this.loadAllCaregivers()
        )
    }

    changeNewCaregiverProperty(property, value){
        this.state = {
            ...this.state,
            newCaregiver: {
                ...this.state.newCaregiver,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    //MEDICATIONS

    loadAllMedications() {

        restClient.handleGetMedications().then(responseJson => {
            this.state = {
                ...this.state,
                medications: responseJson
            }
            this.emit("change", this.state);
        })
    }

    addMedication(medication){
        console.log(medication);
        restClient.handleAddMedication(medication).then(responseJson =>{
            this.state = {
                ...this.state,
                medications: this.state.medications.concat([responseJson])
            };
            console.log(responseJson);
            this.emit("change", this.state);
        });
    }

    deleteMedication(medicationId){
        console.log(medicationId);
        restClient.handleDeleteMedication(medicationId).then(
            () => this.loadAllMedications()
        )
    }

    editMedication(medication, medicationId){
        console.log(medicationId);
        restClient.handleEditMedication(medication, medicationId).then(
            () => this.loadAllMedications()
        )
    }

    changeNewMedicationProperty(property, value){
        this.state = {
            ...this.state,
            newMedication: {
                ...this.state.newMedication,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    //DOCTORS
    loadAllDoctors() {

        restClient.handleGetDoctors().then(responseJson => {
            this.state = {
                ...this.state,
                doctors: responseJson
            }
            this.emit("change", this.state);
        })
    }

}

const doctor = new Doctor();

export default doctor;
