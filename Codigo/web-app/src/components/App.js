import "./App.css";
import React from "react";
import Login from "./Login";
import Codelist from "./Codelist";
import Document from "./Document";
import Header from "./Header";
import Index from "./Index";
import { BrowserRouter as Router, Route } from "react-router-dom";
import restAPI from "../apis/restAPI";

const App = () => {
  const validateAccount = async (usrEmail, usrSenha) => {
    const { data } = await restAPI.post("/validateuser", {
      usrEmail,
      usrSenha,
    });

    return data;
  };

  return (
    <div className="App">
      <Router>
        <Header />
        <Route exact path="/">
          <Login onSubmit={validateAccount} href="/codelist" />
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
      </Router>
    </div>
  );
};

export default App;
