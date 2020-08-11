import React from 'react'
import Form from 'react-bootstrap/Form'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
class FormPaciente extends React.Component {

    render(){
        return(

            <div>
                <Form>
                    <Row>
                        <Col>
                            <Form.Group  controlId="formNombre">
                                <Form.Label>Nombre </Form.Label>
                                <Form.Control type="name" placeholder="Enter email" />
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group  controlId="formApellidos">
                                <Form.Label>Apellidos</Form.Label>
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>
                        </Col>


                    </Row>

                    <Row>
                    <Form.Group className ="col-md-6 mb-3" controlId="formRUT">
                        <Form.Label>Fecha Nacimiento</Form.Label>
                        <Form.Control type="name" placeholder="Enter email" />
                    </Form.Group>
                    </Row>
                   
                     
                </Form>
             </div>
        )
    }
}

export default FormPaciente