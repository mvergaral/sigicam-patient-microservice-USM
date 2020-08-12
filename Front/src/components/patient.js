import React, { Component } from 'react';
import '../index.css'

class Patient extends Component {
  constructor() {
    super()
    this.state = {
      name: "",
      last_name: "",
      run: "",
      gender: "",
      address: "",
      is_using_bed: false,
      is_using_couch: false,
      bed_id: null,
      couch_id: null,
      available_beds: [],
      available_couchs: []
    }
  }
  componentDidMount(){
    console.log(this.props.location.state.id)
    fetch(`http://localhost:8000/patient/`+this.props.location.state.id)
      .then(data => data.json())
      .then(data => {
        this.setState({
          name: data.name,
          last_name: data.last_name,
          run: data.run,
          gender: data.gender,
          address: data.address,
          is_using_bed: data.is_using_bed,
          is_using_couch: data.is_using_couch
        })
        if(data.is_using_bed){
          //pedir id de cama
        }
        if(data.is_using_couch){
          //pedir id de sillon
        }
      })
  }
  setBed(){

  }
  setCouch(){

  }


  render() {
    const bed = () => {
      if(this.state.is_using_bed){
        return (<td>{this.state.bed_id} <button type="button" class="btn btn-danger">Eliminar</button></td>)
      }
      else{
        fetch("https://recuperacionisw.herokuapp.com/api/camas?disponibilidad=disponible")
          .then(data => data.json())
          .then(data => {
            this.setState({ available_beds: data })
          })
        return(
          <td>
            <div className="dropdown">
              <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Seleccionar cama</button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
        {this.state.available_beds.map((bed) => <a className="dropdown-item">Sala: {bed.sala} Cama: {bed.id}</a>)}
              </div>
            </div>
            <button type="button" className="btn btn-primary" onClick={this.setBed}>Asignar</button>
          </td>
        )
      }
    }
    const couch = () => {
      if (this.state.is_using_couch) {
        return (<td>{this.state.couch_id} <button type="button" class="btn btn-danger">Eliminar</button></td>)
      }
      else {
        fetch("https://quimioterapia.herokuapp.com/api/sillon")
          .then(data => data.json())
          .then(data => {
            this.setState({ available_couchs: data })
          })
        return (
          <td>
            <div className="dropdown">
              <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Seleccionar cama</button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
        {this.state.available_couchs.map((couch) => <a className="dropdown-item">Sala: {couch.idSala} Sillon: {couch.id}</a>)}
              </div>
            </div>
            <button type="button" className="btn btn-primary" onClick={this.setCouch}>Asignar</button>
          </td>
        )
      }
    }
    return(
      	<div className="container">
          <div className="row mt-5">
            <div className="col">
              <h2>Paciente</h2>
            </div>
          </div>
          <div className="row mt-5">
            <div className="col">
              <table className="table">
                <thead>
                  <tr>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Nombre</td>
                    <td>{this.state.name} {this.state.last_name}</td>
                  </tr>
                  <tr>
                    <td>Run</td>
                    <td>{this.state.run}</td>
                  </tr>
                  <tr>
                    <td>Sexo</td>
                    <td>{this.state.gender}</td>
                  </tr>
                  <tr>
                    <td>Dirección</td>
                    <td>{this.state.address}</td>
                  </tr>
                  <tr>
                    <td>Cama</td>
                    {bed()}
                  </tr>
                  <tr>
                    <td>Sillón</td>
                    {couch()}
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
    );
  }

}

export default Patient