import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

class CreateForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) { this.setState({ value: event.target.value }); }
  handleSubmit(event) {
    alert('A name was submitted: ' + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <div className="container">
        <div className="row mt-4">
          <div className="col">
            <form onSubmit={this.handleSubmit}>
              <div className="form-group">
                <label>Nombre:</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
                <label>Apellido:</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
              </div>
              <div className="form-group">
                <label>Run:</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
              </div>
              <div className="form-group">
                <label>Sexo:</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
              </div>
              <div className="form-group">
                <label>Address:</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
              </div>
              <input type="submit" value="Submit" className="btn btn-primary"/>
            </form>
          </div>
        </div>
      </div>
    );
  }
}
export default CreateForm;