import React from "react";


const MyCaregiverAccount = ({patients}) => (
    <div class="container has-backround-danger">
        <h2 class="title is-1 is-centerd has-text-danger">Patients</h2>
        <div class="container has-background-warning">
        <table class="table is-hoverable has-background-danger" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Birth date</th>
                    <th>MedicalRecord</th>
                    <th>Doctor</th>
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
                        </tr>
                    ))
                }
            </tbody>
        </table>
        </div>
    </div>
);

export default MyCaregiverAccount;