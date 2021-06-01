import "./Header.css";
import React, { useContext } from "react";
import { Link } from "react-router-dom";
import UserContext from "./UserContext";

const Header = ({ logout }) => {
  const authUser = useContext(UserContext);

  return (
    <div className="Header ui secondary pointing menu">
      <Link to="/index" className="item">
        Home
      </Link>
      {authUser ? (
        <AuthenticatedHeader logout={logout} />
      ) : (
        <UnauthenticatedHeader />
      )}
    </div>
  );
};

const AuthenticatedHeader = ({ logout }) => {
  return (
    <React.Fragment>
      <Link to="/documento" className="item">
        Manuais
      </Link>
      <Link to="/traco" className="item">
        Traços
      </Link>
      <Link to="/tag" className="item">
        Tags
      </Link>
      <Link to="/" className="right item" onClick={() => logout(null)}>
        Logout
      </Link>
    </React.Fragment>
  );
};

const UnauthenticatedHeader = () => {
  return (
    <React.Fragment>
      <Link to="/" className="right item">
        Login
      </Link>
    </React.Fragment>
  );
};

export default Header;
