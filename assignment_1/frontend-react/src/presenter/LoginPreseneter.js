import login from "../model/login"
import restClient from "../rest/RestClient";

class LoginPresenter {

    onChange(property, value){
        login.changeStateProperty(property, value);
    }

    onLogin(){
        login.loginUser();
        console.log(restClient);
        login.getLoggedUser();
    }
    
    seePatients(){
        window.location.assign("#/doctor/patients");
    }

    seeCaregivers(){
        window.location.assign("#/doctor/caregivers");
    }

    seeMedications(){
        window.location.assign("#/doctor/medications");
    }

    addMedicationPlan(){
        window.location.assign("#/doctor/medicationPlan");
    }
}

const loginPresenter = new LoginPresenter();

export default loginPresenter;