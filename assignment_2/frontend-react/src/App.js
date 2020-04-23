import React from 'react';
import './App.css';
import PatientsListSmart from './view/PatientsListSmart';
import LoginSmart from './view/LoginSmart';
import CaregiversListSmart from './view/CaregiversListSmart';
import MedicationListSmart from './view/MedicationListSmart';
import MyDoctorAccountSmart from './view/MyDoctorAccountSmart';
import MyCaregiverAccountSmart from './view/MyCaregiverAccountSmart';
import MedicatinPlanSmart from './view/MedicationPlanSmart';
import MyPatientAccountSmart from './view/MyPatientAccountSmart';
import { HashRouter, Switch, Route } from "react-router-dom";

const App = () => (
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact={true} component={LoginSmart} path="/" />
        <Route exact={true} component={MyDoctorAccountSmart} path="/doctor" />
        <Route exact={true} component={PatientsListSmart} path="/doctor/patients"/>
        <Route exact={true} component={CaregiversListSmart} path="/doctor/caregivers"/>
        <Route exact={true} component={MedicationListSmart} path="/doctor/medications"/>
        <Route exact={true} component={MyCaregiverAccountSmart} path="/caregiver" />
        <Route exact={true} component={MedicatinPlanSmart} path="/doctor/medicationPlan" />
        <Route exact={true} component={MyPatientAccountSmart} path="/patient" />
      </Switch>
    </HashRouter>
  </div>
);

export default App;
