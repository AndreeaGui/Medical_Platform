import React from "react";


const CaregiversList = ({caregivers, onCreateCaregiver, onChangeCaregiver, onDeleteCaregiver, onEditCaregiver,
    name, username, password, birthDate, gender}) => (
    <div>
        <h2 class="title is-1 is-centerd">Caregivers</h2>
        <div>
            <label >Username: </label>
            <input  class="input is-danger" type="text" placeholder="Username" 
            value={username} onChange={e => onChangeCaregiver("username", e.target.value)}/>
            <br/>
            <label>Password: </label>
            <input class="input is-danger" type="password" placeholder="Password"
            value={password} onChange={e => onChangeCaregiver("password", e.target.value)}/>
            <br/>
            <label>Name: </label>
            <input class="input is-danger" type="text" placeholder="Name" 
            value={name} onChange={e => onChangeCaregiver("name", e.target.value)}/>
            <br/>
            <label>Birth Date: </label>
            <input class="input is-danger" type="date" placeholder="Date"
            value={birthDate} onChange={e => onChangeCaregiver("birthDate", e.target.value)}></input>
            <br/>
            <label>Gender: </label>
            <input class="input is-danger" type="text" placeholder="MALE/FEMALE"
            value={gender} onChange={e => onChangeCaregiver("gender", e.target.value)}/>
            <br/>
            <br/>
            <button class="button is-danger is-light is-outlined"
            onClick={() => onCreateCaregiver()}>
                Add caregiver</button>
            <button class="button is-danger is-light is-outlined"
            onClick={()=>onEditCaregiver()}>Save edit</button>
            <br/>
            <br/>
        </div>
        <table class="table is-hoverable is-fullwidth is-centered " >
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Birth date</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                {
                    caregivers.map( (caregiver, index) => (
                        <tr key = {caregiver.id}>
                            <td>{caregiver.id}</td>
                            <td>{caregiver.name}</td>
                            <td>{caregiver.username}</td>
                            <td>{caregiver.birthDate}</td>
                            <td><button class="button is-outlined" onClick={() => onDeleteCaregiver(caregiver.id)}>Delete</button></td>
                            <td><button class="button is-outlined" onClick={ () => onChangeCaregiver("id", caregiver.id)}>Edit</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </div>
);

export default CaregiversList;