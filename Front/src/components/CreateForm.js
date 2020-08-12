import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
class CreateForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
      nombre: '',
      apellidos: '',
      run: '',
      sex: '',
      adress: '',
  }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) { 
    const {name,value} = event.target
    this.setState({ 
      [name]: value
    }) 
  }
  handleSubmit(event) {
    
    
    fetch('http://localhost:8000/patient/new', {
      method: 'POST',
      headers: {
        'Content-type':'application/json'
      },

      body: JSON.stringify(
        {
          run: this.state.run,
          name:this.state.nombre,
          last_name:this.state.apellidos,
          gender:this.state.sex,
          address: this.state.adress
        }
      )
    })
    .then((response) => {
      if(response.ok){
        alert("Paciente creado")
      }
      else{
        alert("Error")
      }
    })

    event.preventDefault();
  }

  render() {
    return (
      <div className="container">
        <div className="row mt-4">
          <div className="col">
            <form onSubmit={this.handleSubmit}>
              <Container>
              <Row>
              
                <Col>
                <label>Nombre:</label>
                <input name ='nombre' className="form-control" type="text" value={this.state.nombre} onChange={this.handleChange} />
                </Col>
                <Col>
                <label>Apellidos:</label>
                <input name = 'apellidos' className="form-control" type="text" value={this.state.apellidos} onChange={this.handleChange} />
                </Col>
              
              </Row>
              
              <div className="form-group">
                <label>Run:</label>
                <input name= 'run'  className="form-control" type="text" value={this.state.run} onChange={this.handleChange} />
              </div>
              <div className="form-group">
                <label>Sexo:</label>
                <input name= 'sex' className="form-control" type="text" value={this.state.sex} onChange={this.handleChange} />
              </div>
              <div className="form-group">
                <label>Address:</label>
                <input name= 'adress' className="form-control" type="text" value={this.state.adress} onChange={this.handleChange} />
              </div>
              </Container>
              <input type="submit" value="Submit" className="btn btn-primary"/>
            </form>
          </div>
        </div>
      </div>
    );
  }
}
export default CreateForm;