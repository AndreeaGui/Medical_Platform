import doctor from "../model/doctor"

class DoctorPresenter {

    //PATIENTS

    updatePatientsList() {
        doctor.loadAllPatients();
    }

    onCreate(){
        doctor.addPatient({
            "name": doctor.state.inputName,
            "username": doctor.state.inputUsername,
            "doctorId": doctor.state.inputDoctorId,
            "caregiverId": doctor.state.inputCaregiverId,
            "password": doctor.state.inputPassword,
            "birthDate": doctor.state.inputBirthDate,
            "gender": doctor.state.inputGender,
            "medicalRecord": doctor.state.inputMedicalRecord
        });
        doctor.changeStateProperty("inputName", "");
        doctor.changeStateProperty("inputUsername", "");
        doctor.changeStateProperty("inputPassword", "");
        doctor.changeStateProperty("inputBirthDate", "");
        doctor.changeStateProperty("inputGender", "");
        doctor.changeStateProperty("inputMedicalRecord", "");
        doctor.changeStateProperty("inputCaregiverId", "");
        doctor.changeStateProperty("inputDoctorId", "");
    
    }

    onChange(property, value){
        doctor.changeStateProperty(property, value);
    }

    onDelete(patientId){
        doctor.deletePatient(patientId);
    }

    onEdit(){
        //debugger;
        doctor.editPatient({
            "name": doctor.state.inputName,
            "username": doctor.state.inputUsername,
            "doctorId": doctor.state.inputDoctorId,
            "caregiverId": doctor.state.inputCaregiverId,
            "password": doctor.state.inputPassword,
            "birthDate": doctor.state.inputBirthDate,
            "gender": doctor.state.inputGender,
            "medicalRecord": doctor.state.inputMedicalRecord
        },doctor.state.inputId);

        doctor.changeStateProperty("inputName", "");
        doctor.changeStateProperty("inputUsername", "");
        doctor.changeStateProperty("inputPassword", "");
        doctor.changeStateProperty("inputBirthDate", "");
        doctor.changeStateProperty("inputGender", "");
        doctor.changeStateProperty("inputMedicalRecord", "");
        doctor.changeStateProperty("inputCaregiverId", "");
        doctor.changeStateProperty("inputDoctorId", "");
    }

    //CAREGIVERS

    updateCaregiversList(){
        doctor.loadAllCaregivers();
    }

    onCreateCaregiver(){
        doctor.addCaregiver({
            "name": doctor.state.newCaregiver.name,
            "username": doctor.state.newCaregiver.username,
            "password": doctor.state.newCaregiver.password,
            "birthDate": "1997-12-03",
            "gender": "MALE"
        });
        doctor.changeNewCaregiverProperty("name", "");
        doctor.changeNewCaregiverProperty("username", "");
        doctor.changeNewCaregiverProperty("password", "");
        doctor.changeNewCaregiverProperty("birthDate", "");
        doctor.changeNewCaregiverProperty("gender", "");
    
    }

    onChangeCaregiver(property, value){
        doctor.changeNewCaregiverProperty(property, value);
    }

    onDeleteCaregiver(caregiverId){
        doctor.deleteCaregiver(caregiverId);
    }

    onEditCaregiver(){
        //debugger;
        doctor.editCaregiver({
            "name": doctor.state.newCaregiver.name,
            "username": doctor.state.newCaregiver.username,
            "password": doctor.state.newCaregiver.password,
            "birthDate": "1997-12-03",
            "gender": "MALE"
        }, doctor.state.newCaregiver.id);

        doctor.changeNewCaregiverProperty("name", "");
        doctor.changeNewCaregiverProperty("username", "");
        doctor.changeNewCaregiverProperty("password", "");
        doctor.changeNewCaregiverProperty("birthDate", "");
        doctor.changeNewCaregiverProperty("gender", "");
    }

    //MEDICATIONS

    updateMedicationsList(){
        doctor.loadAllMedications();
    }

    onCreateMedication(){
        doctor.addMedication({
            "name": doctor.state.newMedication.name,
            "sideEffect": doctor.state.newMedication.sideEffect
        });
        doctor.changeNewMedicationProperty("name", "");
        doctor.changeNewMedicationProperty("sideEffect", "");
    
    }

    onChangeMedication(property, value){
        doctor.changeNewMedicationProperty(property, value);
    }

    onDeleteMedication(medicationId){
        doctor.deleteMedication(medicationId);
    }

    onEditMedication(){
        //debugger;
        doctor.editMedication({
            "name": doctor.state.newMedication.name,
            "sideEffect": doctor.state.newMedication.sideEffect
        }, doctor.state.newMedication.id);

        doctor.changeNewMedicationProperty("name", "");
        doctor.changeNewMedicationProperty("sideEffect", "");
    }  
    
    updateDoctorsList() {
        doctor.loadAllDoctors();
    }

}

const doctorPresenter = new DoctorPresenter();

export default doctorPresenter;