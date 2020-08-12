import React from 'react'
import { BrowserRouter, Switch, Route } from "react-router-dom";
import history from './helpers/history'
import Table from './components/Tabla'
import NavBar from './components/navbar'
import NavPatient from './components/NavPatient'
import CreateForm from './components/CreateForm'
import SearchForm from './components/SearchForm'
import Patient from './components/patient'
class App extends React.Component {
  constructor(){
    super()
  }
  render() {
    return (
      <BrowserRouter
        history={history}>
        <div>
          <NavBar />
          <NavPatient />
          <Switch>
            <Route
              exact
              path='/'
              component={Table}
            />
            <Route
              exact
              path='/new'
              component={CreateForm}
            />
            <Route
              exact
              path='/search'
              component={SearchForm}
            />
            <Route
              exact
              path='/patient'
              component={Patient}
            />
          </Switch>
        </div>
      </BrowserRouter>
    )
  }
}

export default App
