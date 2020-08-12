import React, { Component } from 'react';
import '../index.css'
import Dropdown from 'react-dropdown';

class Patient extends Component {
  constructor() {
    super()
    this.state = {
      id:-1,
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
      available_couchs: [],
      new_bed: -1,
      new_bed_str: '',
      new_couch: -1,
      new_couch_str: ''
    }
    this.setBed = this.setBed.bind(this);
    this.setCouch = this.setCouch.bind(this);
    this.dropBed = this.dropBed.bind(this);
    this.dropCouch = this.dropCouch.bind(this);
    this.setApiBed = this.setApiBed.bind(this);
    this.setApiCouch = this.setApiCouch.bind(this);
    this.dropApiBed = this.dropApiBed.bind(this);
    this.dropApiCouch = this.dropApiCouch.bind(this);
  }
  componentDidMount(){
    console.log(this.props.location.state.id)
    fetch(`http://localhost:8000/patient/`+this.props.location.state.id)
      .then(data => data.json())
      .then(data => {
        this.setState({
          id: data.id,
          name: data.name,
          last_name: data.last_name,
          run: data.run,
          gender: data.gender,
          address: data.address,
        })
        fetch(`http://localhost:8000/patient/`+this.props.location.state.id+`/activeBed`)
        .then(data => data.json())
        .then(data => {
          this.setState({bed_id: data})
          this.setState({ is_using_bed: true })
        })
        fetch(`http://localhost:8000/patient/` + this.props.location.state.id + `/activeCouch`)
          .then(data => data.json())
          .then(data => {
            this.setState({ couch_id: data })
            this.setState({ is_using_couch: true })
          })
      })
    fetch("https://recuperacionisw.herokuapp.com/api/camas?disponibilidad=disponible")
      .then(data => data.json())
      .then(data => {
        this.setState({ available_beds: data })
      })
    fetch("https://quimioterapia.herokuapp.com/api/sillon")
      .then(data => data.json())
      .then(data => {
        this.setState({ available_couchs: data })
      })
  }

  setApiBed() {
    fetch("https://recuperacionisw.herokuapp.com/api/camas/" + this.new_bed, {
      method: "PUT",
      body: JSON.stringify({ id: this.bed_id, estado: "ocupada" })
    })
  }
  dropApiBed() {
    fetch("https://recuperacionisw.herokuapp.com/api/camas/" + this.bed_id, {
      method: "PUT",
      body: JSON.stringify({ id: this.bed_id, estado: "disponible" })
    })
  }

  setApiCouch() {
    fetch("https://quimioterapia.herokuapp.com/api/sillon/"+this.state.couch_id)
    .then(data => {
      fetch("https://quimioterapia.herokuapp.com/api/sillon/", {
        method: "PUT",
        body: JSON.stringify({ 
          id: this.couch_id, 
          asignado: "true",
          activo: data.activo,
          idSala: data.idSala
        })
      })
    })
  }
  dropApiCouch() {
    fetch("https://quimioterapia.herokuapp.com/api/sillon/" + this.state.couch_id)
      .then(data => {
        fetch("https://quimioterapia.herokuapp.com/api/sillon/", {
          method: "PUT",
          body: JSON.stringify({
            id: this.couch_id,
            asignado: "false",
            activo: data.activo,
            idSala: data.idSala
          })
        })
      })
  }

  setBed(){
    fetch('http://localhost:8000/patient/' + this.state.id +'/assignBed?id_bed='+this.state.new_bed, { method: 'PUT' })
    .then(data =>{
      if(data.ok){
        this.setApiBed()
        alert("Asignado")
        window.location.reload(false)
      }
      else{
        alert("Error")
      }
    })
  }


  setCouch(){
    fetch('http://localhost:8000/patient/' +this.state.id+ '/assignCouch?id_couch=' + this.state.new_couch, { method: 'PUT' })
      .then(data => {
        if (data.ok) {
          this.setApiCouch()
          alert("Asignado")
          window.location.reload(false)
        }
        else {
          alert("Error")
        }
      })
  }
  getNewBed(bed_id, room_id){
    this.setState({new_bed: bed_id})
    this.setState({ new_bed_str: "Sala: " + room_id + " Cama: " + bed_id})
  }
  getNewCouch(couch_id, room_id) {
    this.setState({ new_couch: couch_id })
    this.setState({ new_couch_str: "Sala: " + room_id + " Cama: " + couch_id })
  }

  dropBed(){
    fetch('http://localhost:8000/patient/' + this.state.id + '/deallocateBed', { method: 'PUT' })
    .then(data => {
      if(data.ok){
        alert("Eliminado")
        window.location.reload(false)
      }
    })

  }
  dropCouch() {
    fetch('http://localhost:8000/patient/' + this.state.id + '/deallocateCouch', { method: 'PUT' })
      .then(data => {
        if (data.ok) {
          alert("Eliminado")
          window.location.reload(false)
        }
      })

  }
  render() {
    const bed = () => {
      if(this.state.is_using_bed){
        return (<td>{this.state.bed_id} <button type="button" class="btn btn-danger" onClick={this.dropBed}>Eliminar</button></td>)
      }
      else{
        return(
          <td>
            <div className="dropdown">
        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{this.state.new_bed == -1 ? "Seleccionar" : this.state.new_bed_str}</button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                {this.state.available_beds.map((bed) => <a className="dropdown-item" onClick={() => this.getNewBed(bed.id, bed.sala)}>Sala: {bed.sala} Cama: {bed.id}</a>)}
              </div>
            </div>
            <button type="button" className="btn btn-primary" onClick={this.setBed}>Asignar</button>
          </td>
        )
      }
    }
    const couch = () => {
      if (this.state.is_using_couch) {
        return (<td>{this.state.couch_id} <button type="button" class="btn btn-danger" onClick={this.dropCouch}>Eliminar</button></td>)
      }
      else {
        return (
          <td>
            <div className="dropdown">
              <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{this.state.new_couch == -1 ? "Seleccionar" : this.state.new_couch_str}</button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                {this.state.available_couchs.map((couch) => <a className="dropdown-item" onClick={() => this.getNewCouch(couch.id, couch.idSala)}>Sala: {couch.idSala} Sillón: {couch.id}</a>)}
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