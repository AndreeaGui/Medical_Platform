import { EventEmitter } from "events";
import restClient from "../rest/RestClient";
import medicationPlan from "./medicationPlan";

class Login extends EventEmitter{
    constructor() {
        super();
        this.state = {

            loginEmail: "",
            loginPassword: "",

            loggedUsername: "",
            loggedPassword: "",
            loggedName: "",
            loggedBirthDate: "",
            loggedGender: "",
            loggedRole: ""           
        };
    }

    loginUser(){
        restClient.authenticate(this.state.loginEmail, this.state.loginPassword);
        this.emit("change", this.state);
    }

    logoutUser(){
        restClient.authenticate("", "");
        this.state ={
            ...this.state,
            loginEmail : "",
            loginPassword : ""
        }
        this.emit("change", this.state);
    }

    getLoggedUser(){
    restClient.handleGetLoggedUser().then( responseJson => {
            console.log(responseJson);
            this.state ={
                ...this.state,
                loggedUsername: responseJson.username,
                loggedPassword: responseJson.password,
                loggedBirthDate: responseJson.birthDate,
                loggedName: responseJson.name,
                loggedRole:  responseJson.role
            }
            console.log(this.state.loggedRole);
            console.log(responseJson.role);
            
            if(this.state.loggedRole === "DOCTOR")
                window.location.assign("#/doctor");
            if(this.state.loggedRole === "CAREGIVER")
                window.location.assign("#/caregiver");
            if(this.state.loggedRole === "PATIENT"){
                window.location.assign("#/patient");
                //medicationPlan.loadPatientMedicationPlan();
            }
                
        });
        this.emit("change", this.state);
    }

    changeStateProperty(property, value){
        this.state= {
            ...this.state,
            [property]: value
        }
        this.emit("change", this.state);
    }


}

const login = new Login();

export default login;