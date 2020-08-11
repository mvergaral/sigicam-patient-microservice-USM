import React from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Tabla from './components/Tabla'
import NavBar from './components/navbar'
import FormPaciente from './components/FormPaciente'
import Button from 'react-bootstrap/Button'
import PanelPaciente from './components/PanelPaciente'
class App extends React.Component {
  constructor(){
    super()
    this.state = {
      displayForm: false,
      displayPanel: false,
      selectedRow: null,
      searchBar: ""
    }
    this.handleButtonClick = this.handleButtonClick.bind(this)
    this.handleTableClick = this.handleTableClick.bind(this)

  }

  handleButtonClick(){
    this.setState( prevState=>{
      const updatedState = {
        ...prevState,
        displayForm : !prevState.displayForm,
        displayPanel: prevState.displayForm
      }
      return updatedState  
    })
  }
  handleTableClick(selected){
    
    this.setState( ()=>{
      
      return {
        selectedRow : selected,
        displayPanel:true,
        displayForm:false

      }
      
    })
  }

  render() {
    
    const formComponent = this.state.displayForm && <><FormPaciente/> <Button style = {{margin: "10px"}} onClick = {()=> {this.handleButtonClick()}} variant="primary" type="submit">Subir</Button></> 
    const panelComponent = this.state.displayPanel && <PanelPaciente dataPaciente = {this.state.selectedRow}/>

      return (
        
        <div>
          <NavBar/>
          <Container >
            <Row style = {{textAlign: "center"}}>
              <Col>
                <h1>PACIENTES</h1>
                <Button variant = "primary" onClick = {()=> {this.handleButtonClick()}}> 
                  {this.state.displayForm ? "Cancelar" : "Agregar Paciente"}
                </Button >
                <Tabla handleTableClick = {this.handleTableClick}/>
               
              </Col>
              <Col>
                {formComponent}
                {panelComponent}
              </Col>
            </Row>
          </Container>
        </div>
      )
  }
}

export default App
