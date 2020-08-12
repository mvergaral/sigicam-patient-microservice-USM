import React from 'react'
import axios from 'axios'
import Table from 'react-bootstrap/Table'
import InputGroup from 'react-bootstrap/InputGroup'
import FormControl from 'react-bootstrap/FormControl'

import todosData from '../data/todosData'
class Tabla extends React.Component {
    constructor(){
        super()
        this.state = {
			patients: [] 
        }
    }

    onButtonClick(){

    }
    
	componentDidMount() {
		fetch('http://localhost:8000/patient/all')
			.then(res => res.json())
			.then((data) => {
				this.setState({ patients: data })
			})
			.catch(console.log)
	}
    render(){
    
        const RowPaciente = (patient)=>{
            return(
            <tr>
                <td>{patient.id}</td>
                <td>{patient.name}</td>
                <td>{patient.run}</td> {/* añadir redireccion a la vista del paciente*/}
                <td className="d-flex justify-content-between"><button type="button" class="btn btn-primary">Ver</button><button type="button" class="btn btn-danger">Eliminar</button></td>
            </tr>

            )
        }
        return(
            <div className="container">
                <div className="row">
                    <div className="col">
                        <Table striped bordered >
                            <thead>
                                <tr>
                                    <th className="col-1">Id</th>
                                    <th className="col-3">Nombre</th>
                                    <th className="col-3">Run</th>
                                    <th className="col-1"></th>
                                </tr>
                            </thead>
                            <tbody >
                                {this.state.patients.map( patient => RowPaciente(patient))}
                            </tbody>
                        </Table>
                    </div>
                </div>
              
            </div>
          

        )
    }

}

export default Tabla