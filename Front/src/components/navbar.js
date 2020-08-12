import React, { Component } from 'react';

class NavBar extends Component{
    render(){
        return(
        <div>
            <nav className="navbar navbar-dark bg-dark"> {/*hola*/}
                <a className="navbar-brand" href="#">Paciente</a>
                <a className="navbar-brand" href="https://isw-frontend-personal.herokuapp.com/">Personal</a>
                <a className="navbar-brand" href="https://frontpabellon.herokuapp.com/">Pabellón</a>
                <a className="navbar-brand" href="https://ursidae-rec.herokuapp.com/">Recuperación</a>
                <a className="navbar-brand" href="https://frontendquimio.herokuapp.com/">Quimioterapia</a>
            </nav>
        </div>
        );
    }
};

export default NavBar;