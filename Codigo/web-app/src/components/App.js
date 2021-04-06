import "./App.css";
import React from "react";
import Login from "./Login";
import Codelist from "./Codelist";
import Route from "./Route";
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
      <Route path="/">
        <Login onSubmit={validateAccount} href="/codelist" />
      </Route>
      <Route path="/codelist">
        <Codelist />
      </Route>
    </div>
  );
};

export default App;
