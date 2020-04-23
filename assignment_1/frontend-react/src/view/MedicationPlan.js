import React from "react";


const MedicationPlan = ({patients, medications, medicationPlans, 
    onCreateMedicationPlan, onChangeMedicationPlan, onCreateMedicationPerPlan, onChangeMedicationPerPlan,
    startTime, endTime, dosage, intake, patientId, medicationPlanId, medicationId}) => (
    <div class="container has-background-primary">
        <h2 class="title is-1 is-centerd">Add medication plan</h2>
        <div>
            <label>Start Date: </label>
            <input class="input is-danger" type="date" placeholder="start"
            value={startTime} onChange={e => onChangeMedicationPlan("startTime", e.target.value)}></input>
            <br/>
            <label>End Date: </label>
            <input class="input is-danger" type="date" placeholder="end"
            value={endTime} onChange={e => onChangeMedicationPlan("endTime", e.target.value)}></input>
            <br/>
            <select class="dropdown is-right is-active"
            id="patientSelect" onChange={e => onChangeMedicationPlan("patientId", e.target.value)}>
                {
                    patients.map((patient, index) => (
                        <option value={patient.id} >{patient.name}</option>
                    ))
                }
            </select>
            <input class="input is-danger" type="text" placeholder="patient"
            value={patientId}/>
            <br/>
            <button class="button is-danger is-light is-outlined" onClick={() => onCreateMedicationPlan()}>
                Add medication plan</button>
            <br/>
        </div>
        <h2 class="title is-1 is-centerd">Add medication per plan</h2>
        <div>
            <select class="dropdown is-right is-active"
            id="medicationPlanSelect" onChange={e => onChangeMedicationPerPlan("medicationPlanId", e.target.value)}>
                {
                    medicationPlans.map((medicationPlan, index) => (
                        <option value={medicationPlan.id} >
                        {medicationPlan.patientName}:{medicationPlan.startTime}:{medicationPlan.endTime}
                        </option>
                    ))
                }
            </select>
            <input class="input is-danger" type="text" placeholder="medicationPlan"
            value={medicationPlanId}/>
            <br/>
            <select class="dropdown is-right is-active"
            id="medicationSelect" onChange={e => onChangeMedicationPerPlan("medicationId", e.target.value)}>
                {
                    medications.map((medication, index) => (
                        <option value={medication.id} >{medication.name}</option>
                    ))
                }
            </select>
            <input class="input is-danger" type="text" placeholder="medication"
            value={medicationId}/>
            <br/>
            <input  class="input is-danger" type="text" placeholder="dosage" 
            value={dosage} onChange={e => onChangeMedicationPerPlan("dosage", e.target.value)}/>
            <br/>
            <input  class="input is-danger" type="text" placeholder="intake" 
            value={intake} onChange={e => onChangeMedicationPerPlan("intake", e.target.value)}/>
            <br/>
            <button class="button is-danger is-light is-outlined" onClick={() => onCreateMedicationPerPlan()}>
                Add medication per plan</button>
            <br/>
        </div>
    </div>
);

export default MedicationPlan;