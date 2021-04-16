import React from "react";
import { Link, BrowserRouter as Router } from "react-router-dom";

const Header = () => {
  return (
    <div className="ui secondary pointing menu">
      <Router>
        <Link to="/documento" className="item">
          Manuais
        </Link>
        <Link to="/bloco" className="item">
          Blocos
        </Link>
        <Link to="/" className="right item">
          Logout
        </Link>
      </Router>
    </div>
  );
};

export default Header;
