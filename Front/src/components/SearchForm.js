import React, { Component } from 'react';

class SearchForm extends React.Component {
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
        <div className="row mt-5">
          <div className="col-lg-3">
            <div className="row">
              <h2>Filtrar por: </h2>
            </div>
            <div className="row">
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" />
                <label className="form-check-label" for="inlineRadio1">Id</label>
              </div>
            </div>
            <div className="row">
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" />
                <label class="form-check-label" for="inlineRadio2">Rut</label>
              </div>
            </div>
            <div className="row">
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" />
                <label class="form-check-label" for="inlineRadio2">Nombre</label>
              </div>
            </div>
          </div>
          <div className="col-9">
            <div className="row">
              <div className="col">
                <label >Buscar</label>
                <input className="form-control" type="text" value={this.state.value} onChange={this.handleChange} />
                <input type="submit" value="Submit" className="btn btn-primary" />
              </div>
            </div>
            <div className="row">
              <div className="col">
                {/*mostrar paciente*/}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default SearchForm;