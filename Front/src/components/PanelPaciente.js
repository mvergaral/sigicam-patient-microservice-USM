import React from 'react'
import Button from 'react-bootstrap/Button'

function panelPaciente(props){
    console.log(props)
    if (props.dataPaciente === null){
        return(<></>)
    }else{
        return(
            <div>
                <h1>Info Paciente</h1>
                <h2>{props.dataPaciente.text}</h2>
                <h3>Blah</h3>
                <h3>More Blah</h3>
             
                <Button>Asignar a Quimio</Button> 
                <Button>Asignar a Recuperación</Button>
            </div>
        )
    }
}

export default panelPaciente