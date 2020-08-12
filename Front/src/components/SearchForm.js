import React, { Component } from 'react';
import {Link} from 'react-router-dom'
import Table from 'react-bootstrap/Table'
class SearchForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
      value: '',
      campo: '',
      patient: ''
    };
    this.handleChangeBox = this.handleChangeBox.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChangeBar = this.handleChangeBar.bind(this);
  }
  handleChangeBar(event){
    const {name,value} = event.target
    this.setState({ value: value }); 
  }
  handleChangeBox(event) {
    const {name,value} = event.target
    this.setState({ campo: value }); 

   
  
   
    
    }
  handleSubmit(event) {
    const {campo,value} = this.state
    let url
    if (campo === 'id'){
      url = 'http://localhost:8000/patient/'
    }else if(campo === 'run'){
      url = 'http://localhost:8000/patient/findByRun?run='
    }
    console.log(url)
    fetch(url+value)
    .then(response => {
      console.log(response)
      console.log(response.status)
      if (!(response.status === 302)){
       
        alert("Busqueda Fallida")
        return ""
      }
      return response.json()
    })
    .then((data) => {
      this.setState({ patient: data })
    })
  }
  deletePatient(id) {
    fetch('http://localhost:8000/patient/' + id + '/delete', { method: 'DELETE' })
      .then(data => {
        if (data.ok) {
          alert("Paciente eliminado con exito")
          window.location.reload(false)
        }
        else {
          alert("Error")
        }
      })
  }

  render() {
    
    const RowPaciente = (patient)=>{
      

      return(
      <Table striped bordered>
        <thead>
          <tr>
              <th className="col-1">Id</th>
              <th className="col-3">Nombre</th>
              <th className="col-3">Run</th>
              <th className="col-1"></th>
          </tr>
        </thead>
      <tbody>
      <tr>
          <td>{patient.id}</td>
              <td>{patient.name} {patient.last_name}</td>
          <td>{patient.run}</td>
          <td className="d-flex justify-content-between">
                <Link type="button" className="btn btn-primary"
                  to={{
                    pathname: "/patient",
                    state: { id: patient.id }
                  }}
                >Ver</Link>
                <button type="button" className="btn btn-danger" onClick={() => this.deletePatient(patient.id)}>Eliminar</button></td>
      </tr>
      </tbody>
      </Table>
    )
        }
    return (
      <div className="container">
        <div className="row mt-5">
          <div className="col-lg-3">
            <div className="row">
              <h2>Filtrar por: </h2>
            </div>
            <div className="row">
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="id" onChange = {this.handleChangeBox}/>
                <label className="form-check-label" htmlFor="inlineRadio1">Id</label>
              </div>
            </div>
            <div className="row">
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="run" onChange = {this.handleChangeBox}/>
                <label className="form-check-label" htmlFor="inlineRadio2">Rut</label>
              </div>
            </div>
            
          </div>
          <div className="col-9">
            <div className="row">
              <div className="col">
                <label >Buscar</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChangeBar} />
                <input type="submit" value="Submit" className="btn btn-primary" onClick ={this.handleSubmit} />
              </div>
            </div>
            <div className="row">
              <div className="col">
                {!(this.state.patient === '') && <div>{RowPaciente(this.state.patient)}</div>} 
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default SearchForm;