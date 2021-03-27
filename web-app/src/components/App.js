import React from "react";
import Codelist from "./Codelist";
import Login from "./Login";
import Route from "./Route";
import restAPI from "../apis/restAPI";

const App = () => {
  const validateAccount = async (usrEmail, usrSenha) => {
    const { data } = await restAPI.post("/validateuser", {
      data: {
        usrEmail,
        usrSenha,
      },
    });

    return data;
  };

  return (
    <React.Fragment>
      <Route path="/">
        <Login onSubmit={validateAccount} />
      </Route>
      <Route path="/codelist">
        <Codelist />
      </Route>
    </React.Fragment>
  );
};

export default App;
