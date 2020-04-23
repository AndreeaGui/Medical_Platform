import { EventEmitter } from "events";
import restClient from "../rest/RestClient";
import WebSocketListener from "../ws/WebSocketListener";

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

const listener = new WebSocketListener();
listener.on("event", event =>{
    console.log(event);
    alert("Patient " + event.activity.patientId + " has problems while "+ event.activity.activity);
});

const caregiver = new Caregiver();

export default caregiver;