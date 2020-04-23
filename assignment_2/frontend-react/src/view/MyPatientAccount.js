import React from "react";


const MyPatientAccount = ({medicationPlans}) => (
    <div class="container has-backround-danger">
        <h2 class="title is-1 is-centerd has-text-danger">My medication plans</h2>
        <div class="container has-background-warning">
        <table class="table is-hoverable has-background-danger" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Medication</th>
                    <th>Dosage</th>
                    <th>Intake</th>
                </tr>
            </thead>
            <tbody>
                {
                    medicationPlans.map( (mp, index) => (
                        <tr key = {mp.id}>
                            <td>{mp.id}</td>
                            <td>{mp.medicationName}</td>
                            <td>{mp.dosage}</td>
                            <td>{mp.intake}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        </div>
    </div>
);

export default MyPatientAccount;