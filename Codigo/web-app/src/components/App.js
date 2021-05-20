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

const App = ({ history }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (user === null) {
      history.push("/");
    }
  }, [user, history]);

  const validateAccount = async (email, senha) => {
    const { data } = await restAPI.get(
      `/usuarios/logar?email=${email}&senha=${senha}`
    );

    setUser(data);

    return data;
  };

  return (
    <UserContext.Provider value={user}>
      <div className="App">
        <Header />
        <Route exact path="/">
          <Login onSubmit={validateAccount} />
        </Route>
        <Route exact path="/index">
          <Index />
        </Route>
        <Route exact path="/documento">
          <Document />
        </Route>
        <Route path="/documento/:id">
          <Codelist />
        </Route>
        <Route path="/cadastrar">
          <SignUp />
        </Route>
      </div>
    </UserContext.Provider>
  );
};

export default withRouter(App);
