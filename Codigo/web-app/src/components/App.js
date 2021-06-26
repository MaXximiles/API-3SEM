import "./App.css";
import React, { useEffect, useState } from "react";
import Login from "./Login";
import Codelist from "./Codelist";
import Document from "./Document";
import Header from "./Header";
import Index from "./Index";
import SignUp from "./SignUp";
import { withRouter, Route } from "react-router-dom";
import restAPI from "../apis/restAPI";
import UserContext from "./UserContext";
import Tracos from "./Tracos";
import Tags from "./Tags";
import Usuario from "./Usuario";

const App = ({ history }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    setUser(window.localStorage.getItem("token"));
  }, []);

  const validateAccount = async (email, senha) => {
    const { data } = await restAPI.get(
      `/usuarios/logar?email=${email}&senha=${senha}`
    );

    console.log(data);

    if (data) {
      window.localStorage.setItem("token", JSON.stringify(data));
      setUser(data);
    }

    return data;
  };

  const logout = () => {
    window.localStorage.removeItem("token");
    setUser(null);
  };

  return (
    <UserContext.Provider value={user}>
      <div className="App">
        <Header logout={logout} />
        <Route exact path="/">
          <Login onSubmit={validateAccount} />
        </Route>
        <Route exact path="/index">
          <Index />
        </Route>
        <Route exact path="/traco">
          <Tracos />
        </Route>
        <Route exact path="/tag">
          <Tags />
        </Route>
        <Route exact path="/documento">
          <Document />
        </Route>
        <Route exact path="/documento/:id">
          <Codelist />
        </Route>
        <Route exact path="/usuarios">
          <Usuario />
        </Route>
        {/* <Route exact path="/cadastrar">
          <SignUp />
        </Route> */}
      </div>
    </UserContext.Provider>
  );
};

export default withRouter(App);
