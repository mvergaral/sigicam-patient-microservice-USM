import React, { Component } from 'react';

class NavBar extends Component{ //transforma la clase en componente

    render(){//esto es para que muestre contenido HTML
        return(//codigo jsx
        <div>
            <nav className="navbar navbar-dark bg-dark"> {/*hola*/}
            <a className="navbar-brand" href="#">Paciente</a>
            <a className="navbar-brand" href="#">Personal</a>
            <a className="navbar-brand" href="#">Pabellón</a>
            <a className="navbar-brand" href="#">Recuperación</a>
            <a className="navbar-brand" href="#">Quimioterapia</a>
            </nav>
        </div>
        );
    }
};

export default NavBar;