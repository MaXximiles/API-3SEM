import React from "react";
import DirectoryTree from "./DirectoryTree";
import Login from "./Login";
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
    <React.Fragment>
      <Route path="/">
        <Login onSubmit={validateAccount} />
      </Route>
      <Route path="/codelist">
        <DirectoryTree />
      </Route>
    </React.Fragment>
  );
};

export default App;
