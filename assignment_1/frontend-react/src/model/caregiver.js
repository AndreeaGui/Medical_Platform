import { EventEmitter } from "events";
import restClient from "../rest/RestClient";

class Caregiver extends EventEmitter {
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
            inputDoctorId: "",

            newCaregiver: {
                id:"",
                username: "",
                name:"",
                password:"",
                birthDate: "",
                gender: ""
            }
        };
    }

    //PATIENTS

    loadAllPatientsPerCaregiver() {
        restClient.handleGetPatientsPerCaregiver().then(responseJson => {
            this.state = {
                ...this.state,
                patients: responseJson
            }
            this.emit("change", this.state);
        })
    }
}

const caregiver = new Caregiver();

export default caregiver;