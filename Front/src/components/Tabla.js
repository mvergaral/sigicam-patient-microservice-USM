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
						filtro: "",
						patients: [] 
        }
        this.handleSearchBar = this.handleSearchBar.bind(this)
    }

    handleSearchBar(event){
    
      this.setState({
          filtro: event.target.value
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
            
            //3 lineas para el filtro, y solo checkea datos que sean strings
            const values = Object.entries(patient).map((valor)=>{return valor[1]}) 
            const stringValues = values.filter(  foo => typeof foo === "string" )
            const includeFilters = stringValues.map((bar)=>{return bar.includes(this.state.filtro)}).includes(true)
            return(
                includeFilters &&
                <tr 
                key ={patient.id} 
                onClick = {() => this.props.handleTableClick(patient)}
                >
                    <td>{patient.id}</td><td>{patient.name}</td>                   
                </tr>
            )
        }
        return(
            
            
            <div >
                <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text  value = {this.state.filtro} id="basic-addon1">Filtro</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl value = {this.state.filtro} onChange ={this.handleSearchBar}
                            aria-describedby="basic-addon1"
                        />
                </InputGroup>
                <Table striped bordered hover >
                    <thead>
                        <tr>
                            <th>#</th><th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody >
                        {this.state.patients.map( patient => RowPaciente(patient))}
                    </tbody>
                </Table>
              
            </div>
          

        )
    }

}

export default Tabla