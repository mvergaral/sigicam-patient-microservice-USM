import React, { Component } from 'react';
import history from '../helpers/history';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import routes from './routes'

class NavPaciente extends Component {
  constructor(props) {
    super(props);

    this.state = {
      pages: routes(),
    }

    this.onPageClick = this.onPageClick.bind(this);
  }

  onPageClick(root) {
    history.push(root);
  }

  render() {
    const Li = styled.li`
    &:hover {
      background: #80868c;
    }
    `;
    const Nav = styled.nav`
      background: #090a0b;
    `;
    const pages = this.state.pages;
    return (
      <div>
        <Nav className="navbar navbar-expand-lg  navbar-dark" style={{ padding: 0 }}>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse d-flex justify-content-center" id="navbarNav">
            <ul className="navbar-nav">
              <Li className="nav-item">
                <Link className="nav-link active"
                  to={{ pathname: "/" }}
                >{pages[0].name}</Link>
              </Li>
              <Li className="nav-item">
                <Link className="nav-link active"
                  to={{ pathname: "/search" }}
                >{pages[1].name}</Link>
              </Li>
              <Li className="nav-item">
                <Link className="nav-link active"
                  to={{ pathname: "/new" }}
                >{pages[2].name}</Link>
              </Li>
            </ul>
          </div>
        </Nav>
      </div>
    );
  }
};

export default NavPaciente;