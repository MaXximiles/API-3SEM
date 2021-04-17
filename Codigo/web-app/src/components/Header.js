import "./Header.css";
import React from "react";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className="Header ui secondary pointing menu">
      <Link to="/documento" className="item">
        Manuais
      </Link>
      <Link to="/" className="right item">
        Logout
      </Link>
    </div>
  );
};

export default Header;
