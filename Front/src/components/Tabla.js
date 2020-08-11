import React from 'react'
import Table from 'react-bootstrap/Table'
import InputGroup from 'react-bootstrap/InputGroup'
import FormControl from 'react-bootstrap/FormControl'

import todosData from '../data/todosData'
class Tabla extends React.Component {
    constructor(){
        super()
        this.state = {
            filtro: ""
        }
        this.handleSearchBar = this.handleSearchBar.bind(this)
    }

    handleSearchBar(event){
    
      this.setState({
          filtro: event.target.value
      })
        
    }
    
    
        
    render(){
        const RowPaciente = (paciente)=>{
            
            //3 lineas para el filtro, y solo checkea datos que sean strings
            const values = Object.entries(paciente).map((valor)=>{return valor[1]}) 
            const stringValues = values.filter(  foo => typeof foo === "string" )
            const includeFilters = stringValues.map((bar)=>{return bar.includes(this.state.filtro)}).includes(true)
            return(
                includeFilters &&
                <tr 
                key ={paciente.id} 
                onClick = {() => this.props.handleTableClick(paciente)}
                >
                    <td>{paciente.id}</td><td>{paciente.text}</td>                   
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
                        {todosData.map((dataPaciente) => RowPaciente(dataPaciente))}
                    </tbody>
                </Table>
              
            </div>

        )
    }

}

export default Tabla