import React from"react";

const MyAccount = ({username, password, name, birthDate, gender, seePatients, seeCaregivers, seeMedications, addMedicationPlan}) => (
    <div>
        <h2 class="title is-1 is-centerd has-text-danger">My account - doctor</h2>
        <br/>
        <br/>
        <label class="label has-text-danger">Username: </label>
        <span>{username}</span>
        <br/>
        <br/>
        <label class="label has-text-danger">Name: </label>
        <span>{name}</span>
        <br/>
        <br/>
        <label class="label has-text-danger">Birth date: </label>
        <span>{birthDate}</span>
        <br/>
        <br/>
        <button class="button is-danger is-outlined"
        onClick={() => seePatients()}>Patients</button>
        <br/>
        <br/>
        <button class="button is-danger is-outlined"
        onClick={() => seeCaregivers()}>Caregivers</button>
        <br/>
        <br/>
        <button class="button is-danger is-outlined"
        onClick={() => seeMedications()}>Medications</button>
        <br/>
        <br/>
        <button class="button is-danger is-outlined"
        onClick={() => addMedicationPlan()}>Add medication plan</button>
        <br/>
        <br/>
    </div>
);

export default MyAccount;