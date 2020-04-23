import React from "react";


const PatientsList = ({patients, onCreate, onChange, onDelete, onEdit, caregivers, doctors,
    name, username, password, doctorId, caregiverId, birthDate, gender, medicalRecord}) => (
    <div class="container has-background-primary">
        <h2 class="title is-1 is-centerd">Patients</h2>
        <div class="container has-background-warning">
            <label >Username: </label>
            <input  class="input is-danger" type="text" placeholder="Username" 
            value={username} onChange={e => onChange("inputUsername", e.target.value)}/>
            <br/>
            <label>Password: </label>
            <input class="input is-danger" type="password" placeholder="Password"
            value={password} onChange={e => onChange("inputPassword", e.target.value)}/>
            <br/>
            <label>Name: </label>
            <input class="input is-danger" type="text" placeholder="Name"
            value={name} onChange={e => onChange("inputName", e.target.value)}/>
            <br/>
            <label>Birth Date: </label>
            <input class="input is-danger" type="date" placeholder="Date"
            value={birthDate} onChange={e => onChange("inputBirthDate", e.target.value)}></input>
            <br/>
            <label>Gender: </label>
            <input class="input is-danger" type="text" placeholder="MALE/FEMALE"
            value={gender} onChange={e => onChange("inputGender", e.target.value)}/>
            <br/>
            <label>Medical Record: </label>
            <input class="input is-danger" type="text" placeholder="Write the medical record"
            value={medicalRecord} onChange={e => onChange("inputMedicalRecord", e.target.value)}/>
            <br/>
            <label>Caregiver id: </label>
            <select class="dropdown is-right is-active"
            id="caregiverSelect" onChange={e => onChange("inputCaregiverId", e.target.value)}>
                {
                    caregivers.map((caregiver, index) => (
                        <option value={caregiver.id} >{caregiver.name}</option>
                    ))
                }
            </select>
            <input class="input is-danger" type="text" placeholder="Caregiver"
            value={caregiverId}/>
            <br/>
            <label>Doctor id: </label>
            <select class="dropdown is-right is-active"
            id="doctorSelect" onChange={e => onChange("inputDoctorId", e.target.value)}>
                {
                    doctors.map((doctor, index) => (
                        <option value={doctor.id} >{doctor.name}</option>
                    ))
                }
            </select>
            <input class="input is-danger" type="text" placeholder="Doctor"
            value={doctorId}/>
            <br/>
            <br/>
            <div class="buttons has-addons">
                <button class="button is-danger is-light is-outlined" onClick={() => onCreate()}>Add patient</button>
                <button class="button is-danger is-light is-outlined" onClick={()=> onEdit()}>Save edit</button>
            </div>
            <br/>
        </div>
        <div class="container has-background-warning">
        <table className="table is-hoverable" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Birth date</th>
                    <th>MedicalRecord</th>
                    <th>Doctor</th>
                    <th>Caregiver</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                {
                    patients.map( (patient, index) => (
                        <tr key = {patient.id}>
                            <td>{patient.id}</td>
                            <td>{patient.name}</td>
                            <td>{patient.username}</td>
                            <td>{patient.birthDate}</td>
                            <td>{patient.medicalRecord}</td>
                            <td>{patient.doctorName}</td>
                            <td>{patient.caregiverName}</td>
                            <td><button class="button is-outlined" onClick={() => onDelete(patient.id)}>Delete</button></td>
                            <td><button class="button is-outlined" onClick={ () => onChange("inputId", patient.id)}>Edit</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        </div>
    </div>
);

export default PatientsList;