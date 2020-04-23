import caregiver from "../model/caregiver"

class CaregiverPresenter {

    
    updatePatientsListPerCaregiver() {
        caregiver.loadAllPatientsPerCaregiver();
    }
}

const caregiverPresenter = new CaregiverPresenter();

export default caregiverPresenter;