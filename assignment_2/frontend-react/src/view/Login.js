import React from"react";

const Login = ({username, password, onChange, onLogin}) => (
    <div class="container has-background-danger is-centered">
    <br/> 
    <h2 class="title is-1 is-centerd">Integrated Medical Monitoring Platform for Home-care assistance </h2> 
        <label class="label">Username</label>
        <input class="input is-danger" type="text" autoComplete="on"
        value={username} onChange={e => onChange("loginEmail", e.target.value)}/>
        <br/>
        <label class="label">Password</label>
        <input class="input is-danger"type="password"
        value={password} onChange={e => onChange("loginPassword", e.target.value)}/>
        <br/>
        <br/>
        <button class="button is-danger is-outlined" onClick={() => onLogin()}>Login</button>
        <br/>
    </div>
);

export default Login;