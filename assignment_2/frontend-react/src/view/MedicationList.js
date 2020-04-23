import React from "react";


const MedicationList = ({medications, name, sideEffect,
    onCreateMedication, onChangeMedication, onDeleteMedication, onEditMedication}) => (
    <div>
        <h2 class="title is-1 is-centerd">Medications</h2>
        <div>
            <label>Name: </label>
            <input class="input is-danger" type="text" placeholder="Name"
            value={name} onChange={e => onChangeMedication("name", e.target.value)}/>
            <br/>
            <label>Side Effects: </label>
            <input class="input is-danger" type="text" placeholder="ex: side effect 1, side effect 2, etc."
            value={sideEffect} onChange={e => onChangeMedication("sideEffect", e.target.value)}/>
            <br/>
            <button class="button is-danger is-light is-outlined" onClick={() => onCreateMedication()}>
                Add medication</button>
            <button class="button is-danger is-light is-outlined" onClick={()=>onEditMedication()}>Save edit</button>
        </div>
        <br/>
        <div class="container has-background-warning">
        <table className="table is-hoverable is-centers" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Side effect</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                {
                    medications.map( (medication, index) => (
                        <tr key = {medication.id}>
                            <td>{medication.id}</td>
                            <td>{medication.name}</td>
                            <td>{medication.sideEffect}</td>
                            <td><button class="button is-outlined" 
                            onClick={() => onDeleteMedication(medication.id)}>Delete</button></td>
                            <td><button class="button is-outlined"
                            onClick={ () => onChangeMedication("id", medication.id)}>Edit</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        </div>
    </div>
);

export default MedicationList;