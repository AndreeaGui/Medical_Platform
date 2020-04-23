const BASE_URL = "http://localhost:8080";

class RestClient {
    constructor() {
        this.username = "";
        this.password = "";
        this.authorization = "None";
    }

    authenticate(username, password) {
        this.username = username;
        this.password = password;
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    logout() {
        this.username = "";
        this.password = "";
        this.authorization = "None";
    }

    handleGetLoggedUser(){
        return fetch(BASE_URL + "/getUser", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    //PATIENTS from DOCTOR

    handleGetPatients() {
        return fetch(BASE_URL + "/doctor/patients", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleAddPatient(patient){
        return fetch(BASE_URL + "/doctor/patients/new",{
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(patient)
        }).then(response => {
            if(response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleDeletePatient(patientId){
        return fetch (BASE_URL + "/doctor/patients/"+patientId, {
            method: "DELETE",
            headers: {
                Authorization: this.authorization
            }
        });
    }

    handleEditPatient(patient, patientId){
        return fetch (BASE_URL + "/doctor/patients/"+patientId, {
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(patient)
        }).then( response => {
            if(response.status === 200){
                return response.json();
            }
            return { "status": response.status, "text": response.text() };
        });
    }

    //CAREGIVERS from DOCTOR

    handleGetCaregivers(){
        return fetch(BASE_URL + "/doctor/caregivers", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleAddCaregiver(caregiver){
        return fetch(BASE_URL + "/doctor/caregivers/new",{
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(caregiver)
        }).then(response => {
            if(response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleDeleteCaregiver(caregiverId){
        return fetch (BASE_URL + "/doctor/caregivers/" + caregiverId, {
            method: "DELETE",
            headers: {
                Authorization: this.authorization
            }
        });
    }

    handleEditCaregiver(caregiver, caregiverId){
        return fetch (BASE_URL + "/doctor/caregivers/" + caregiverId, {
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(caregiver)
        }).then( response => {
            if(response.status === 200){
                return response.json();
            }
            return { "status": response.status, "text": response.text() };
        });
    }

    //MEDICATIONS from DOCTOR
    handleGetMedications(){
        return fetch(BASE_URL + "/doctor/medications", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleAddMedication(medication){
        return fetch(BASE_URL + "/doctor/medications/new",{
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(medication)
        }).then(response => {
            if(response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleDeleteMedication(medicationId){
        return fetch (BASE_URL + "/doctor/medications/" + medicationId, {
            method: "DELETE",
            headers: {
                Authorization: this.authorization
            }
        });
    }

    handleEditMedication(medication, medicationId){
        return fetch (BASE_URL + "/doctor/medications/"+medicationId, {
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(medication)
        }).then( response => {
            if(response.status === 200){
                return response.json();
            }
            return { "status": response.status, "text": response.text() };
        });
    }

    //PATIENTS from CAREGIVER
    handleGetPatientsPerCaregiver(){
        return fetch(BASE_URL + "/caregiver/patients", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    //DOCTOR
    handleGetDoctors() {
        return fetch(BASE_URL + "/doctor/doctors", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    //MEDICATION PLAN
    handleGetAllMedicationPlans() {
        return fetch(BASE_URL + "/medicationPlan/getMedicationPlans", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleAddMedicationPlan(medicationPlan){
        return fetch(BASE_URL + "/medicationPlan/addMedicationPlan",{
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(medicationPlan)
        }).then(response => {
            if(response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }

    handleAddMedicationPerPlan(medicationPerPlan){
        return fetch(BASE_URL + "/medicationPlan/addMedicationPerPlan",{
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            },
            body: JSON.stringify(medicationPerPlan)
        }).then(response => {
            if(response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }


    handleGetPatientMedicationPlan(){
        return fetch(BASE_URL + "/medicationPlan/patientMedicationPlan", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (response.status === 200)
                return response.json();
            return { "status": response.status, "text": response.text() };
        });
    }


}

const restClient = new RestClient();
//restClient.authenticate("gui", "000");
export default restClient;