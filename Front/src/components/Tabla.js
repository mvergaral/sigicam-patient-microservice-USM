import React from 'react'
import Table from 'react-bootstrap/Table'
import {Link} from 'react-router-dom'

class Tabla extends React.Component {
    constructor(){
        super()
        this.state = {
			patients: [] 
        }
    }

    deletePatient(id){
        fetch('http://localhost:8000/patient/'+id+'/delete', {method: 'DELETE'})
        .then(data => {
            if(data.ok){
                alert("Paciente eliminado con exito")
                window.location.reload(false)
            }
            else{
                alert("Error")
            }
        })
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
            <tr key={patient.id}>
                <td>{patient.id}</td>
                <td>{patient.name}</td>
                <td>{patient.run}</td>
                <td className="d-flex justify-content-between">
                    <Link type="button" className="btn btn-primary"
                        to={{
                            pathname: "/patient",
                            state: {id: patient.id}
                        }}
                    >Ver</Link>
                    <button type="button" className="btn btn-danger" onClick={() => this.deletePatient(patient.id)}>Eliminar</button>
                </td>
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